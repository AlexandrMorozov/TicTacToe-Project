package Logic;

import Interfaces.PlayerInterface;
import Model.GameField;

import java.util.ArrayList;

public class GameSession
{
    private GameField gameField;
    private ArrayList<PlayerInterface> listOfPlayers;


    public void initializePlayers()
    {

    }

    public void gameProcess()
    {
        boolean isVictory=false;

        while (isVictory==false)
        {

            for(int i=0;i<listOfPlayers.size();i++)
            {

                char currentSymbol=listOfPlayers.get(i).getGameSymbol();
                if(diagonalVictoryChecking(currentSymbol,gameField.getGameTiles()) || hvVictoryChecking(currentSymbol,gameField.getGameTiles()))
                {
                    isVictory=true;
                }
            }
        }
    }

   /* private int victoryConditionsCalculation()
    {
        char[][] fieldOfGame=gameField.getGameTiles();

        for(int i=0;i<listOfPlayers.size();i++)
        {
            char currentSymbol=listOfPlayers.get(i).getGameSymbol();
            if(diagonalVictoryChecking(currentSymbol,fieldOfGame) || hvVictoryChecking(currentSymbol,fieldOfGame))
            {
                return i;
            }
        }

    }*/
    private boolean diagonalVictoryChecking(char currentSymbol, char[][] fieldOfGame)
    {

        if((fieldOfGame[0][0]==currentSymbol && fieldOfGame[1][1]==currentSymbol && fieldOfGame[2][2]==currentSymbol)
                || (fieldOfGame[0][2]==currentSymbol && fieldOfGame[1][1]==currentSymbol && fieldOfGame[2][0]==currentSymbol))
        {
            return true;
        }
        return false;
    }
    private boolean hvVictoryChecking(char currentSymbol, char[][] fieldOfGame)//Проверка победы по вертикали и горизонтали
    {
        int[] verticalVictoryCounters=new int[3];
        for(int m=0;m<fieldOfGame.length;m++)
        {
            int horizontalVictoryCounter=0;

            for(int n=0;n<fieldOfGame[m].length;n++)
            {
                if(fieldOfGame[m][n]==currentSymbol)
                {
                    horizontalVictoryCounter++;
                    verticalVictoryCounters[n]++;
                }
            }

            if(horizontalVictoryCounter==3)
            {
                return true;
            }
        }
        for(int a=0;a<verticalVictoryCounters.length;a++)//Можно вынести в отдельный метод
        {
            if(verticalVictoryCounters[a]==3)
            {
                return true;
            }
        }
        return false;

    }

}
