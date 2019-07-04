package Logic;

import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;
import Model.CommonStatistics;
import Model.EndGameStatistics;
import Model.GameField;

import java.util.ArrayList;

public class GameSession
{
    private GameField gameField=new GameField();
    private VictoryConditionsChecking victoryConditions=new VictoryConditionsChecking();
    private ArrayList<PlayerInterface> listOfPlayers=new ArrayList<PlayerInterface>();
    private GamePlatformInterface platform;
    private CommonStatistics statistics;


    public GameSession(GamePlatformInterface platform, CommonStatistics statistics)
    {
        this.platform=platform;
        this.statistics=statistics;
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

                platform.displayField(gameField.getGameTiles());
                coordinates=listOfPlayers.get(i).makeMove(gameField,platform);

                /*do {
                    platform.displayField(gameField.getGameTiles());

                    coordinates=listOfPlayers.get(i).makeMove(platform);

                }
                while (gameField.setTile(coordinates,listOfPlayers.get(i).getGameSymbol())==false);*/

                if(victoryConditions.diagonalVictoryChecking(currentSymbol,gameField.getGameTiles()) || victoryConditions.hvVictoryChecking(currentSymbol,gameField.getGameTiles()))
                {
                    isVictory=true;

                    if(i==0)
                    {
                        statistics.addMainInfo(0,"victory");
                        statistics.addMainInfo(1,"defeat");
                    }
                    else
                    {
                        statistics.addMainInfo(1,"victory");
                        statistics.addMainInfo(0,"defeat");
                    }
                    endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Victory of "+ listOfPlayers.get(i).getName(),listOfPlayers.get(i).getName()};
                    statistics.sddLastInfo(new EndGameStatistics(endGameData));
                    break;
                }
                else if(victoryConditions.drawChecking(gameField.getGameTiles()))
                {
                    isDraw=true;

                    statistics.addMainInfo(0,"draw");
                    statistics.addMainInfo(1,"draw");

                    endGameData=new String[]{listOfPlayers.get(0).getName(),listOfPlayers.get(1).getName(),"Draw","none"};
                    statistics.sddLastInfo(new EndGameStatistics(endGameData));
                    break;
                }


            }
        }

        platform.displayEndOfGame(endGameData);
        return new EndGameStatistics(endGameData);
    }

}
