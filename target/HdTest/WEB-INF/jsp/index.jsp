<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/1
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>indexJsp</title>
</head>
<body>
<h1>index</h1>
sessionId:<%=request.getSession().getId()%>,
loginName:<%=request.getSession().getAttribute("loginUser")%>
</body>
</html>
