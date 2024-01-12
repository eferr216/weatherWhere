<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul>
        <li><a href="index.jsp">Check Weather</a></li>
        <c:choose>
            <c:when test="${empty userName}">
                <li><a href="logIn">Log In</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="searchItem?link=clothing">Clothes</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>