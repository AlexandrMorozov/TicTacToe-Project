package Model;

public class GameField
{
    private char[][] gameTiles;

    public GameField()
    {
        gameTiles=new char[3][3];
    }

    public char[][] getGameTiles()//
    {
        return gameTiles;
    }
    public char getTile(int x,int y)
    {
        return gameTiles[x][y];
    }
    public void setTile(int x,int y,char symbol)
    {
        gameTiles[x][y]=symbol;
    }

}
