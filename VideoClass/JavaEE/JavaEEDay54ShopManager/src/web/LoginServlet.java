package web;

import dao.UserDaoInterface;
import dao.impl.UserDaoImpl;
import pojo.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-23-13:35
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDaoInterface dao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 查询判断
        List<Users> list = dao.getUsers(username, password);

        if (list.size() == 0 || list == null){
            // 登录失败返回原页面
            resp.getWriter().print("<script>window.history.back()</script>");
        }else {
            // 登录成功跳转到首页
            // 将用户信息保存到 session
            // 将读取查询到的结果集的第一条数据保存到session中
            req.getSession().setAttribute("user", list.get(0));
            // 同时将用户名和密码保存到cookie
            Cookie cookie1 = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            // 为 cookie 设置有效期
            cookie1.setMaxAge(1000*60*60*24);
            cookie2.setMaxAge(1000*60*60*24);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);

            // 跳转到首页
            resp.sendRedirect("index.html");

        }

   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);

    }


}
