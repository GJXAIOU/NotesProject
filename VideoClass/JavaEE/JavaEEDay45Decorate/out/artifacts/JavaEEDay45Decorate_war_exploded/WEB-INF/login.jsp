<%--
  Created by IntelliJ IDEA.
  User: GJXAIOU
  Date: 2019/8/12
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是登录页面</title>
</head>
<body>
    <%--这里点击登录离职后跳转到Servlet--%>
    <form action="login" method="post">
        请输入账号密码：
        <input type="text" name="username"/>
        <input type="text" name="password"/>
        <input type="submit" value="登录">


    </form>

</body>
</html>
