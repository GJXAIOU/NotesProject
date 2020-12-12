package gjxaiou;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-16-14:36
 */
@WebServlet("/checkuser")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if ("admin".equals(name)) {
            // 返回1表示该用户名已经存在
            resp.getWriter().print(1);
        }else {
            resp.getWriter().print(2);
        }
    }
}
