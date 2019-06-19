package Model;

import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;

public class PlayerAI extends Player implements PlayerInterface
{

    public int[] makeMove(GamePlatformInterface platform)//?????????
    {
        return calculateCoordinates();
    }

    private int[] calculateCoordinates()
    {
        int[] coordinates=new int[2];
        return coordinates;
    }

}
