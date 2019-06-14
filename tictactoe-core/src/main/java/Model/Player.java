package Model;

import Interfaces.PlayerInterface;

public class Player implements PlayerInterface
{
    private String name;
    private char gameSymbol;

    public Player(String name,char gameSymbol)
    {
        this.name=name;
        this.gameSymbol=gameSymbol;
    }

    public String getName()
    {
        return name;
    }

    public char getGameSymbol()
    {
        return gameSymbol;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGameSymbol(char gameSymbol)
    {
        this.gameSymbol = gameSymbol;
    }
}
