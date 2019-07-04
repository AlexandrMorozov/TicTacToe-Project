<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tic-tac-toe</title>
    <script src="/js/clientwebsocket.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body onload="OnStatisticsMenuLoad()">
<div>
    <h2 id="player"></h2><h2> statistics: </h2>
</div>
<div>
    Number of victories: <b id="victories"></b>
</div>
<div>
    Number of defeats :<b id="defeats"></b>
</div>
<div>
    Number of draws:<b id="draws"></b>
</div>
<div>
    Efficiency coefficient:<b id="coeff"></b>
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
</body>
</html>
