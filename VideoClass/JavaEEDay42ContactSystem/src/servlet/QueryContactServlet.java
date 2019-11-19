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
 * @create 2019-08-09-11:24
 */
public class QueryContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收id
        Integer id = Integer.valueOf(req.getParameter("id"));

        // 2.查询到这个联系人的数据
        ContactDaoImplement contactDaoImplement = new ContactDaoImplement();
        Contact contact = contactDaoImplement.findContact(id);

        if (contact != null) {
            resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
            return;
        }

        // 3.把查询到的数据展示到修改页面
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text = html; charset = utf-8");

        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
        html += "<title>修改联系人</title>";
        html += "</head>";
        html += "<body>";
        html += "<form action=UpdateContactServlet?id="+ contact.getId() +" method=POST>";
        html += "<table align=center width=400 border=1px>";
        html += "<tr><th colspan=2>修改联系人</th></tr>";
        html += "<tr><td>姓名</td>"+
                "<td><input type=text name=name value="+ contact.getName() +"></td></tr>";

        // 将数据显示在文本框中
        if (contact.getGender().equals("M")) {
            html += "<tr><td>性别</td>" +
                    "<td><input type=radio name=gender value=M checked>男" +
                    "<input type=radio name=gender value=F>女</td></tr>";
        } else{
            html += "<tr><td>性别</td>" +
                    "<td><input type=radio name=gender value=M>男" +
                    "<input type=radio name=gender value=F checked>女</td></tr>";
        }

        html += "<tr><td>年龄</td>" +
                "<td><input type=text name=age value=" + contact.getAge() + "></td></tr>";
        html += "<tr><td>电话</td>" +
                "<td><input type=text name=tel value=" + contact.getTel() + "></td></tr>";
        html += "<tr><td>QQ</td>" +
                "<td><input type=text name=qq value=" + contact.getQq() + "></td></tr>";
        html += "<tr><td>email</td>" +
                "<td><input type=text name=email value=" + contact.getEmail() + "></td></tr>";
        html += "<tr><th colspan=2><input type=submit value=提交></th></tr>";
        html += "</table>";
        html += "</form>";
        html += "</body>";
        html += "</html>";

        resp.getWriter().write(html);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
