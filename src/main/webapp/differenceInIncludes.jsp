<%@page language="java" contentType="text/html; UTF-8" %>
<html>

<head>
    <title>Разница между jsp действием и директивой</title>
</head>

<%@include file="includable.jsp"%>

<hr/>

<jsp:include page="includable.jsp">
    <jsp:param name="address" value="Russia, Samara"/>
    <jsp:param name="type" value='<%= request.getParameter("type")%>'/>
    <jsp:param name="postIndex" value='<%= request.getParameter("postIndex")%>'/>
</jsp:include>

</body>
</html>