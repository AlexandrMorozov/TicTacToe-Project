package Model;

import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;

public class PlayerAI extends Player implements PlayerInterface
{

    public int[] makeMove(GameField field, GamePlatformInterface ...platform)//?????????
    {
       int[] coordinates=null;
       coordinates=calculateCoordinates(field);
       if(coordinates==null)
       {
           do {
               coordinates=generateRandomCoordinates();
           }
           while (field.setTile(coordinates,gameSymbol)==false);

       }
       else
       {
           field.setTile(coordinates,gameSymbol);
       }

       return coordinates;
    }

    private int[] calculateCoordinates(GameField field)
    {
        int[] coordinates=new int[2];

        int[][] verticalCounters=new int[3][2];

        for(int i=0;i<field.getGameTiles().length;i++)
        {
            int opponentSignsCounter=0;
            int signsCounter=0;
            int freeTileIndex=0;

            for(int j=0;j<field.getGameTiles()[i].length;j++)
            {
                if(field.getGameTiles()[i][j]==gameSymbol)
                {
                    signsCounter++;
                    verticalCounters[j][0]++;
                }
                else if(field.getGameTiles()[i][j]==0)
                {
                    freeTileIndex=j;
                }
                else
                {
                    opponentSignsCounter++;
                    verticalCounters[j][1]++;
                }
            }

            if((opponentSignsCounter==2 && signsCounter==0) || (opponentSignsCounter==0 && signsCounter==2))
            {
                coordinates[0]=i;
                coordinates[1]=freeTileIndex;
                return coordinates;
            }
        }

        for (int g=0;g<verticalCounters.length;g++)
        {
            if((verticalCounters[g][0]==2 && verticalCounters[g][1]==0) || (verticalCounters[g][0]==0 && verticalCounters[g][1]==2))
            {
                for(int p=0;p<3;p++)
                {
                    if(field.getGameTiles()[p][g]==0)
                    {
                        coordinates[0]=p;
                        coordinates[1]=g;
                        return coordinates;
                    }
                }
            }
        }

        return null;
    }

   /* private int[] checkLeftDiagonal(GameField field)
    {
        int[] coordinates=null;
        int counter=0;
        int opponentSignsCounter=0;
        int signsCounter=0;
        int[] freeTileIndexes=new int[2];

       for(int i=0;i<field.getGameTiles().length;i++)
       {
           if(field.getGameTiles()[i][counter]==gameSymbol)
           {
               signsCounter++;
           }
           else if(field.getGameTiles()[i][counter]==0)
           {
               freeTileIndexes[0]=i;
               freeTileIndexes[1]=counter;
           }
           else
           {
               opponentSignsCounter++;
           }

           counter++;
       }

        if((opponentSignsCounter==2 && signsCounter==0) || (opponentSignsCounter==0 && signsCounter==2))
        {
            coordinates[0]=freeTileIndexes[0];
            coordinates[1]=freeTileIndexes[1];
            return coordinates;
        }
        return coordinates;

    }

    private int[] checkRightDiagonal(GameField field)
    {
        int[] coordinates=null;
        int counter=0;
        int opponentSignsCounter=0;
        int signsCounter=0;
        int[] freeTileIndexes=new int[2];

        for(int i=field.getGameTiles().length-1;i>=0;i--)
        {
            if(field.getGameTiles()[i][counter]==gameSymbol)
            {
                signsCounter++;
            }
            else if(field.getGameTiles()[i][counter]==0)
            {
                freeTileIndexes[0]=i;
                freeTileIndexes[1]=counter;
            }
            else
            {
                opponentSignsCounter++;
            }

            counter++;
        }

        if((opponentSignsCounter==2 && signsCounter==0) || (opponentSignsCounter==0 && signsCounter==2))
        {
            coordinates[0]=freeTileIndexes[0];
            coordinates[1]=freeTileIndexes[1];
            return coordinates;
        }

        return coordinates;
    }*/

    private int[] generateRandomCoordinates()
    {
        int[] coordinates=new int[2];
        int max=2;
        coordinates[0]=(int) (Math.random() * (max+1));
        coordinates[1]=(int) (Math.random() * (max+1));

        return coordinates;
    }





}
