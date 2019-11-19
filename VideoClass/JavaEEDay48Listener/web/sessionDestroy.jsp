<%--
  Created by IntelliJ IDEA.
  User: GJXAIOU
  Date: 2019/8/15
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        // 注销的时候可以主动调用销毁
        session.invalidate();
    %>
</body>
</html>
