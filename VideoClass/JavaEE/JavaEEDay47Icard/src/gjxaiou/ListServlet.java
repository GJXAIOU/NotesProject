package gjxaiou;

import dao.FileDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-14-21:07
 */
@WebServlet("/index")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.查询数据库读取数据
        FileDaoImpl fileDao = new FileDaoImpl();

        // 2.把数据存放到request作用域
        req.setAttribute("datas", fileDao.getAll());

        // 3.转发到jsp
        // 将这个jsp页面放在WEB-INF下，防止用于直接访问jsp页面，因为直接访问是没有数据的
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
