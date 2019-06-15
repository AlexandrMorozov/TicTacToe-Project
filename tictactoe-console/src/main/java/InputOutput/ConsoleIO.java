package InputOutput;

import Interfaces.GamePlatformInterface;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleIO implements GamePlatformInterface
{
   /* public static void main(String[] args)
    {
       //enterCoordinates("Al",'X');
        validatePlayer("dd");
    }*/

   private String inputStringData(String message)
   {
       String receiver;
       Scanner input=new Scanner(System.in);
       System.out.print(message);
       receiver=input.nextLine();
       System.out.println();
       //input.close();
       return receiver;
   }
    //Отображение меню
    public void displayMenu()
    {
        System.out.println("Hello! Welcome in our incredible Tic-Tac-Toe game! Please, Choose your action(Enter number of action):");
        System.out.println("1. Start new game");
        System.out.println("2. Statistics");
        System.out.println("3. Exit");
    }
    //Проверка ввода действия на валидность
    private boolean checkAnswer(String answer)
    {
        boolean isValid=false;
        if(answer.equals("1") || answer.equals("2") || answer.equals("3") )
        {
            isValid=true;
        }
        else
        {
            System.out.println("Wrong data! Please try again ...");
        }
        return isValid;
    }
    //Ввод действия
    public String enterAnswer()
    {
        String action;
        do {
            action=inputStringData("Please enter your action: ");
        }
        while (checkAnswer(action)==false);

        return action;
    }
    //Отображение игрового поля
    public void displayField(char[][] gameField)
    {
        for(int i=0;i<gameField.length;i++)
        {
            System.out.println(gameField[i][0]+" | "+gameField[i][1]+" | "+gameField[i][2]);
            if(i<2)
            {
                System.out.println("_________");
            }
        }
    }
    //Ввод координат
    public int[] enterCoordinates(String playerName, char playerSign)
    {
        int[] coordinates;

        do {
            //Можно вынести в отдельный метод
            System.out.println("Player "+playerName+"("+playerSign+") turn. Enter coordinates of the tile in the following order: X;Y :");
            coordinates= receiveCoordinates();
            System.out.println();
        }
        while (checkCoordinates(coordinates)==false);

        return coordinates;
    }
    //Проверка ввода на валидность
    private int[] receiveCoordinates()
    {
        int[] coordinates=new int[2];
        for(int i=0;i<2;i++)
        {
            boolean isValid=false;
            while (isValid==false)
            {
                String message="Enter "+i+" coordinate";
                System.out.println(message);
                try
                {
                    Scanner input=new Scanner(System.in);
                    coordinates[i]=input.nextInt();
                    isValid=true;
                }
                catch (InputMismatchException e)
                {
                }
            }
        }
        return coordinates;

    }
    //Проверка данных на валидность
    private boolean checkCoordinates(int[] coordinates)
    {
        for(int i=0;i<coordinates.length;i++)
        {
            if(coordinates[i]<0 || coordinates[i]>2)
            {
                System.out.println("Wrong data! Numbers should be from 0 to 2");
                return false;
            }
        }
        return true;
    }
    //Вывод меню выбора типа игры
    public void displayOpponentChoosingMenu()
    {
        System.out.println("Select type of game:");
        System.out.println("1. Another player");
        System.out.println("2. Game bot");
    }
    //Ввод типа игры
    public String enterTypeOfGame()
    {
        String action;
        do {
            action=inputStringData("Please choose type of the game: ");
        }
        while (checkTypeOfGame(action)==false);

        return action;
    }
    //Проверка ввода типа игры
    private boolean checkTypeOfGame(String answer)
    {
        if(answer.equals("1") || answer.equals("2") )
        {
            return true;
        }
        else
        {
            System.out.println("Wrong data! Please try again ...");
        }
        return false;
    }

    //Общий метод ввода для игры с другим игроком(с проверкой на совпадение имён)
    public String[] enterPlayerMultiplayer()
    {
        String[] playersNames=new String[2];
        do {

            for(int i=0;i<2;i++)
            {
               playersNames[i]= enterPlayer(i);
            }
        }
        while (checkPlayersNamesMatch(playersNames)==false);

        return playersNames;

    }
    //Ввод имени игрока
    public String enterPlayer(int playerNum)
    {
        String action;
        do {
            action=inputStringData("Enter "+playerNum+" player name: ");
        }
        while (validatePlayer(action)==false);

        return action;
    }
    //Проверка введённого имени на валидность
    private boolean validatePlayer(String playerName)
    {
        if(Pattern.matches("[A-Za-z0-9]*",playerName))
        {
            return true;
        }

        return false;
    }
    //Проверка имён игроков на совпадение
    private boolean checkPlayersNamesMatch(String[] playersNames)
    {
        if(playersNames[0].equals(playersNames[1]))
        {
            return false;
        }

        return true;
    }
    //Менб выбора знака
    public void displaySignCombinationChoosingMenu(String[] playersNames)
    {
        System.out.println("Select sign combination: ");
        System.out.println("1. "+playersNames[0]+" X; "+playersNames[1]+" O; ");
        System.out.println("2. "+playersNames[1]+" X; "+playersNames[0]+" O; ");
    }
    //Ввод знака
    public char[] enterSign()
    {
        char[] signs=new char[2];
        String action;
        do {
            action=inputStringData("Enter combination: ");
        }
        while (validateSign(action)==false);


        if(action.equals("1"))
        {
            signs[0]='X';
            signs[1]='O';
        }
        else if(action.equals("2"))
        {
            signs[0]='O';
            signs[1]='X';
        }

        return signs;
    }
    //Проверка правильности ввода
    private boolean validateSign(String combination)
    {
        if(combination.equals("1") || combination.equals("2") )
        {
            return true;
        }
        else
        {
            System.out.println("Wrong data! Please try again ...");
        }
        return false;
    }







}
