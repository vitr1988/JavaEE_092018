<#assign r=JspTaglibs["/WEB-INF/randomtag.tld"]>
<#assign text="World">

<html>
<body>

<h1>Hello, ${user!"Vasya"}!</h1>
<h2>Hello, ${text!"Vasya"}!</h2>

Random generated value is <@r.random size=6 threshold=10000 />!<br/>
${ 2 + 2 }<br/>
<#if foo>
  Do this<br/>
</#if>

<#if x == 1>
  Do this again<br/>
<#else>
  Do that<br/>
</#if>

Bar: ${bar?trim}<br/>

<p>These are our employees:
<ul>
    <#list persons as person>
        <li>${person.name}</li>
    </#list>
</ul>
</body>
</html>