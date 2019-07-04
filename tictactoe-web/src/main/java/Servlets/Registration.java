package Servlets;

import Main.Main;
import Model.PlayerWeb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class Registration extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String nickname=req.getParameter("nickname");
        Cookie[] cookies=req.getCookies();

        boolean isAuthorized=false;
        //Нужна будет доп. проверка
        for (Cookie c : cookies)
        {
            if(nickname.equals(c.getValue()))
            {
                isAuthorized=true;
                break;
            }
        }

        if(!isAuthorized)
        {
            resp.addCookie(new Cookie("player",nickname));
        }


        PlayerWeb newPlayer=new PlayerWeb();
        newPlayer.setName(nickname);
        System.out.println(newPlayer.getName());
        Main.getInstance().addPlayer(newPlayer,nickname);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/MainMenu.jsp");
        requestDispatcher.forward(req, resp);
    }



}
