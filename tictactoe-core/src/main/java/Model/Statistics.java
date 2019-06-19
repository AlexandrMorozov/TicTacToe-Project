package Model;

import Interfaces.StatisticsInterface;

public class Statistics implements StatisticsInterface
{
   /* private int numOfVictories=0;
    private int numOfDefeats=0;
    private int numOfDraws=0;
    private int efficiencyCoefficient=0;*/

    private EndGameStatistics endGameStatistics;
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
        return endGameStatistics.getLastGameInfo();
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

    public void addLastGameInfo(EndGameStatistics endGameStatistics)
    {
        this.endGameStatistics=endGameStatistics;
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
