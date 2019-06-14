package Main;

import InputOutput.ConsoleIO;
import Interfaces.GamePlatformInterface;
import Logic.GameSession;


public class Main
{
    // static GamePlatformInterface test=new ConsoleIO();
    //new comment

    //Точка входа для консольного приложения
    public static void main(String[] args)
    {
        GamePlatformInterface test=new ConsoleIO();
        String action=startApplication(test);
        if(action.equals("1"))
        {
            startGameSession(test);
        }
        else if(action.equals("2"))
        {
            showStatistics(test);
        }
        else if(action.equals("3"))
        {
            exitGame();
        }
    }
    private static String startApplication(GamePlatformInterface test)
    {
        String action;
        test.displayMenu();
        action=test.enterAnswer();
        return action;
    }
    private static void startGameSession(GamePlatformInterface test)
    {
        GameSession newGame=new GameSession();
        test.displayOpponentChoosingMenu();
        String[] playersNames=new String[2];
        char[] playersSigns;
        boolean[] isBot=new boolean[2];
        if(test.enterTypeOfGame().equals("1"))
        {
            playersNames=test.enterPlayerMultiplayer();
            isBot= new boolean[]{false,false};
        }
        else if(test.enterTypeOfGame().equals("2"))
        {
            playersNames=new String[]{test.enterPlayer(0),"AI1"};
            isBot= new boolean[]{false,true};
        }
        test.displaySignCombinationChoosingMenu(playersNames);
        playersSigns=test.enterSign();
        for(int i=0;i<playersNames.length;i++)
        {
            newGame.initializePlayer(playersNames[i],playersSigns[i],isBot[i]);
        }

    }
    private static void showStatistics(GamePlatformInterface test)
    {

    }
    private static void exitGame()
    {
        System.exit(0);
    }


}
