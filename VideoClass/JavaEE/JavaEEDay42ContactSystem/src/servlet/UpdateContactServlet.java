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
 * @create 2019-08-09-11:23
 */
public class UpdateContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Contact contact = new Contact();
        contact.setId(Integer.valueOf(req.getParameter("id")));
        contact.setName("name");
        contact.setGender("gender");
        contact.setAge(Integer.valueOf("id"));
        contact.setTel("tel");
        contact.setQq("qq");
        contact.setEmail("email");

        ContactDaoImplement contactDaoImplement = new ContactDaoImplement();
        contactDaoImplement.updateContact(contact);
        resp.sendRedirect(req.getContextPath() + "/ListContactServlet");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
