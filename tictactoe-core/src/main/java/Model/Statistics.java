package Model;

import Interfaces.StatisticsInterface;

public class Statistics implements StatisticsInterface
{
   /* private int numOfVictories=0;
    private int numOfDefeats=0;
    private int numOfDraws=0;
    private int efficiencyCoefficient=0;*/

    private String[] lastGamePlayers=new String[2];
    private String lastGameResult;
    private String lastGameWinner;

    /*public int[] getMainStatisticsInfo()
    {
        int[] mainStatisticsInfo=new int[]{numOfVictories,numOfDefeats,numOfDraws,efficiencyCoefficient};
        return mainStatisticsInfo;
    }*/

    public String[] getLastGameInfo()
    {
        String[] lastGameInfo=new String[] {lastGamePlayers[0],lastGamePlayers[1],lastGameResult,lastGameWinner};
        return lastGameInfo;
    }

   /* public void addMainStatisticsInfo(String gameResult)
    {
        if(gameResult.equals("win"))
        {
            numOfVictories++;
        }
        else if(gameResult.equals("lose"))
        {
            numOfDefeats++;
        }
        else
        {
            numOfDraws++;
        }

        countEfficiencyCoefficient();
    }*/

    public void addLastGameInfo(String[] lastGameInfo)
    {
        lastGamePlayers[0]=lastGameInfo[0];
        lastGamePlayers[1]=lastGameInfo[1];
        lastGameResult=lastGameInfo[2];
        lastGameWinner=lastGameInfo[3];
    }

  /*  private int countEfficiencyCoefficient()
    {
        float numOfGames=numOfVictories+numOfDraws+numOfDefeats;

        if(numOfGames!=0)
        {
            return Math.round(100/numOfGames*numOfVictories);
        }

        return 0;
    }*/

}
