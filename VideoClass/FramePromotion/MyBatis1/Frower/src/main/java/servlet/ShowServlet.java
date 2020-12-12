package servlet;

import pojo.Flower;
import service.FlowerService;
import service.impl.FlowerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-12-20:44
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private FlowerService flowerService = new FlowerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flower> list = null;
        try {
            list = flowerService.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
