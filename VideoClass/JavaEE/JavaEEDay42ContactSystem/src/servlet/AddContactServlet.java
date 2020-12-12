package servlet;

import dao.impl.ContactDaoImplement;
import entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-09-11:22
 */
public class AddContactServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符集
        req.setCharacterEncoding("utf-8");

        // 1.提取数据
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        Integer age = Integer.valueOf(req.getParameter("age"));
        String tel = req.getParameter("tel");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");

        // 2.创建Contact对象
        Contact contact = new Contact();
        contact.setName(name);
        contact.setGender(gender);
        contact.setAge(age);
        contact.setTel(tel);
        contact.setQq(qq);
        contact.setEmail(email);

        // 3.调研Dao层将数据写入数据库
        ContactDaoImplement contactDaoImplement = new ContactDaoImplement();
        contactDaoImplement.addContact(contact);

        resp.sendRedirect(req.getContextPath() + "/ListContactServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
