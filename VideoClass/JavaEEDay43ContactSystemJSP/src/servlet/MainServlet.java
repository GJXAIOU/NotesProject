package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//从ServletContext对象中获取到Session信息
		ServletContext servletContext = this.getServletContext();
		
		//获取ServletContext域中所有的属性名
		Enumeration<String> names = servletContext.getAttributeNames();
		
		HttpSession session = req.getSession(false);
		String sessionName = "sessionID" + session.getId();
		
		if (session != null) {
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				//System.out.println(name);
				if (name.equals(sessionName)) {
					HttpSession temp = (HttpSession) servletContext.getAttribute(name);
					System.out.println(temp.getAttribute("user"));
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
