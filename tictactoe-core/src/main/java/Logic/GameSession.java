package Logic;

import Model.GameField;
import Model.Player;
import Model.PlayerAI;

import java.util.ArrayList;

public class GameSession
{
    private GameField gameField=new GameField();
    private ArrayList<Player> listOfPlayers=new ArrayList<Player>();
    InputOutputController ioControl;

    public GameSession(String[] playersNames,char[] playersSigns,boolean[] isBot,InputOutputController ioControl)
    {
        this.ioControl=ioControl;
        for(int i=0;i<2;i++)
        {
            initializePlayer(playersNames[i],playersSigns[i],isBot[i]);
        }
    }

    /*public*/private void initializePlayer(String playerName,char playerSign, boolean isBot)
    {
        if(isBot)
        {
            listOfPlayers.add(new PlayerAI(playerName,playerSign));
        }
        else
        {
            listOfPlayers.add(new Player(playerName,playerSign));
        }
    }

    public void gameProcess()
    {
        boolean isVictory=false;
        boolean isDraw=false;



        while (isVictory==false && isDraw==false)
        {
            //ioControl.displayGameField(gameField.getGameTiles());
            for(int i=0;i<listOfPlayers.size();i++)
            {
                char currentSymbol=listOfPlayers.get(i).getGameSymbol();
                int[] coordinates;

                do {
                    ioControl.displayGameField(gameField.getGameTiles());
                    if(listOfPlayers.get(i) instanceof PlayerAI)
                    {
                        PlayerAI duplicate=(PlayerAI) listOfPlayers.get(i);
                        coordinates=duplicate.responseAIPrototype();
                    }
                    else
                    {
                        coordinates=ioControl.enterGameCoordinates(listOfPlayers.get(i).getName(),listOfPlayers.get(i).getGameSymbol());

                    }
                }
                while (gameField.setTile(coordinates,listOfPlayers.get(i).getGameSymbol())==false);

                if(diagonalVictoryChecking(currentSymbol,gameField.getGameTiles()) || hvVictoryChecking(currentSymbol,gameField.getGameTiles()))
                {
                    isVictory=true;
                    break;
                }
                else if(drawChecking(gameField.getGameTiles()))
                {
                    isDraw=true;
                    break;
                }


            }
        }
    }

    private boolean drawChecking(char[][] fieldOfGame)
    {
        for (int i=0;i<fieldOfGame.length;i++)
        {
            for (int j=0;j<fieldOfGame[i].length;j++)
            {
                if(fieldOfGame[i][j]==0)
                {
                    return false;
                }
            }
        }
        return true;
    }
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
