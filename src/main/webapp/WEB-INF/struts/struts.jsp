<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Struts 2 hello world example</title>
        <s:head/>
    </head>

    <body>
        <h1><s:text name="welcome"/></h1>
        <s:if test="hasActionErrors()">
            <div id="fieldErrors">
                <s:actionerror/>
            </div>
        </s:if>
        <s:form action="testAction" namespace="/" method="post" name="myForm" theme="xhtml">
            <s:textfield name="name" size="40" maxlength="40" key="your.message-label"/>
            <s:submit key="submit" />
        </s:form>
    </body>
</html>