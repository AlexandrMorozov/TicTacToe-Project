package Main;

import InputOutput.ConsoleIO;
import Interfaces.GamePlatformInterface;
import Interfaces.StatisticsInterface;
import Logic.GameSession;
import Logic.InputOutputController;
import Model.Statistics;


public class Main
{
    // static GamePlatformInterface test=new ConsoleIO();
    //new comment
    //Точка входа для консольного приложения
    public static void main(String[] args)
    {
        StatisticsInterface statistics=new Statistics();
        GamePlatformInterface inputOutput=new ConsoleIO();
        boolean isExit=false;

        while (isExit==false)
        {
            String action=startApplication(inputOutput);

            if(action.equals("1"))
            {
                startGameSession(inputOutput,statistics);
            }
            else if(action.equals("2"))
            {
                showStatistics(inputOutput,statistics);
            }
            else if(action.equals("3"))
            {
                isExit=true;
            }
        }

        exitGame();

    }
    private static String startApplication(GamePlatformInterface test)
    {
        String action;
        test.displayMenu();
        action=test.enterAnswer();
        return action;
    }
    private static void startGameSession(GamePlatformInterface inputOutput,StatisticsInterface statistics)
    {
       // GameSession newGame=new GameSession();
        inputOutput.displayOpponentChoosingMenu();

        String[] playersNames=null;
        char[] playersSigns;
        boolean[] isBot=null;

        if(inputOutput.enterTypeOfGame().equals("1"))
        {
            playersNames=inputOutput.enterPlayerMultiplayer();
            isBot= new boolean[]{false,false};
        }
        else if(inputOutput.enterTypeOfGame().equals("2"))
        {
            playersNames=new String[]{inputOutput.enterPlayer(0),"AI1"};
            isBot= new boolean[]{false,true};
        }

        inputOutput.displaySignCombinationChoosingMenu(playersNames);
        playersSigns=inputOutput.enterSign();

        InputOutputController ioControl=new InputOutputController(inputOutput);
        GameSession newGame=new GameSession(playersNames,playersSigns,isBot,ioControl);
        newGame.gameProcess(statistics);


    }
    private static void showStatistics(GamePlatformInterface inputOutput, StatisticsInterface statistics)
    {
        String[] results=statistics.getLastGameInfo();
        inputOutput.displayEndOfGame(results);
    }
    private static void exitGame()
    {
        System.exit(0);
    }


}
