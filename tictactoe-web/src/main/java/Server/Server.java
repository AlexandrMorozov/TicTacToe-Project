package Server;


import Main.Main;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/multiplayergame/{nickname}")
public class Server
{

    @OnOpen
    public void open(Session session, @PathParam("nickname") String nickname)
    {
        Main.getInstance().addSession(session,nickname);
    }

    @OnClose
    public void close(Session session)
    {
        System.out.println("dsdfdgdg");//
    }

    @OnError
    public void onError(Throwable error)
    {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session)
    {
        try (JsonReader reader = Json.createReader(new StringReader(message)))
        {
            JsonObject jsonMessage = reader.readObject();
            if ("game_request".equals(jsonMessage.getString("action")))
            {
                System.out.println("Game request from "+jsonMessage.getString("sender"));
                Main.getInstance().addToLine(jsonMessage.getString("sender"));
            }
            if ("game_request_cancel".equals(jsonMessage.getString("action")))
            {
                Main.getInstance().removeFromLine(jsonMessage.getString("sender"));
            }
            if("game_bot".equals(jsonMessage.getString("action")))
            {
                Main.getInstance().initBotGame(jsonMessage.getString("sender"));
            }
            if("make_move".equals(jsonMessage.getString("action")))
            {
                int[] coordinates=new int[2];
                coordinates[0]=jsonMessage.getInt("x");
                coordinates[1]=jsonMessage.getInt("y");
                Main.getInstance().makeTurn(coordinates,jsonMessage.getString("sender"));

            }
            if("get_statistics".equals(jsonMessage.getString("action")))
            {
                Main.getInstance().sendStatistics(jsonMessage.getString("sender"));
            }
        }
    }
}
