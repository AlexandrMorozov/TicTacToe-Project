package Main;

import Interfaces.PlayerInterface;
import Logic.VictoryConditionsChecking;
import Model.EndGameStatistics;
import Model.GameField;
import java.util.ArrayList;

public class Game
{
    private GameField gameField=new GameField();
    private VictoryConditionsChecking victoryConditions=new VictoryConditionsChecking();
    private ArrayList<PlayerInterface> listOfPlayers=new ArrayList<>();
    private int currentPlayerTurn=0;
    private boolean endOfTheGame=false;


    public ArrayList<PlayerInterface> getListOfPlayers()
    {
        return listOfPlayers;
    }

    public void addPlayer(PlayerInterface player)
    {
        listOfPlayers.add(player);
    }


    public synchronized boolean makeMove(int[] coordinates,String sender)
    {
        char currentSymbol;
        for(int i=0;i<listOfPlayers.size();i++)
        {
            if(currentPlayerTurn==i && Main.getInstance().getListOfSessions().get(listOfPlayers.get(i))==null)
            {
                currentSymbol=listOfPlayers.get(i).getGameSymbol();
                coordinates=listOfPlayers.get(i).makeMove(gameField);

                Main.getInstance().sendMessageToPlayers(listOfPlayers,coordinates,currentSymbol,currentPlayerTurn);
                endOfTheGame=checkVictoryConditions(currentSymbol, currentPlayerTurn);

                if(currentPlayerTurn==0)
                {
                    currentPlayerTurn=1;
                }
                else
                {
                    currentPlayerTurn=0;
                }

            }

            if(currentPlayerTurn==i && listOfPlayers.get(i).getName().equals(sender))
            {
                currentSymbol=listOfPlayers.get(i).getGameSymbol();
                if(gameField.setTile(coordinates,listOfPlayers.get(i).getGameSymbol())==false)
                {
                    Main.getInstance().sendInputErrorToPlayer(listOfPlayers.get(i).getName());
                    return false;
                }
                else
                {
                    Main.getInstance().sendMessageToPlayers(listOfPlayers,coordinates,currentSymbol,currentPlayerTurn);
                    endOfTheGame=checkVictoryConditions(currentSymbol, currentPlayerTurn);
                }

                if(currentPlayerTurn==0)
                {
                    currentPlayerTurn=1;
                }
                else
                {
                    currentPlayerTurn=0;
                }
            }

            if(endOfTheGame==true)
            {
                return true;
            }

        }

       return false;
    }

    private boolean checkVictoryConditions(char currentSymbol, int counter)
    {
        String[] endGameData;

        if(victoryConditions.diagonalVictoryChecking(currentSymbol,gameField.getGameTiles()) ||
                victoryConditions.hvVictoryChecking(currentSymbol,gameField.getGameTiles()))
        {
            endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Victory of:"+ listOfPlayers.get(counter).getName(),listOfPlayers.get(counter).getName()};

            if(counter==0)
            {
                Main.getInstance().addToStatistics(listOfPlayers.get(0).getName(),new EndGameStatistics(endGameData),"victory");
                Main.getInstance().addToStatistics(listOfPlayers.get(1).getName(),new EndGameStatistics(endGameData),"defeat");
            }
            else
            {
                Main.getInstance().addToStatistics(listOfPlayers.get(1).getName(),new EndGameStatistics(endGameData),"victory");
                Main.getInstance().addToStatistics(listOfPlayers.get(0).getName(),new EndGameStatistics(endGameData),"defeat");
            }
            Main.getInstance().sendEndOfTheGameToPlayers(listOfPlayers, counter);
            return true;
        }
        else if(victoryConditions.drawChecking(gameField.getGameTiles()))
        {
            endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Draw","none"};

            Main.getInstance().addToStatistics(listOfPlayers.get(0).getName(),new EndGameStatistics(endGameData),"draw");
            Main.getInstance().addToStatistics(listOfPlayers.get(1).getName(),new EndGameStatistics(endGameData),"draw");

            Main.getInstance().sendEndOfTheGameToPlayers(listOfPlayers, counter);
            return true;
        }

        return false;
    }

}
