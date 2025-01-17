<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<p>検索キーワードを入力してください。</p>

<form action="search_practice" method="post">
<p>商品名</p>
<input type="text" name="keyword_name">
<input type="submit" value="検索">
</form>

<form action="search_practice" method="post">
<p>金額</p>
<input type="text" name="keyword_price">
<input type="submit" value="検索">
</form>

<%@include file="../footer.html" %>
