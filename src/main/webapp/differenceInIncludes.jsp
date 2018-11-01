<%@ page import="java.util.Objects" %>
<%@page language="java" contentType="text/html; UTF-8" %>
<html>

<head>
    <title>Разница между jsp действием и директивой</title>
</head>

<%@include file="includable.jsp"%>

<hr/>

<jsp:include page="includable.jsp">
    <jsp:param name="address" value="Russia, Samara"/>
    <jsp:param name="type" value='<%= Objects.toString(request.getParameter("type"), "") %>'/>
    <jsp:param name="postIndex" value='<%= Objects.toString(request.getParameter("postIndex"), "1") %>'/>
</jsp:include>

</body>
</html>