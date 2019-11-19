package dao.impl;
import	java.util.ArrayList;

import dao.UserDaoInterface;
import pojo.Users;
import utils.BaseDao;

import java.util.List;

/**根据用户名和密码查询数据
 * @author GJXAIOU
 * @create 2019-08-23-10:08
 */
public class UserDaoImpl extends BaseDao implements UserDaoInterface {
    @Override
    public List<Users> getUsers(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        List<String> list = new ArrayList<>();
        list.add(username);
        list.add(password);
        // 参数含义：SQL 语句； SQL 语句中占位符的数据；查询语句返回的泛型
        return super.inquiryCurrent(sql, list.toArray(), Users.class);
    }
}
