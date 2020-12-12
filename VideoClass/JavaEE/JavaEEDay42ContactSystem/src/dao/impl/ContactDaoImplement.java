package dao.impl;

import entity.Contact;
import util.BaseDao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现Dao接口的所有方法
 * 基于BaseDao和实现Dao层接口，完成数据库操作
 * @author GJXAIOU
 * @create 2019-08-09-13:56
 */
public class ContactDaoImplement extends BaseDao implements ContactDao {
    String sql = null;
    Object[] paramsValue = null;

    @Override
    public void addContact(Contact contact) {
        // 准备SQL语句
        sql = "insert into contact_system(name, gender, age, tel, qq, email) value(?, ?, ?, ?, ?, ?)";

        List list = new ArrayList();
        list.add(contact.getName());
        list.add(contact.getGender());
        list.add(contact.getAge());
        list.add(contact.getTel());
        list.add(contact.getQq());
        list.add(contact.getEmail());

        paramsValue =  list.toArray();
        super.updateCurrent(sql, paramsValue);

    }

    @Override
    public void deleteContact(int id) {
        sql = "delete from contact_system where id = ?";

        Object[] objects = {id};
        super.updateCurrent(sql, paramsValue)   ;

    }

    @Override
    public void updateContact(Contact contact) {
        sql = "update contact_system set name = ?, gender = ?, age = ?, tel = ?, qq = ?, email = ? ";

        List list = new ArrayList();
        list.add(contact.getName());
        list.add(contact.getGender());
        list.add(contact.getAge());
        list.add(contact.getTel());
        list.add(contact.getQq());
        list.add(contact.getEmail());


        paramsValue =  list.toArray();

        super.updateCurrent(sql, paramsValue);

    }

    @Override
    public Contact findContact(int id) {
        sql = "select * from contact_system where id = ?";

        paramsValue = new Object[] {id};

        // 下面使用反射思想
        List<Contact> list = super.inquiryCurrent(sql, paramsValue,Contact.class);

        // list链表不为空则返回第一个值，否则返回空
        return (list != null && list.size() != 0) ? list.get(0) : null;
    }

    @Override
    public List<Contact> findAllContact() {
        sql = "select * from contact_system";
        List<Contact> list = super.inquiryCurrent(sql, paramsValue, Contact.class);
        return (list != null && list.size() != 0) ? list : null;
    }
}
