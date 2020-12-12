package com.gjxaiou.service.impl;

import com.gjxaiou.pojo.People;
import com.gjxaiou.service.PeopleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-30-10:45
 */
public class PeopleServiceImpl implements PeopleService {
    public List<People> selectAll() throws IOException {
        // MyBatis 默认不加载配置文件，因此需要先加载配置文件，返回整个配置文件的流对象
        // 在数据访问层处理异常和在控制器中处理异常，一般在 service 中只抛出异常；
        InputStream inputStream = Resources.getResourceAsStream("MyBatis.xml");
        // 前面是工厂       实例化工厂对象时使用的是构建者设计模式   它的名称标志:后面有Builder
        // 构建者设计模式意义: 简化对象实例化过程
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 整个 sqlsession 就是 MyBatis 中 API 封装的对象,增删改查都在里面
        SqlSession session = factory.openSession();
        List<People> list = session.selectList("com.gjxaiou.mapper.PeopleMapper.selAll");
        session.close();
        return list;
    }
}
