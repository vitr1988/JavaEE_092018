<#assign r=JspTaglibs["/WEB-INF/randomtag.tld"]>
<#assign text="World">

<html>
<body>

<h1>Hello, ${user!"Vasya"}!</h1>
<h2>Hello, ${text!"Vasya"}!</h2>

<#setting locale="en_US">
Current date : ${currentDate?string("dd.MM.yyyy")}<br/>
Number : ${digit?string("0.00")}<br/>
Other number : ${digit?string.currency}<br/>
Yet another number : ${digit?string.number}<br/>
Other number again : ${digit?string.percent}<br/>

<hr/>

<#setting locale="ru_RU">
Current date : ${currentDate?string("dd MMMM yyyy")}<br/>
Number : ${digit?string("0.00")}<br/>
Other number : ${digit?string.currency}<br/>
Yet another number : ${digit?string.number}<br/>
Other number again : ${digit?string.percent}<br/>

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