<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Expression Language Example</title>
</head>
<body>
<%
    List<String> names = new ArrayList<>();
    names.add("Vitalii Ivanov");
    names.add("Petr Petrov");
    pageContext.setAttribute("names", names);
%>
<strong>Simple EL Example:</strong> ${requestScope.person}
<br><br>
<strong>Simple EL Example without scope:</strong> ${person}
<br><br>
<strong>Simple Example:</strong> ${applicationScope["User.Cookie"]}
<br><br>
<strong>Multiples EL Example:</strong> ${sessionScope.employee.address.address}
<br><br>
<strong>List EL Example:</strong> ${names[1]}
<br><br>
<strong>Header information EL Example:</strong> ${header["Accept-Encoding"]}
<br><br>
<strong>Cookie EL Example:</strong> ${cookie["User.Cookie"].value}
<br><br>
<strong>PageContext EL Example:</strong> HTTP Method is ${pageContext.request.method}
<br><br>
<strong>Context param EL Example:</strong> ${initParam.AppID}
<br><br>
<strong>Arithmetic Operator EL Example:</strong> ${initParam.AppID + 200}
<br><br>
<strong>Relational Operator EL Example:</strong> ${initParam.AppID < 200}
<br><br>
<strong>Arithmetic Operator EL Example:</strong> ${initParam.AppID + 200}
<br><br>

</body>
</html>