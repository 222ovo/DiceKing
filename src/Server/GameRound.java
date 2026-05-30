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
                //掷骰子的人和发消息的人一定是玩家id
                String msg = players.get(id).receiveMsg();
                if (msg.startsWith("UpdatePlayerPos")) {

                    String playerPos = msg.substring("UpdatePlayerPos".length());
                    String[] parts = playerPos.split("\\|");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);

                    System.out.println("玩家" + id + "坐标为" + x + "," + y);
                    Server.sendMsgForAll("UpdatePlayerPos" + id + "|" + x + "|" + y,id);//给除了玩家id以外的玩家发消息
                }
                if(msg.equals("Over"))
                {
                    break;
                }
            }
        }
    }
}
