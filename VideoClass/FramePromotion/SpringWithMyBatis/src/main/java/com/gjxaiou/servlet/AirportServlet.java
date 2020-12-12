package com.gjxaiou.servlet;

import com.gjxaiou.service.AirportService;
import com.gjxaiou.service.impl.AirportServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-09-23-18:42
 */
@WebServlet("/airport")
public class AirportServlet extends HttpServlet {
    private AirportService airportService;


    @Override
    public void init() throws ServletException {
        // 对 Service 进行实例化，Spring 和 Web 整合后所有信息都存放在 WebApplicationContext 中
        WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        airportService = requiredWebApplicationContext.getBean("airportService", AirportServiceImpl.class);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setAttribute("list", airportService.show());
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
