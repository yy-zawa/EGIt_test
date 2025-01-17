<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<%@page errorPage="total-error.jsp" %>

<%
request.setCharacterEncoding("UTF-8");
// int price=Integer.parseInt(request.getParameter("price"));
// int count=Integer.parseInt(request.getParameter("count"));

String priceParam = request.getParameter("price");
String countParam = request.getParameter("count");

if (priceParam == null || priceParam.equals("") || countParam == null || countParam.equals("")) {
	throw new NullPointerException("price または count のパラメータが null または空です。");
}
%>

<%=price %>円×<%=count %>個＝<%=price*count %>円

<%@include file="../footer.html" %>
