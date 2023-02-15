<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${locale == 'ua'}">
        <fmt:setLocale value="ua"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<html>
<body>
<div class="main_page">

    <form action="/Testing/register.jsp">
        <button class = "index_buttons" type="submit">
            Register
        </button>
    </form>

    <form action="/Testing/login.jsp">
        <button class = "index_buttons" type="submit">
            Login
        </button>
    </form>

</div>
</body>
</html>
