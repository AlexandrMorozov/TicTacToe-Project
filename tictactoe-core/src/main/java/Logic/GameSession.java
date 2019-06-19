package Logic;

import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;
import Model.EndGameStatistics;
import Model.GameField;

import java.util.ArrayList;

public class GameSession
{
    private GameField gameField=new GameField();
    private VictoryConditionsChecking victoryConditions=new VictoryConditionsChecking();
    private ArrayList<PlayerInterface> listOfPlayers=new ArrayList<PlayerInterface>();
    private GamePlatformInterface platform;


    public GameSession(GamePlatformInterface platform)
    {
        this.platform=platform;
    }

    public void addPlayer(PlayerInterface player)
    {
        listOfPlayers.add(player);
    }

    public EndGameStatistics gameProcess()
    {
        boolean isVictory=false;
        boolean isDraw=false;
        String[] endGameData=null;

        while (isVictory==false && isDraw==false)
        {
            for(int i=0;i<listOfPlayers.size();i++)
            {
                char currentSymbol=listOfPlayers.get(i).getGameSymbol();
                int[] coordinates;

                do {
                    platform.displayField(gameField.getGameTiles());

                    coordinates=listOfPlayers.get(i).makeMove(platform);

                }
                while (gameField.setTile(coordinates,listOfPlayers.get(i).getGameSymbol())==false);

                if(victoryConditions.diagonalVictoryChecking(currentSymbol,gameField.getGameTiles()) || victoryConditions.hvVictoryChecking(currentSymbol,gameField.getGameTiles()))
                {
                    isVictory=true;
                    endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Victory of "+ listOfPlayers.get(i).getName(),listOfPlayers.get(i).getName()};
                    break;
                }
                else if(victoryConditions.drawChecking(gameField.getGameTiles()))
                {
                    isDraw=true;
                    endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Draw","none"};
                    break;
                }


            }
        }

        platform.displayEndOfGame(endGameData);
        return new EndGameStatistics(endGameData);
    }

}
