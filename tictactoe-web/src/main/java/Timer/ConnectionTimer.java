package Timer;

import Main.Main;
import Model.PlayerWeb;

import java.util.ArrayList;
import java.util.TimerTask;

public class ConnectionTimer extends TimerTask
{
    @Override
    public void run()
    {
        checkParticipants();
    }

    private void checkParticipants()
    {
       ArrayList<PlayerWeb> gameWaitingLine=Main.getInstance().getGameWaitingLine();

       while (gameWaitingLine.size()>=2)
       {
           System.out.println("I");
           int counter=0;
           int min=counter+1;
           int max=(gameWaitingLine.size()-1)-min;

           int connection=(int)(Math.random()*++max)+min;

           Main.getInstance().initSigns(gameWaitingLine.get(counter),gameWaitingLine.get(connection));
           Main.getInstance().addGame(gameWaitingLine.get(counter),gameWaitingLine.get(connection));

           Main.getInstance().initGame(gameWaitingLine.get(counter),gameWaitingLine.get(connection),"Your turn: ");
           Main.getInstance().initGame(gameWaitingLine.get(connection), gameWaitingLine.get(counter),"Opponent turn: ");

           gameWaitingLine.remove(counter);
           gameWaitingLine.remove(connection-1);

       }

    }

}
