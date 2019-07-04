package Main;

import Interfaces.PlayerInterface;
import Model.EndGameStatistics;
import Model.PlayerAI;
import Model.PlayerWeb;
import Model.Statistics;
import Timer.ConnectionTimer;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

public class Main
{
    private static Main instance;
    private LinkedHashMap<String,PlayerWeb> listOfPlayers =new LinkedHashMap<>();
    private LinkedHashMap<PlayerWeb,Session> listOfSessions =new LinkedHashMap<>();
    private LinkedHashMap<PlayerWeb, Statistics> listOfStatistics=new LinkedHashMap<>();
    private ArrayList<PlayerWeb> gameWaitingLine=new ArrayList<>();
    private ArrayList<Game> listOfGames=new ArrayList<>();

    Timer connectionTimer =new Timer(true);
    TimerTask multiplayerConnector =new ConnectionTimer();


    private Main()
    {
        connectionTimer.scheduleAtFixedRate(multiplayerConnector,0,5*1000);
    }

    public void addSession(Session newSession, String nickname)
    {
        listOfSessions.put(listOfPlayers.get(nickname),newSession);
    }

    public void addPlayer(PlayerWeb player, String playerName)
    {
        listOfPlayers.put(playerName,player);
        listOfStatistics.put(listOfPlayers.get(playerName),new Statistics());
        System.out.println(listOfStatistics.get(listOfPlayers.get(playerName)));
    }

    public void addGame(PlayerInterface player1, PlayerInterface player2)
    {
        Game newGame=new Game();
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        listOfGames.add(newGame);
    }


