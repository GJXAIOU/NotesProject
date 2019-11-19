<%--
  Created by IntelliJ IDEA.
  User: GJXAIOU
  Date: 2019/8/14
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%--  datas :是一个list集合  datas[0] 即第一个IDinfo对象;--%>

  <a href="upload.html">继续添加新的信息</a>

  <ol>
      <%--使用增强for循环取出作用域汇总保存的集合对象--%>
      <%--  items指向的需要循环的集合，是一个el; var表示提供给循环体中的变量--%>
      <c:forEach items="${datas}" var="idinfo">
          <%--    src含义 :可以采用绝对路径或者相对路径，因为这里上传图片之后直接保存在服务器的根目录下，可以使用相对路径--%>
          <li>正面：
              <%--  访问Servlet并且传递当前点击的图片的名称--%>
              <a href="download?name=&{idinfo.front}">
                  <img width="150px" height="200px" src="E:\\Program\\Java\\Project\\VideoClass\\JavaEEDay47Icard\\out\\artifacts" +
                  "\\JavaEEDay47Icard_war_exploded/${idinfo.front}" />
              </a>

              反面：
              <a href="download?name=&{idinfo.back}">
                  <img width="150px" height="200px" src="http://localhost:8080/JavaEEDay47Icard_war_exploded/${idinfo.back}" />
              </a>
              身份证号码：${idinfo.idnumber} <br>
          </li>

      </c:forEach>

  </ol>
  </body>
</html>
