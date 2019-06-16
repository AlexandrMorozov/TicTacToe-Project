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

    public char getTile(int[] coordinates)
    {
        return gameTiles[coordinates[0]][coordinates[1]];
    }

    public boolean setTile(int[] coordinates,char symbol)
    {
        if(checkTile(coordinates))
        {
            gameTiles[coordinates[0]][coordinates[1]]=symbol;
            return true;
        }
        return false;
    }

    private boolean checkTile(int[] coordinates)
    {
        if(gameTiles[coordinates[0]][coordinates[1]]!=0)
        {
            return false;
        }
        return true;
    }

}
