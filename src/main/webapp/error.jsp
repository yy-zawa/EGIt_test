<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラーが発生しました</title>
</head>
<body>
    <h1>エラーが発生しました</h1>
    <p>エラーメッセージ: <%= exception.getMessage() %></p>
    <p>例外のクラス: <%= exception.getClass().getName() %></p>

    <h3>スタックトレース:</h3>
    <pre>
        <% for (StackTraceElement element : exception.getStackTrace()) { %>
            <%= element.toString() %><br>
        <% } %>
    </pre>
</body>
</html>
