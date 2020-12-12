<%--
  Created by IntelliJ IDEA.
  User: GJX
  Date: 2019/10/3
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>This is debug page</title>
</head>
<body>
    <s:debug></s:debug>
    <br>
    获取 userName: <s:property value="userName"></s:property>

    <br>
    获取对象的属性值：
    <br>
    username：<s:property value="user.username"/> <br>
    password：<s:property value="user.password"/><br>
    address： <s:property value="user.address"/> <br>

    <br>
    获取 List 集合：方式一：<br>
    <s:property value="userList[0].username"></s:property>
    <s:property value="userList[0].password"></s:property>
    <s:property value="userList[0].address"></s:property>
    <br>
    <s:property value="userList[1].username"></s:property>
    <s:property value="userList[1].password"></s:property>
    <s:property value="userList[1].address"></s:property>

    <br>

    获取 List 集合：方式二：<br>
    <s:iterator value="userList">
        <!--遍历 list 得到 list 里面每一个 user 对象-->
        <s:property value="username"></s:property>
        <s:property value="password"></s:property>
        <s:property value="address"></s:property>
        <br>
    </s:iterator>

    <br>
    获取 List 集合：方式三：<br>
    <s:iterator value="userList" var="user">
        <%--遍历值栈 List 集合，得到每一个 user 对象；
            机制：把每次遍历得到的 user 对象放在 context 里面(因为值是从 context 中取到的)，
            获取 context 里面数据使用 # 和 ognl 表达式；
        --%>
        <s:property value="#user.username"></s:property>
        <s:property value="#user.password"></s:property>
        <s:property value="#user.address"></s:property>
        <br>
    </s:iterator>



</body>
</html>
