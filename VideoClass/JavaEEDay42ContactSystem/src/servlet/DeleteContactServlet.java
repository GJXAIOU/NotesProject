package servlet;

import dao.impl.ContactDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-09-11:23
 */
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过URL，获取id
        Integer id = Integer.valueOf(req.getParameter("id"));

        // 获取contact对象
        ContactDaoImplement contactDaoImplement = new ContactDaoImplement();

        contactDaoImplement.deleteContact(id);

        // 删除之后，通过重定向回到主页
        resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
