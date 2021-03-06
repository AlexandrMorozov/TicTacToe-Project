package Main;

import InputOutput.ConsoleIO;
import Interfaces.GamePlatformInterface;
import Interfaces.PlayerInterface;
import Interfaces.StatisticsInterface;
import Logic.GameSession;
import Model.*;

public class Main
{
    //new comment
    //Точка входа для консольного приложения
    public static void main(String[] args)
    {
        //StatisticsInterface statistics=new Statistics();
        CommonStatistics statistics=new CommonStatistics();
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
    private static void startGameSession(GamePlatformInterface inputOutput,/*StatisticsInterface*/CommonStatistics statistics)
    {
        inputOutput.displayOpponentChoosingMenu();
        PlayerInterface player1=null;
        PlayerInterface player2=null;

        char[] playersSigns;

        String typeOfGame=inputOutput.enterTypeOfGame();

        if(typeOfGame.equals("1"))
        {
            String[] playersNames=new String[2];

            do {
                for(int i=0;i<2;i++)
                {
                    playersNames[i]=inputOutput.enterPlayer(i);
                }
            }
            while (inputOutput.checkPlayersNamesMatch(playersNames)==false);

            player1=new Player();
            player2=new Player();
            player1.setName(playersNames[0]);
            player2.setName(playersNames[1]);
        }
        else if(typeOfGame.equals("2"))
        {
            player1=new Player();
            player2=new PlayerAI();
            player1.setName(inputOutput.enterPlayer(0));
            player2.setName("AI1");
        }

        inputOutput.displaySignCombinationChoosingMenu(player1.getName(),player2.getName());
        playersSigns=inputOutput.enterSign();
        player1.setGameSymbol(playersSigns[0]);
        player2.setGameSymbol(playersSigns[1]);

        GameSession newGame=new GameSession(inputOutput,statistics);

        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        /*EndGameStatistics endGameStatistics=*/newGame.gameProcess();
        //statistics.addLastGameInfo(endGameStatistics);


    }
    private static void showStatistics(GamePlatformInterface inputOutput, /*StatisticsInterface*/CommonStatistics statistics)
    {
        //String[] results=statistics.getLastGameInfo();
        //inputOutput.displayEndOfGame(results);

        for(int i=0;i<2;i++)
        {
            int num=i+1;
            inputOutput.displayMainStatistics(statistics.getMainInfo(i),"player"+num);
            inputOutput.displayEndOfGame(statistics.getLastInfo(i));
        }
    }
    private static void exitGame()
    {
        System.exit(0);
    }


}
