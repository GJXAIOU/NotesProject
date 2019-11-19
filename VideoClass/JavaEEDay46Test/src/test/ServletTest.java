package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-13-22:03
 */
// 这里在设置路径时候，尽量将Servlet和a.jsp同一目录（因为都转发到b.jsp）即：@WebServlet("/servlet")
@WebServlet("/main/servlet")
public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../a.jsp").forward(req, resp);
    }
}
