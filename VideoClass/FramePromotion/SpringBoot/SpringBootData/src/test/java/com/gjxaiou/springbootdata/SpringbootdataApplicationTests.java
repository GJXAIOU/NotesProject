package com.gjxaiou.springbootdata;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootdataApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        // output：class com.zaxxer.hikari.HikariDataSource
        // springboot 1.X 版本输出为：org.apache.tomcat.jdbc.pool.DataSource
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        // output：HikariProxyConnection@398088176 wrapping com.mysql.cj.jdbc.ConnectionImpl@3481ff98
        System.out.println(connection);
        connection.close();
    }
}