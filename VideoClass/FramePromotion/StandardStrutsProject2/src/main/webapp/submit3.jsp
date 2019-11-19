<%--
  Created by IntelliJ IDEA.
  User: GJX
  Date: 2019/9/30
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is submit.jsp</title>
</head>
<body>
    <form action = "${pageContext.request.contextPath}/form3.action" method="post">
        username: <input type="text" name="username"> <br>
        password: <input type="text" name="password"> <br>
        address:  <input type="text"  name="address"> <br>
        submit:   <input type="submit" name="提交">
    </form>

</body>
</html>