    public void initGame(PlayerWeb player1, PlayerWeb player2, String turn)
    {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "start_game")
                .add("player1", player1.getName()+"("+player1.getGameSymbol()+")")
                .add("player2", player2.getName()+"("+player2.getGameSymbol()+")")
                .add("turn", turn)
                .build();
        try
        {
            listOfSessions.get(player1).getBasicRemote().sendText(addMessage.toString());
        }
        catch (IOException ex)
        {
            System.out.println("Err");//
        }

    }

    public void initBotGame(String playerName)
    {
        PlayerInterface botPlayer=new PlayerAI();
        botPlayer.setName("BotPlayer");

        initSigns(listOfPlayers.get(playerName),botPlayer);
        addGame(listOfPlayers.get(playerName), botPlayer);

        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "start_game")
                .add("player1", playerName+"("+listOfPlayers.get(playerName).getGameSymbol()+")")
                .add("player2", botPlayer.getName()+"("+botPlayer.getGameSymbol()+")")
                .add("turn", "Your turn: ")
                .build();
        try
        {
            System.out.println(playerName);
            listOfSessions.get(listOfPlayers.get(playerName)).getBasicRemote().sendText(addMessage.toString());
        }
        catch (IOException ex)
        {
            System.out.println("Err");//
        }
    }

    public void deleteGame()
    {


    }

    public void initSigns(PlayerInterface player1, PlayerInterface player2)
    {
       player1.setGameSymbol('X');
       player2.setGameSymbol('O');
    }

    public static synchronized Main getInstance()
    {
        if(instance==null)
        {
            instance=new Main();
        }

        return instance;
    }

    public void addToLine(String sender)
    {
        gameWaitingLine.add(listOfPlayers.get(sender));
    }
    public void removeFromLine(String sender)
    {
        gameWaitingLine.remove(listOfPlayers.get(sender));
    }

    public ArrayList<PlayerWeb> getGameWaitingLine()
    {
        return gameWaitingLine;
    }

    public LinkedHashMap<PlayerWeb, Session> getListOfSessions()
    {
        return listOfSessions;
    }

    public void addToStatistics(String playerName, EndGameStatistics endGameStatistics, String gameResult)
    {
        if(listOfStatistics.get(listOfPlayers.get(playerName))!=null)
        {
            listOfStatistics.get(listOfPlayers.get(playerName)).addMainStatisticsInfo(gameResult);
            listOfStatistics.get(listOfPlayers.get(playerName)).addLastGameInfo(endGameStatistics);
        }
    }

   // public void
    public void sendInputErrorToPlayer(String playerName)
    {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "wrong_input")
                .build();
        try
        {
            listOfSessions.get(listOfPlayers.get(playerName)).getBasicRemote().sendText(addMessage.toString());
        }
        catch (IOException ex)
        {
            System.out.println("Err");//
        }
    }
    public void sendStatistics(String playerName)
    {
        String[] lastGameResult=listOfStatistics.get(listOfPlayers.get(playerName)).getLastGameInfo();
        int[] mainStatistics=listOfStatistics.get(listOfPlayers.get(playerName)).getMainStatisticsInfo();

        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "statistics")
                .add("victories",mainStatistics[0])
                .add("defeats",mainStatistics[1])
                .add("draws",mainStatistics[2])
                .add("coefficient", mainStatistics[3])
                .add("participants",lastGameResult[0]+", "+lastGameResult[1] )
                .add("gameresult", lastGameResult[2])
                .add("gamewinner", lastGameResult[3])
                .build();
        try
        {
            listOfSessions.get(listOfPlayers.get(playerName)).getBasicRemote().sendText(addMessage.toString());
        }
        catch (IOException ex)
        {
        }
    }

    public void sendMessageToPlayers(ArrayList<PlayerInterface> listOfPlayers, int[] coordinates, char currentSymbol, int counter/*JsonObject message*/)
    {
        for(int z=0;z<listOfPlayers.size();z++)
        {
            if(listOfSessions.get(listOfPlayers.get(z))!=null)
            {
                String turn;
                if(z==counter)
                {
                    turn="Opponent turn:";
                }
                else
                {
                    turn="Your turn:";
                }
                JsonProvider provider = JsonProvider.provider();
                JsonObject addMessage = provider.createObjectBuilder()
                        .add("action", "display_result")
                        .add("x",coordinates[0])
                        .add("y",coordinates[1])
                        .add("result",currentSymbol)
                        .add("turn", turn)
                        .build();
                try
                {
                    listOfSessions.get(listOfPlayers.get(z)).getBasicRemote().sendText(addMessage.toString());
                }
                catch (IOException ex)
                {
                    System.out.println("Err");//
                }
            }

        }
    }

    public void sendEndOfTheGameToPlayers(ArrayList<PlayerInterface> listOfCurrentPlayers, int counter)
    {
        for(int z=0;z<listOfCurrentPlayers.size();z++)
        {
            if(listOfSessions.get(listOfCurrentPlayers.get(z))!=null)
            {
                String[] result=listOfStatistics.get(listOfPlayers.get(listOfCurrentPlayers.get(z).getName())).getLastGameInfo();
                String gameStatus;

                if(counter==z)
                {
                    gameStatus="Victory!";
                }
                else
                {
                    gameStatus="Defeat...";
                }

                JsonProvider provider=JsonProvider.provider();
                JsonObject endGameMessage = provider.createObjectBuilder()
                        .add("action", "end_game")
                        .add("gamestatus", gameStatus)
                        .add("participants",result[0]+", "+result[1] )
                        .add("gameresult", result[2])
                        .add("gamewinner", result[3])
                        .build();
                try
                {
                    listOfSessions.get(listOfCurrentPlayers.get(z)).getBasicRemote().sendText(endGameMessage.toString());
                }
                catch (IOException ex)
                {
                    System.out.println("Err");//
                }
            }

        }
    }


    public synchronized void makeTurn(int[] coordinates,String sender)
    {
       for (Game game : listOfGames)
       {
           if(game.getListOfPlayers().get(0).getName().equals(sender)
                   || game.getListOfPlayers().get(1).getName().equals(sender))
           {
               boolean isGameEnded=game.makeMove(coordinates,sender);

               if(isGameEnded==true)
               {
                   System.out.println("xxxxxxxxxxxxxxxxxxx");//
                   listOfGames.remove(game);
                   break;
               }
           }
       }
    }



}
