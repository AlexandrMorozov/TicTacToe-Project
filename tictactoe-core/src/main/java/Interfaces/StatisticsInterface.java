package Interfaces;

import Model.EndGameStatistics;

public interface StatisticsInterface
{
   /* int[] getMainStatisticsInfo();*/
    String[] getLastGameInfo();
    /*void addMainStatisticsInfo(String gameResult);*/
    void addLastGameInfo(EndGameStatistics endGameStatistics);

}
