package Model;

import java.util.ArrayList;

public class CommonStatistics
{
    private ArrayList<Statistics> playersStatistics=new ArrayList<Statistics>();

    public CommonStatistics()
    {
        for(int i=0;i<2;i++)
        {
            playersStatistics.add(new Statistics());
        }

    }

    public void addMainInfo(int counter, String result)
    {
        playersStatistics.get(counter).addMainStatisticsInfo(result);
    }

    public void sddLastInfo(EndGameStatistics lastGameInfo)
    {
        for(int i=0;i<playersStatistics.size();i++)
        {
            playersStatistics.get(i).addLastGameInfo(lastGameInfo);
        }
    }

    public String[] getLastInfo(int counter)
    {
        String[] lastGameInfo;
        lastGameInfo=playersStatistics.get(counter).getLastGameInfo();

        return lastGameInfo;
    }

    public int[] getMainInfo(int counter)
    {
        int[] mainGameInfo;
        mainGameInfo=playersStatistics.get(counter).getMainStatisticsInfo();

        return mainGameInfo;

    }
}
