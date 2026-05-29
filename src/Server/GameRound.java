package Server;

import Client.Client;

import java.util.ArrayList;
import java.util.Random;

public class GameRound {
    private ArrayList<ServerPlayer> players;//玩家列表
    private int id; //当前回合的玩家id
    public GameRound(ArrayList<ServerPlayer> players)
    {
        this.players = players;
    }

    public void gameStart()
    {
        Random r = new Random();
        id = r.nextInt(0,players.size());//选出进行回合的玩家

        while(true)
        {
            id = (id+1)%players.size(); //当前玩家回合
            players.get(id).sendMsg("YourRound");   //给玩家发信息

            while(true) {
                String msg = players.get(id).receiveMsg();
                if (msg.startsWith("RollDice")) {
                    String points = msg.substring("RollDice".length());
                    System.out.println("玩家" + id + "掷出的点数为" + points);
                    Server.sendMsgForAll("RollDice" + id + points);
                }
                if(msg.equals("Over"))
                {
                    break;
                }
            }
        }
    }
}
