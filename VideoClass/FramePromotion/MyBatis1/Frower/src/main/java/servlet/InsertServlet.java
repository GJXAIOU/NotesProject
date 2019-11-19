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

/**
 * @author GJXAIOU
 * @create 2019-09-12-20:52
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    private FlowerService flowerService = new FlowerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String production = req.getParameter("production");

        Flower flower = new Flower();
        flower.setName(name);
        flower.setPrice(Double.parseDouble(price));
        flower.setProduction(production);

        try {
            int index = flowerService.add(flower);
            if (index > 0){
                resp.sendRedirect("show");
            }else {
                resp.sendRedirect("add.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
