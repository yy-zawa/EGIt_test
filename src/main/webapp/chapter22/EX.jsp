<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="result" value="${Math.random()}" />
<fmt:formatNumber value="${result}" pattern="0.00" var="result"/>


抽選結果：
<c:if test="${result>=0.65}">
	<p style="display:inline">あたり！</p><br>
</c:if>

<c:if test="${result<0.65}">
	<p style="display:inline">はずれ！</p><br>
</c:if>

result：
<c:out value="${result}" />
<br>


<c:url value="/login.jsp" var="loginUrl" />
<a href="${loginUrl}">Login</a>


<c:catch var="exception">
     <% int a=2, b=0, c=a/b; %>
</c:catch>

<c:if test="${not empty exception}">
    <p>例外が発生しました: ${exception.message}</p>
</c:if>


<%@include file="../footer.html" %>
