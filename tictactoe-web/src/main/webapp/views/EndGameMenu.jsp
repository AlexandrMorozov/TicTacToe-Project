<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 04.07.2019
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tic-tac-toe</title>
    <script src="/js/clientwebsocket.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body onload="OnEndGameMenuLoad()">
<div>
    <div>
        <h2 id="gamestatus"></h2>
    </div>
    <div>
        Last game participants: <b id="lastgameparticipants"></b>
    </div>
    <div>
        Last game result :<b id="lastgameresult"></b>
    </div>
    <div>
        Last game winner :<b id="lastgamewinner"></b>
    </div>
    <div>
        <form method="POST" action="menu">
            <input type="submit" value="Return to menu">
        </form>
    </div>

</div>
</body>
</html>
