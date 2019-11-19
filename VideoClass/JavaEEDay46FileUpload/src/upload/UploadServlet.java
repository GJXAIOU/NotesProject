package upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author GJXAIOU
 * @create 2019-08-13-19:26
 */
@WebServlet("upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 添加：判断前端页面中的form表单中是否有属性：enctype= " XXXX";因为没有会报异常
        if (ServletFileUpload.isMultipartContent(req)) {
            // 带有该属性
            // 1.对请求进行解析，读取文件

            // 用于设置缓冲区大小（单位为字节），和临时存储目录
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 设置缓冲区大小
            diskFileItemFactory.setSizeThreshold(2 * 1024);

            // 设置临时目录，一般将临时目录和最终保存文件目录放在一起
            File file = new File("E:");
            if (!file.exists()){
                file.mkdir();
            }
            diskFileItemFactory.setRepository(file);

            // 这里读取文件不再是：req.getParamter，而是使用fileupload(),因为getparamter的返回值为string
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            // 设置上传文件最大大小
            // 设置上传的单文件最大值
            servletFileUpload.setFileSizeMax(1024*1024*5);
            // 设置整个表单能够上传的文件最大值（可能有多个文件）,这里以10兆为例
            servletFileUpload.setSizeMax(1024 *1024 *10);

            // 参数为请求，作用是解析请求，并且将请求的数据封装成一个集合返回（因为可能有多文件上传），泛型是FileItem
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);

                // 遍历集合，获取到数据
                for (FileItem item : fileItems) {

                    // 当表单中既有文本又有文件时候，要分开处理
                    if (item.isFormField()) {
                        // 表示当前数据是一个普通文本

                        // 获取文本内容即可(同时防止中文乱码)
                        String string = item.getString("UTF-8");
                        System.out.println(string);
                    }else {
                        // 当前Item是一个非文本的文件

                        // 2.把读取的文件对象保存到服务器本地磁盘中
                        // 在本地文件夹转给你新建一个文件，以上传文件名保存:item.getName()
                        // 为了保证存储的文件名唯一，防止被覆盖，使用UUID，这是用于生成随机的唯一字符的api
                        String newFileName = UUID.randomUUID().toString().replace("-", "");
                        // 还要获取原来文件的后缀名（取.之后的字符）
                        String name = item.getName();
                        String substring = name.substring(name.lastIndexOf("."));

                        // 为了让其他用户可以访问（下载），将文件保存在web项目资源目录下面：
                        // 这里获取到的不是代码的工作区间路径，是项目部署到Tomcat之后在Tomcat里的路径
                        String realPath = req.getServletContext().getRealPath("");

                        File file1 = new File(realPath+ newFileName + substring);
                        item.write(file1);
                    }

                }
                // 跳转到成功页面
                resp.sendRedirect("success.html");

            } catch (FileUploadException e) {
                // 跳转到错误页面
                resp.sendRedirect("error.html");
                e.printStackTrace();
            } catch (Exception e) {
                // 跳转到错误页面
                resp.sendRedirect("error.html");
                e.printStackTrace();
            }
        } else {
            // 跳转到错误页面
            resp.sendRedirect("error.html");
        }
    }
}
