package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//从前端接受到数据
		req.setCharacterEncoding("utf-8");

		String user = req.getParameter("user");
		String password = req.getParameter("password");

		//模拟登陆成功
		if (user.equals("匿名君") && password.equals("123456")) {
			//创建新的session
			HttpSession session = req.getSession(false);
			//如果不存在session
			if (session == null) {
				session = req.getSession();
				//Tomcat服务器默认的Cookie的name属性为 JSESSIONID 这个Cookie保存的是sessionID
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				//设置Cookie的有效时间
				cookie.setMaxAge(60 * 30);
				//发送给浏览器保存
				resp.addCookie(cookie);

				//将用户名存在session对象中
				session.setAttribute("user", user);

				//获取整个WEB项目的ServletContext对象
				ServletContext servletContext = this.getServletContext();

				//把Session放到到WEB项目的ServletContext中
				//把SessionID号作为唯一索引
				//sessionIDXXXXXXXXXXXXXXXXXXXX
				servletContext.setAttribute("sessionID" + session.getId(), session);
			}
			resp.sendRedirect(req.getContextPath() + "/myIndex.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
