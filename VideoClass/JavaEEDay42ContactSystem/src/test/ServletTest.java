package test;

import org.junit.Test;
import util.JdbcUtil;

import java.sql.Connection;

/**
 * @author GJXAIOU
 * @create 2019-08-09-19:36
 */
public class ServletTest {
    @Test
    public void servletTest(){
        Connection connection = JdbcUtil.getConnection();
    }

}
