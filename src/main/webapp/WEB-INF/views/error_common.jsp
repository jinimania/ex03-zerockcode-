<%--
  @author LeeSoohoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>${exception.getMessage()}</h4>
<ul>
    <c:forEach items="${exception.getStackTrace()}" var="stack">
        <li>${stack.toString()}</li>
    </c:forEach>
</ul>

</body>
</html>
