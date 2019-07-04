package Interfaces;

import Model.GameField;

public interface PlayerInterface
{
    String getName();
    char getGameSymbol();
    void setName(String name);
    void setGameSymbol(char gameSymbol);
    int[] makeMove(GameField field, GamePlatformInterface ...platform);

}
