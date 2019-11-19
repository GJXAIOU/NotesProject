package servlet;

import dao.impl.ContactDaoImplement;
import entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-09-11:24
 */
public class ListContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从数据库中拿出所有的数据
        ContactDaoImplement contactDaoImplement = new ContactDaoImplement();
        // 拿出所有的联系人信息，用list集合保存
        List<Contact> list = contactDaoImplement.findAllContact();

        // 2.设置页面表示展示数据方式和字符集，以及相应的字符编码
        resp.setContentType("text/html; charset = utf-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        String html = "";

        html += "<!doctype html>";
        html += "<html>";
        html += "<head>";
        html += "<title>显示所有联系人</title>";
        html += "</head>";
        html += "<body>";
        html += "<center><h3>所有联系人</h3></center>";
        html += "<table border='1' align='center' width='800px'>";
        html += "<tr>";
        html += "<th>编号</th><th>姓名</th><th>性别</th><th>年龄</th>"
                + "<th>电话</th><th>QQ</th><th>email</th><th>功能</th>";
        html += "</tr>";

        if (list != null) {
            for (Contact contact : list) {
                html += "<tr>";
                html += "<td>" + contact.getId() + "</td>";
                html += "<td>" + contact.getName() + "</td>";
                html += "<td>" + contact.getGender() + "</td>";
                html += "<td>" + contact.getAge() + "</td>";
                html += "<td>" + contact.getTel() + "</td>";
                html += "<td>" + contact.getQq() + "</td>";
                html += "<td>" + contact.getEmail() + "</td>";
                html += "<td> ";
                html += "<a href='" + req.getContextPath() +
                        "/DeleteContactServlet?id=" + contact.getId() +"'>删除</a>";
				/*
				 这里要借助于URL传递参数，传递的信息是当前要删除用户的ID号
				 在URL中最后用?隔开，之后表示参数 id = ?
				 */
                html += " | ";
                html += "<a href='" + req.getContextPath() +
                        "/QueryContactServlet?id=" + contact.getId() +"'>修改</a>";
                html += "</td>";
                html += "</tr>";
            }
        }

        html += "</table>";
        html += "<center><a href='" + req.getContextPath() +"/addContact.html'>【添加联系人】</a></center>";
        html += "</body>";
        html += "</html>";

        printWriter.write(html);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
