package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<User> list = new ArrayList<User>();
		
		for (int i = 0; i < 20; i++) {
			list.add(new User(i + 1, "Jack" + i));
		}
		
		//把数据放入到request域对象中
		req.setAttribute("list", list);
		
		//转发到JSP文件中展示
		req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
