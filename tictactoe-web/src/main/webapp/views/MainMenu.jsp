<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tic-tac-toe</title>
    <script src="/js/clientwebsocket.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div>
    <div>
        <input type="submit" value="Game with player" onclick="sendGameRequest()">
    </div>
    <div>
        <input type="button" value="Game with bot" onclick="startGameWithBot()">
    </div>
    <div>
        <input type="button" value="Statistics" onclick="toStatistics()">
    </div>
</div>
</body>
</html>
