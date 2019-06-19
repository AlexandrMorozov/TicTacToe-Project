package Logic;

public class VictoryConditionsChecking
{
    public boolean drawChecking(char[][] fieldOfGame)
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
    public boolean diagonalVictoryChecking(char currentSymbol, char[][] fieldOfGame)
    {

        if((fieldOfGame[0][0]==currentSymbol && fieldOfGame[1][1]==currentSymbol && fieldOfGame[2][2]==currentSymbol)
                || (fieldOfGame[0][2]==currentSymbol && fieldOfGame[1][1]==currentSymbol && fieldOfGame[2][0]==currentSymbol))
        {
            return true;
        }
        return false;
    }
    public boolean hvVictoryChecking(char currentSymbol, char[][] fieldOfGame)//Проверка победы по вертикали и горизонтали
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
