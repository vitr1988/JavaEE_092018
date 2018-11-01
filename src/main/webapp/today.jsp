<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="java.time.*, java.time.format.*" info="Test JSP" errorPage="error.jsp" %>
<%@ page import="static ru.otus.jsp.JspServlet.DATE_TIME_FORMAT" %>
<!DOCTYPE html>
<%!
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private String getFormattedDate() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Hello World -->
    <%-- Hello World --%>
    <%@include file="html/title.html" %>
    <% int ui = 5/0; %>
    <%= getServletInfo() %>
</head>
<body>
<i>Today is <% out.print(getFormattedDate()); %><%= getFormattedDate() %></i>
</body>
</html>
