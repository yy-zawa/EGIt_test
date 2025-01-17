<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Greeting Output</title>
</head>
<body>
    <h1>ようこそ！</h1>
    <p>こんにちは、<strong><%= request.getParameter("user") %></strong>さん！</p>
</body>
</html>
