package gjxaiou;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author GJXAIOU
 * @create 2019-08-13-21:26
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数的name，表示当前的点击的图片
        String name = req.getParameter("name");


        // 1.首先将服务器上的某一个文件转换为File对象
        File file = new File("E:\\Program\\Java\\Project\\VideoClass\\JavaEEDay47Icard\\out\\artifacts" +
                "\\JavaEEDay47Icard_war_exploded/" + name);

        // 文件下载需要对响应头进行设置
        // 设置返回给客户端的数据类型,这是设置是可执行文件
        resp.setContentType("application/octet-stream");

        // 告诉浏览器需要下载的文件的名称
        // 编码：将中文转成Unicode字节码
        // 解码：将Unicode码转换成中文 ：URLDecoder.encode(file.getName(), "UTF-8"),这里浏览器会自动解码
        resp.addHeader("Content-Dispositon", "attachment;filename = " + URLEncoder.encode(file.getName(), "UTF-8"));

        // 用于向客户端发送文件；向客户端输出文本或者HTML源码使用：resp.getWriter().print()
        ServletOutputStream outputStream = resp.getOutputStream();

        // 将文件转换为流
        InputStream inputStream = new FileInputStream(file);
        outputStream.write(IOUtils.toByteArray(inputStream));

        inputStream.close();
        outputStream.close();


        // 2.把File通过响应传递给客户端
    }
}
