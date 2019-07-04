window.cookie =
    {
        get: function(sKey)
        {
            var sValue = '';
            var sKeyEq = sKey+ '=';
            var aCookies = document.cookie.split(';');

            for(var iCounter = 0, iCookieLength = aCookies.length; iCounter < iCookieLength; iCounter++)
            {
                while(aCookies[iCounter].charAt(0) === ' ')
                {
                    aCookies[iCounter] = aCookies[iCounter].substring(1);
                }
                if(aCookies[iCounter].indexOf(sKeyEq) === 0)
                {
                    sValue = aCookies[iCounter].substring(sKeyEq.length);
                }
            }

            return unescape(sValue);
        }

    };


var playerNameCookie = cookie.get("player");
var socket=new WebSocket("ws://localhost:8080/multiplayergame/"+playerNameCookie);
socket.onmessage = onMessage;

var gameRequestStatus=false;

function onMessage(event)
{
    var device = JSON.parse(event.data);

    if (device.action === "start_game")
    {
        document.location.href = "http://localhost:8080/views/GameField.jsp"+"?"
            +"player1="+device.player1+"&"+"player2="+device.player2+"&"+"turn="+device.turn;
    }
    if (device.action === "end_game")
    {
        document.location.href = "http://localhost:8080/views/EndGameMenu.jsp"+"?"
            +"gamestatus="+device.gamestatus+"&"+"participants="+device.participants+"&"+"gameresult="
            +device.gameresult+"&"+"gamewinner="+device.gamewinner;
    }
    if(device.action === "statistics")
    {
        console.log("qqqqq");
        document.location.href = "http://localhost:8080/views/StatisticsMenu.jsp"+"?"
            +"victories="+device.victories+"&"+"defeats="+device.defeats+"&"+"draws="
            +device.draws+"&"+"coefficient="+device.coefficient+"&"+ "participants="+device.participants
            +"&"+"gameresult=" +device.gameresult+"&"+"gamewinner="+device.gamewinner;
    }
    if (device.action === "display_result")
    {
        var coordinate=findCoordinate(device.x,device.y)
        coordinate="block cell"+coordinate;
       // console.log(device.turn+"  2");
        document.getElementById("turn").innerHTML =device.turn;
        document.getElementsByClassName(coordinate).item(0).innerHTML=String.fromCharCode(device.result);
    }
    if(device.action=="wrong_input")
    {
        alert("Wrong input! Try again")
    }


}

function sendGameRequest()
{
    if(gameRequestStatus==false)
    {

        var gameRequest={action: "game_request", sender: cookie.get("player")}
        socket.send(JSON.stringify(gameRequest));
        gameRequestStatus=true;
        alert("Start searching for players");
    }
    else
    {
        var gameRequest={action: "game_request_cancel", sender: cookie.get("player")}
        socket.send(JSON.stringify(gameRequest));
        gameRequestStatus=false;
        alert("Stop searching for players");
    }

}

function startGameWithBot()
{
   if(gameRequestStatus==true)
   {
       sendGameRequest();
   }

    var gameRequest={action: "game_bot", sender: cookie.get("player")}
    socket.send(JSON.stringify(gameRequest));
}

function toStatistics()
{
    if(gameRequestStatus==true)
    {
        sendGameRequest();
    }

    var statisticsRequest={action: "get_statistics", sender: cookie.get("player")}
    socket.send(JSON.stringify(statisticsRequest));
}

function sendMove(x,y)
{

    var coordinate=findCoordinate(x,y);
    coordinate="block cell"+coordinate;
    //console.log(document.getElementsByClassName(coordinate).item(0).innerHTML.toString());
    var d=document.getElementsByClassName(coordinate).item(0).innerHTML.toString();
    //console.log(d);
    if(document.getElementsByClassName(coordinate).item(0).innerHTML=="")
    {
        console.log(d);
    }
    else
    {
        console.log("mda")
    }

    if(d!=="X" || d!=="O")
    {
        var gameRequest={action: "make_move", sender: cookie.get("player"), x: x,y: y}
        socket.send(JSON.stringify(gameRequest));
    }
    else
    {
        alert("Wrong input!");
    }


}


function findCoordinate(x,y)
{
    var coordinate;
    if(x==0)
    {
        switch (y)
        {
            case 0: coordinate=1; break;
            case 1: coordinate=2; break;
            case 2: coordinate=3; break;
        }
    }
    else if(x==1)
    {
        switch (y)
        {
            case 0: coordinate=4; break;
            case 1: coordinate=5; break;
            case 2: coordinate=6; break;
        }
    }
    else if(x==2)
    {
        switch (y)
        {
            case 0: coordinate=7; break;
            case 1: coordinate=8; break;
            case 2: coordinate=9; break;
        }
    }
    return coordinate;
}

function OnGameFieldLoad()
{
    var query = window.location.href.split("?")[1];
    var params = query.split("&");
    for(var i=0;i<params.length;i++)
    {
        params[i]=params[i].replace("%20"," ");
    }
    document.getElementById("player1").innerHTML = params[0].split("=")[1];
    document.getElementById("player2").innerHTML = params[1].split("=")[1];
    document.getElementById("turn").innerHTML = params[2].split("=")[1];
}

function OnEndGameMenuLoad()
{
    var query = window.location.href.split("?")[1];
    var params = query.split("&");
    for(var i=0;i<params.length;i++)
    {
        params[i]=params[i].replace("%20"," ");
    }
    document.getElementById("gamestatus").innerHTML = params[0].split("=")[1];
    document.getElementById("lastgameparticipants").innerHTML = params[1].split("=")[1];
    document.getElementById("lastgameresult").innerHTML = params[2].split("=")[1];
    document.getElementById("lastgamewinner").innerHTML = params[3].split("=")[1];
}

function OnStatisticsMenuLoad()
{
    var query = window.location.href.split("?")[1];
    var params = query.split("&");
    for(var i=0;i<params.length;i++)
    {
        params[i]=params[i].replace("%20"," ");
    }
    document.getElementById("player").innerHTML = cookie.get("player");
    document.getElementById("victories").innerHTML = params[0].split("=")[1];
    document.getElementById("defeats").innerHTML = params[1].split("=")[1];
    document.getElementById("draws").innerHTML = params[2].split("=")[1];
    document.getElementById("coeff").innerHTML = params[3].split("=")[1];
    document.getElementById("lastgameparticipants").innerHTML = params[4].split("=")[1];
    document.getElementById("lastgameresult").innerHTML = params[5].split("=")[1];
    document.getElementById("lastgamewinner").innerHTML = params[6].split("=")[1];
}

