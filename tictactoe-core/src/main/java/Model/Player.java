package Model;


public class Player
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
}
