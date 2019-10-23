package com.gjxaiou.dao.split;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author GJXAIOU
 * @create 2019-10-20-20:54
 */
public class DynamicDataSourceHolder {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    // 创建一个线程安全对象来保存 key;
    private static ThreadLocal<String> contextHolder = new ThreadLocal<String> ();
    // key 一共取值有两种
    public static final String  DB_MASTER = "master";
    public static final String  DB_SLAVE = "slave";

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }
    // 设置线程的 DBtype
    public static void setDbType(String str) {
        logger.debug("所使用的数据源为：" + str);
        contextHolder.set(str);
    }

    // 清洗数据源，清理连接类型
    public static void clearDBType(){
        contextHolder.remove();
    }
}
