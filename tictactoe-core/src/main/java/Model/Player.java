package Model;


import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;

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

    public int[] makeMove(GamePlatformInterface platform)
    {
       return platform.enterCoordinates(name,gameSymbol);
    }
}
