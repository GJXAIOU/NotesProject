package gjxaiou;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-12-18:46
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取前端传送过来的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2.判断数据是否正确，可以采用数据库验证
        if ("gjxaiou".equals(username) && "123456".equals(password)){
            // 登录成功，跳转到首页
            // 这里使用重定向
            resp.sendRedirect("index.jsp");

            // 在回话中保存一个变量，来表示用户已经登录
            HttpSession session = req.getSession();
            // 在session中保存一个变量
            session.setAttribute("islogin", true);
        }else {
            // 登录失败，重新回到登录页面，即回到上一页面
            resp.getWriter().print("<script>window.history.back()</script>");
        }

        // 3. 根据判断结果进行页面跳转，见上；
    }
}
