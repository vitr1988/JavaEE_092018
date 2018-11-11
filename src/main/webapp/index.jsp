<html>
<body>
<h2>Hello World!</h2>

<form action="<%= request.getContextPath() %>/jdbc" method="POST">
    <input type="submit" value="Click me">
</form>

<a href="<%= request.getContextPath() %>/html/page.html">Go to web-page</a>
<a href="<%= request.getContextPath() %>/Application.html">Go to GWT</a>
<a href="<%= request.getContextPath() %>/struts.action">Go to Struts</a>

</body>
</html>
