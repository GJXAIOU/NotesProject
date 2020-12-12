package gjxaiou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastjsonSockJsMessageCodec;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author GJXAIOU
 * @create 2019-08-16-15:40
 */
@WebServlet("/getCity")
public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取前端传递过来的选中的省的值
        String s1value = req.getParameter("s1value");

        // 2.根据省组装市
        // 如果没有数据库，将js里面的判断语句挪到这里即可；
        // 使用json传递数据
        ArrayList<City> cities = new ArrayList<>();

        if (s1value == "北京"){
            cities.add(new City(1, "东城"));
            cities.add(new City(2, "西城"));
        }
        if (s1value == "江苏"){
            cities.add(new City(1, "南京"));
            cities.add(new City(2, "宿迁"));
            cities.add(new City(3, "连云港"));
            cities.add(new City(4, "徐州"));

        }
        if (s1value == "上海"){
            cities.add(new City(1, "黄浦"));
            cities.add(new City(2, "浦东"));
        }

        // 3.最后将市的数据传递给前端
        String toJSONString = JSON.toJSONString(cities);
        System.out.println(toJSONString);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(toJSONString);


    }
}
