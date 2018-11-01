<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rand" uri="/customtags" %>
<html>
<head>
    <title>Страница со сгенерированным значением</title>
</head>
<body>
    Случайно сгенерированное число: <rand:random size="6" threshold="<%= Integer.MAX_VALUE %>"/>
</body>
</html>
