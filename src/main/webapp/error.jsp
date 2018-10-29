<%--
  Created by IntelliJ IDEA.
  User: ViTr
  Date: 29.10.2018
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error Handler</title>
</head>
<body>
    <em><%= exception.getLocalizedMessage() %></em>
</body>
</html>
