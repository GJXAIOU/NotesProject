package gjxaiou;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-15-20:30
 */
@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求参数
        String userId = req.getParameter("userId");
        String roomId = req.getParameter("roomId");
        System.out.println(userId);
        System.out.println(roomId);
        // 2.根据请求操作数据库

        // 3.把结果告知客户端
        // 向浏览器输出，输出什么，浏览器就接收到什么； 1表示成功
        resp.getWriter().print("1");
    }
}
