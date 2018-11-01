<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--
    Address bean = new Address();
    bean.setAddress("Moscow");
--%>
<jsp:useBean id="bean" class="ru.otus.jsp.model.Address" scope="application"/>

<jsp:setProperty name="bean" property="address" value="Moscow"/>
<jsp:setProperty name="bean" property="address" param="addr"/>

<jsp:setProperty name="bean" property="*"/>
<%--<jsp:setProperty name="bean" property="address" param="address"/>--%>

<jsp:getProperty name="bean" property="address"/>
<jsp:getProperty name="bean" property="type"/>
<jsp:getProperty name="bean" property="postIndex"/>