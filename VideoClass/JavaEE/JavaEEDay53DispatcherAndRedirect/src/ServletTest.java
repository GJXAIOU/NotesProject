import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-22-16:19
 */
@WebServlet("/index")
public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 尝试使用转发访问百度 : 失败
        //req.getRequestDispatcher("http://www.baidu.com").forward(req, resp);

        // 尝试使用重定向跨域访问百度 ：成功
        resp.sendRedirect("https://www.baidu.com");

    }
}
