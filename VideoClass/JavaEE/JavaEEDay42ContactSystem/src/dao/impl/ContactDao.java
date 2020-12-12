package dao.impl;

import entity.Contact;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-09-10:58
 */
public interface ContactDao {
    /**
     * 添加联系人
     * @param contact 要添加的联系人对象
     */
    void addContact(Contact contact);

    /**
     * 删除联系人
     * @param id 要删除的联系人的id
     */
    void deleteContact(int id);

    /**
     * 修改联系人
     * @param contact 要修改的联系人对象
     */
    void updateContact(Contact contact);

    /**
     * 根据id查询联系人
     * @param id 要查询的联系人id
     * @return 要查询的联系人对象
     */
    Contact findContact(int id);

    /**
     * 查询所有联系人
     * @return 所有联系人对象，使用List集合保存，集合中数据为Contact类对象
     */
    List<Contact>  findAllContact();


}
