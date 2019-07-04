package Model;


import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;
import Interfaces.StatisticsInterface;

public class Player implements PlayerInterface
{
    protected String name;
    protected char gameSymbol;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGameSymbol(char gameSymbol)
    {
        this.gameSymbol = gameSymbol;
    }

    public String getName()
    {
        return name;
    }

    public char getGameSymbol()
    {
        return gameSymbol;
    }

    public int[] makeMove(GameField field,GamePlatformInterface ...platform)
    {
        int[] coordinates=platform[0].enterCoordinates(name,gameSymbol);

        if(field.setTile(coordinates,gameSymbol)==false)
        {
            platform[0].displayField(field.getGameTiles());
            coordinates=makeMove(field,platform[0]);
        }

       return coordinates;
    }
}
