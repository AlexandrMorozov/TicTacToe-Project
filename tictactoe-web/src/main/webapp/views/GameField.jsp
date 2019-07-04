<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru-RU">
<head>
    <title>Tic-tac-toe</title>
    <script src="/js/clientwebsocket.js"></script>
    <link rel="stylesheet" href="/css/gamefieldstyles.css">
</head>
<body onload="OnGameFieldLoad()">
	<div id="wrapper">
    <div>
        <div class="krestiki_noliki">
            <b id="player1"></b><b> vs </b><b id="player2"></b>
            <br>
            <p class="result" id="turn"></p>
            <div class="block cell1" onclick="sendMove(0,0)"><b></b></div>
            <div class="block cell2" onclick="sendMove(0,1)"><b></b></div>
            <div class="block cell3" onclick="sendMove(0,2)"><b></b></div>
            <div class="block cell4" onclick="sendMove(1,0)"><b></b></div>
            <div class="block cell5" onclick="sendMove(1,1)"><b></b></div>
            <div class="block cell6" onclick="sendMove(1,2)"><b></b></div>
            <div class="block cell7" onclick="sendMove(2,0)"><b></b></div>
            <div class="block cell8" onclick="sendMove(2,1)"><b></b></div>
            <div class="block cell9" onclick="sendMove(2,2)"><b></b></div>
        </div>
    </div>
</div>

</body>
</html>
