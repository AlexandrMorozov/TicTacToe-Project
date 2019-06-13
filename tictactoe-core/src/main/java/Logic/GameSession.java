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

    }

    private void victoryConditionsChecking()
    {
        int[][] victoryCounters=new int[2][4];//счётчики для каждойкомбинации
        char[][] fieldOfGame=gameField.getGameTiles();
        for(int j=0;j<fieldOfGame.length;j++)//подсчёт комбинаций
        {
            for (int k=0;k<fieldOfGame[j].length;k++)
            {
                if(fieldOfGame[j][k]==listOfPlayers.get(0).getGameSymbol())
                {
                    //victoryCounters[0]
                    victoryCounters[0]=changeCounter(j,k,victoryCounters[0]);
                }
                else if(fieldOfGame[j][k]==listOfPlayers.get(1).getGameSymbol())
                {
                    victoryCounters[1]=changeCounter(j,k,victoryCounters[1]);
                }
            }
        }
        for (int g=0;g<victoryCounters.length;g++)//проверка счётчиков
        {

        }


    }
    private int[] changeCounter(int j, int k,int[] playerCounter)
    {
        if((j==1 && k==0) || (j==2 && k==1) || (j==1 && k==2) || (j==0 && k==1))
        {
            playerCounter[0]++;
            playerCounter[1]++;
        }
        else if((j==0 && k==0) || (j==2 && k==2))
        {
            playerCounter[0]++;
            playerCounter[1]++;
            playerCounter[2]++;
        }
        else if((j==0 && k==2) || (j==2 && k==0))
        {
            playerCounter[0]++;
            playerCounter[1]++;
            playerCounter[3]++;
        }
        else if((j==1 && k==1))
        {
            playerCounter[0]++;
            playerCounter[1]++;
            playerCounter[2]++;
            playerCounter[3]++;
        }
        return playerCounter;
    }



}
