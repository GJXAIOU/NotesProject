package gjxaiou;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-15-21:38
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取用户名密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2.判断是否是正确的用户名密码匹配
        // 这里暂时不使用数据库
        if ("admin".equals(username) && "admin".equals(password)) {
            // 服务器返回1表示成功
            resp.getWriter().print(1);
        }else {
            resp.getWriter().print(0);
        }


        // 3.返回登录结果，不在这里做任何页面跳转,上面的返回1和0就是返回值
    }
}
