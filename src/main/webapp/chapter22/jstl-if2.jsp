<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:forEach var="i" begin="100" end="199">
	<c:choose>
		<c:when test="${i%10==0}">(${i},</c:when>
		<c:when test="${i%10==9}">${i})<br></c:when>
		<c:otherwise>${i},</c:otherwise>
	</c:choose>
</c:forEach>

<%@include file="../footer.html" %>
