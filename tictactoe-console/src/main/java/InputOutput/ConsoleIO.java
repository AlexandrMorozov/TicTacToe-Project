package InputOutput;

import Interfaces.GamePlatformInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIO implements GamePlatformInterface
{
    /*public static void main(String[] args)
    {
       enterCoordinates("Al",'X');
    }*/
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
        double test=23;
        String action;
        Scanner input=new Scanner(System.in);
        do {
            //Можно вынести в отдельный метод
            System.out.print("Please enter your action: ");
            action=input.nextLine();
            System.out.println();
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


}
