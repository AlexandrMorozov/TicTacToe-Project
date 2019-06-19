package Model;

public class EndGameStatistics
{
    private String[] lastGamePlayers=new String[2];
    private String lastGameResult;
    private String lastGameWinner;

    public EndGameStatistics(String[] lastGameInfo)
    {
        lastGamePlayers[0]=lastGameInfo[0];
        lastGamePlayers[1]=lastGameInfo[1];
        lastGameResult=lastGameInfo[2];
        lastGameWinner=lastGameInfo[3];
    }

    public String[] getLastGameInfo()
    {
        String[] lastGameInfo=new String[] {lastGamePlayers[0],lastGamePlayers[1],lastGameResult,lastGameWinner};
        return lastGameInfo;
    }

}
