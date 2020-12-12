package dao;

import pojo.Users;

import java.util.List;

/**
 * 对User表的操作
 *
 * @author GJXAIOU
 * @create 2019-08-23-9:56
 */
public interface UserDaoInterface {
    /**
     * 注：在Dao中创建方法名一般不涉及具体的业务逻辑，因为Dao是专门操作数据库，因此命名一般采用增删改查
     *     来命名，例如使用getUser()等等。
     */
    List<Users> getUsers(String username, String password);

}
