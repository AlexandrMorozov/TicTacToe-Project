package Model;

import Interfaces.StatisticsInterface;

public class Statistics implements StatisticsInterface
{
    private int numOfVictories=0;
    private int numOfDefeats=0;
    private int numOfDraws=0;
    private int efficiencyCoefficient=0;
    private EndGameStatistics endGameStatistics=new EndGameStatistics();

    public Statistics()
    {

    }

    public int[] getMainStatisticsInfo()
    {
        int[] mainStatisticsInfo=new int[]{numOfVictories,numOfDefeats,numOfDraws,efficiencyCoefficient};
        return mainStatisticsInfo;
    }

    public String[] getLastGameInfo()
    {
        return endGameStatistics.getLastGameInfo();
    }

    public void addMainStatisticsInfo(String gameResult)
    {
        if(gameResult.equals("victory"))
        {
            numOfVictories++;
        }
        else if(gameResult.equals("defeat"))
        {
            numOfDefeats++;
        }
        else if(gameResult.equals("draw"))
        {
            numOfDraws++;
        }

        countEfficiencyCoefficient();
    }

    public void addLastGameInfo(EndGameStatistics endGameStatistics)
    {
        this.endGameStatistics=endGameStatistics;

    }

    private int countEfficiencyCoefficient()
    {
        float numOfGames=numOfVictories+numOfDraws+numOfDefeats;

        if(numOfGames!=0)
        {
            return Math.round(100/numOfGames*numOfVictories);
        }

        return 0;
    }

}
