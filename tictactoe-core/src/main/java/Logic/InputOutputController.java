package Logic;

import Interfaces.GamePlatformInterface;

public class InputOutputController
{
    GamePlatformInterface platform;
    public InputOutputController(GamePlatformInterface platform)
    {
        this.platform=platform;
    }


    /*public void displayMainMenu()//Не определена необходимость присутствия
    {
        platform.displayMenu();
    }
    public void displayOCMenu()
    {
        platform.displayOpponentChoosingMenu();
    }*/
    public void displayGameField(char[][] gameField)
    {
        platform.displayField(gameField);
    }
    /*public void displaySCCMenu(String[] playersNames)//Не определена необходимость присутствия
    {
        platform.displaySignCombinationChoosingMenu(playersNames);
    }
    public String enterMenuAnswer()
    {
        return platform.enterAnswer();
    }
    public String enterTOG()
    {
        return platform.enterTypeOfGame();
    }
    public String[] enterPMultiplayer()
    {
        return platform.enterPlayerMultiplayer();
    }
    public String enterPPlayer(int playerNum)
    {
        return platform.enterPlayer(playerNum);
    }
    public char[] enterGameSign()
    {
        return platform.enterSign();
    }*/
    public int[] enterGameCoordinates(String playerName, char playerSign)
    {
        return platform.enterCoordinates(playerName,playerSign);
    }
    public  void displayEndGame(String[] results)
    {
         platform.displayEndOfGame(results);
    }

}
