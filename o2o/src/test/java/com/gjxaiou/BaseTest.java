package com.gjxaiou;

/**
 * @author GJXAIOU
 * @create 2019-10-12-14:31
 */


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置 spring 和 junit 的整合，junit 启动的时候加载 spring IOC 容器(初始化 spring 容器)
 */
//表示继承了SpringJUnit4ClassRunner类，让测试运行于Spring测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//配置文件，多个配置文件可以用locations
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml","classpath:spring/spring" +
        "-service.xml"})
public class BaseTest {

}
