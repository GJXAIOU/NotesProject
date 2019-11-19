package jdbc.preparedStatement;


import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**通过使用PreparedStatement实现增删改查
 * @author GJXAIOU
 * @create 2019-08-01-21:23
 */
public class PersonDao {
    /**
     * 输入一个Person类对象，保存数据到数据库中
     * @param person
     * @return int类型，返回值大于0表示添加成功，等于0表示添加数据失败
     */
    public int addTest(Person person) {
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备预处理SQL语句
        String sql = "insert into person(name, gender, score, home, hobby) values(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            // 3.使用preparedStatement处理SQL语句
            preparedStatement = connection.prepareStatement(sql);

            //插入数据
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getGender());
            preparedStatement.setInt(3, person.getScore());
            preparedStatement.setString(4,person.getHome());
            preparedStatement.setString(5, person.getHobby());

            //指向SQL语句，返回的int 类型为影响的行数
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement);
        }

        return 0;

    }

    /**
     * 根据id删除数据库中数据
     * @param id
     * @return int类型，返回值大于0表示删除成功，返回0表示删除数据失败
     */
    public int deleteTest(int id){
        // 1.建立数据库连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备预处理SQL语句
        String sql = "delete from person where id = ?";

        // 3.使用preparedStatement处理SQL语句
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 4.输入参数
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(connection, preparedStatement);
        }

        return 0;
    }

    /**
     * 修改Person表中数据信息
     * @param person 传入的person类对象
     * @return int类型，返回值大于0表示修改成功，返回0表示修改数据失败
     */
    public int updateTest(Person person){
        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备预处理的SQL语句
        String sql = "update person set name = ? , gender = ? where id = ?";

        // 3.使用preparedstatement处理SQL语句
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 4.输入参数
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getGender());
            preparedStatement.setInt(3, person.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(connection, preparedStatement);
        }

        return 0;
    }

    /**
     * 查询数据库person中所有的信息，返回一个list集合
     * @return 返回保存person类对象的list集合
     */
    public List<Person> selectTest() {
        ResultSet set = null;
        PreparedStatement preparedStatement = null;
        List<Person> list = new ArrayList<Person>();

        // 1.建立连接
        Connection connection = JdbcUtil.getConnection();

        // 2.准备SQL语句
        String sql = "select * from person";

        // 3.使用preparedstatement执行SQL语句
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 4.接收结果集
            set = preparedStatement.executeQuery();

            // 5.获取查找结果
            while (set.next()){
                Person person = new Person();
                person.setId(set.getInt("id"));
                person.setName(set.getString("name"));
                person.setGender(set.getString("gender"));
                person.setScore(set.getInt("score"));
                person.setHome(set.getString("home"));
                person.setHobby(set.getString("hobby"));

                list.add(person);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnectionWithResult(connection, preparedStatement, set);
        }
        return null;
    }

    /**
     * 根据ID，查询数据库person中的对应信息，返回一个person类对象
     * @param id 要查询的id
     * @return 返回一个person类对象，如果没有找到，返回null
     */

    public Person selectByIdTest(int id){
        ResultSet set = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Person person = new Person();


        // 1.连接数据库
        connection = JdbcUtil.getConnection();

        // 2.准备SQL语句
        String sql = "select * from person where id = ?";

        // 3.使用Preparedstatement指向SQL语句
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 4.传入参数：
            preparedStatement.setInt(1,id);

            set = preparedStatement.executeQuery();

            if (set.next()){
                person.setId(set.getInt("id"));
                person.setName(set.getString("name"));
                person.setGender(set.getString("gender"));
                person.setScore(set.getInt("score"));
                person.setHome(set.getString("home"));
                person.setHobby(set.getString("hobby"));
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnectionWithResult(connection, preparedStatement, set);
        }
        return null;
    }



}
