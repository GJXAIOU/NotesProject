package com.gjxaiou.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

/**
 * @author GJXAIOU
 * @create 2019-10-20-21:08
 */
// 因为 insert 会封装到 update 中，不要单独表明了；
@Intercepts({@Signature(type = Executor.class, method ="update",args = {MappedStatement.class,
        Object.class}), @Signature(type = Executor.class, method ="query",args =
        {MappedStatement.class,
        Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    // 正则表达式，用于判断是否是 insert、delete、update 方法；
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    // 下面方法拦截之后，决定使用哪一个数据源
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive =
                TransactionSynchronizationManager.isActualTransactionActive();
        // 接收 mybatis传过来的操作的参数,然后进行比对；
        Object[] objects = invocation.getArgs();
        // 相当于获取 SQL 语句最前面，就是SQL是增？删？。。
        MappedStatement ms = (MappedStatement) objects[0];
        String lookupKey =  DynamicDataSourceHolder.DB_MASTER;
        // 如果操作时事务的
        if (synchronizationActive != true){

            // 读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
                // 如果 selectKey 为自增 id 查询主键，即mybatis 使用了select Last_insert_ID()，方法, 这是用使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                }else{
                    // 获取 SQL 语句，然后将所有的制表符、换行符等等换成空格；
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n" +
                            "\\r]"," ");
                    // 使用正则判断是不是插入、删除、更新方法；
                    if (sql.matches(REGEX)){
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    }else {
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        }else {
            // 如果是事务操作，一般都是写操作，使用主库
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        // 日志信息
        logger.debug("设置的方法[{}] use [{}] Strategy, SqlCommanType [{}]..",ms.getId(), lookupKey,
                ms.getSqlCommandType().name());

        DynamicDataSourceHolder.setDbType(lookupKey);
        // 然后正式开始进行操作
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 如果拦截的对象 target 是 mybatis 的 Executor 的类型的话，就进行拦截，就将上面的 intercept 包装进去；
            // 因为 Executor 是用来支持一系列的增删改查操作，相当于有增删改查操作就拦截；
        if (target instanceof Executor){
            return Plugin.wrap(target,this);
        }else {
            // 如果不是则返回对象本体；
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }
}
