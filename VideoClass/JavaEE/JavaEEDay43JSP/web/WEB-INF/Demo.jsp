<%--
  Created by IntelliJ IDEA.
  User: GJXAIOU
  Date: 2019/8/9
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP '02script.jsp' starting page</title>

</head>
<body>
    这里可以直接书写JSP！
<%--
 下面就是JSP脚本，在脚本中只能书写Java代码，
 并且这部分Java代码会出现在 _jspService方法中
 --%>
<%
    int num = 10;
    System.out.println(num);
%>
<%--
单个的JSP脚本中Java代码可以不完整，可以在多个脚本直接嵌套其他语言，
但是要求JSP脚本前后组合必须形成一个完成的Java语句。
--%>

<%
    for (int i = 0; i < 10; i++) {
%>

<h2>雷猴</h2>

<%
    }
%>



<%-- JSP表达式 --%>
<%--
    下面的语句相对于这三句：
        out.print(new Date() );
        out.print(name );
 --%>
<%=new Date() %>
<%=num %>



<%-- JSP声明 --%>
<%!
    //定义了一个成员变量
    private int aa = 10;

    //定义了一个方法
    public int add(int a, int b) {
        return a + b;
    }

    int sum = add(10, 12);
%>
<%
    System.out.println(aa);
    System.out.println(sum);
    System.out.println(add(20, 40));

%>

<%

        /*
        相对于是一个带有缓冲区的PrintWriter对象，
        如果想要JspWriter对象展示内容，
        展示在浏览器上有三种条件
        1.页面加载完成
        2.缓冲区已满
        3.调用flush方法
        注：缓冲区大小默认是8KB 是在page指令里面的buffer属性设置的
        */
        response.getWriter().write("Test PrintWriter");
        out.write("Test JspWriter Out");
        out.flush();

%>

    <%
        //可以获取其他内置对象
        pageContext.getRequest();
        pageContext.getResponse();
        pageContext.getServletContext();
        pageContext.getServletConfig();
        pageContext.getException();
        pageContext.getOut();
        pageContext.getPage();
    %>
    <%
        /*
        pageContext 是域对象
        pageContext.setAttribute(String name, Object value, int 域对象标号);
        第一个参数：属性名
        第二个参数：属性值
        第三个参数：域对象编号（给哪个域使用）
        */
        //request域
        pageContext.setAttribute("MSG", "request_msg",
                pageContext.REQUEST_SCOPE);

        //Session域
        pageContext.setAttribute("MSG", "session_msg",
                pageContext.SESSION_SCOPE);

        //整个WEB项目域对象 在JSP中叫Application 在Servlet称之为ServletContext
        pageContext.setAttribute("MSG", "application_msg",
                pageContext.APPLICATION_SCOPE);

        //当前页面page域对象，只在当前页面管用
        pageContext.setAttribute("MSG", "page_msg",
                pageContext.PAGE_SCOPE);

    %>

    <%
        /*
        从域对象中拿出数据，分别从各种域对象调用getAttribute(String name)方法
        */
        out.write("" + request.getAttribute("MSG") + "<br>");
        out.write("" + session.getAttribute("MSG") + "<br>");
        out.write("" + application.getAttribute("MSG") + "<br>");
        out.write("" + pageContext.getAttribute("MSG") + "<br>");

        //从域对象中查找属性 就近原则
        //pageContext -> request -> session -> appliction
        out.write("" + pageContext.findAttribute("MSG") + "<br>");
        // 获取request对象，因为06和07两个request，不能直接从06中获取07的request，，需要重定向
        request.getRequestDispatcher("07getattribute.jsp").forward(request, response);
    %>



</body>
</html>

