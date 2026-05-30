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
            ServerPlayer player = players.get(id);
            if(player.isStay)
            {
                player.isStay = false;
                continue;
            }
            player.sendMsg("YourRound");   //给玩家发信息

            while(true) {
                //掷骰子的人和发消息的人一定是玩家id
                String msg = player.receiveMsg();
                if (msg.startsWith("UpdatePlayerPos")) {

                    String playerPos = msg.substring("UpdatePlayerPos".length());
                    String[] parts = playerPos.split("\\|");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);

                    System.out.println("玩家" + id + "坐标为" + x + "," + y);
                    Server.sendMsgForAll("UpdatePlayerPos" + id + "|" + x + "|" + y,id);//给除了玩家id以外的玩家发消息
                }
                else if(msg.startsWith("Buy"))
                {
                    String buildingInfo = msg.substring("Buy".length());
                    String[] parts = buildingInfo.split("\\|");
                    String name = parts[0];
                    int price = Integer.parseInt(parts[1]);

                    System.out.println("玩家" + id + "花费" + price + "购买了" + name);
                    Server.sendMsgForAll("Buy" + id + "|" + name + "|" + price);
                }
                else if(msg.startsWith("Pay"))
                {
                    msg = msg.substring("Pay".length());
                    String[] parts = msg.split("\\|");
                    int revenue = Integer.parseInt(parts[0]);
                    String playerId = parts[1];

                    System.out.println("玩家" + id + "支付给玩家" + playerId + ":" + revenue);
                    //玩家id给玩家playerId revenue元
                    Server.sendMsgForAll("Pay" + id + "|" + playerId + "|" + revenue);
                }
                else if(msg.startsWith("UpdatePlayerGold"))
                {
                    String bounds = msg.substring("UpdatePlayerGold".length());

                    System.out.println("玩家" + id + "获得" + bounds);
                    Server.sendMsgForAll("UpdatePlayerGold" + id + "|" + bounds);
                }
                else if(msg.equals("Stay"))
                {
                    player.isStay = true;
                    System.out.println("滞留一回合");
                }
                else if(msg.equals("BackToZero"))
                {
                    Server.sendMsgForAll("BackToZero" + id);
                    System.out.println("玩家" + id + "回到原点");
                }
                else if(msg.equals("Over"))
                {
                    System.out.println("玩家" + id + "回合结束");
                    break;
                }
            }
        }
    }
}
