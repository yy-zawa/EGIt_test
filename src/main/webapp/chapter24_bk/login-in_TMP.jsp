<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<form action="Login.action" method="post">

<!-- <p>ログイン名<input type="text" name="login"></p> -->

<!-- ログイン情報入力 -->
<p>
ログインID<input type="text" name="login" placeholder="090-1234-5678">
</p>
<p>
パスワード<input type="password" name="password">
</p>
<p>

<!-- ログインボタン -->
<input type="submit" value="ログイン">
</p>
</form>

<%@include file="../footer.html"%>
