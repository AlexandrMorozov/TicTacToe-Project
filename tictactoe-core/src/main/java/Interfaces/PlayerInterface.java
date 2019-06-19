package Interfaces;

public interface PlayerInterface
{
    String getName();
    char getGameSymbol();
    void setName(String name);
    void setGameSymbol(char gameSymbol);
    int[] makeMove(GamePlatformInterface platform);

}
