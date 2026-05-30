package Client;

import Server.Server;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static int playerNum = 0;
    public static void main(String[] args)
    {
        Player player = new Player();
        while(true)
        {
            try {
                String msg = player.getInputStream().readUTF();
                if(msg != null) {
                    //通过不同的字符串实现协议解析
                    if(msg.startsWith("UpdatePlayersNum")) {
                        String numStr = msg.substring("UpdatePlayersNum".length()).trim();
                        playerNum = Integer.parseInt(numStr);
                        player.getGameWindow().setBroadText("当前玩家数量:" + playerNum);
                        if(player.getId() == -1)
                            player.setID(playerNum - 1);
                    }
                    else if(msg.equals("start"))
                    {
                        player.getGameWindow().setBroadText("游戏开始");
                        player.setGameState(GameState.RUNNING);
                        player.getGameWindow().initGame();
                    }
                    else if(msg.startsWith("UpdatePlayerGold"))
                    {
                        String playerID = msg.substring("UpdatePlayerGold".length(),"UpdatePlayerGold".length() + 1);
                        String goldNum = msg.substring("UpdatePlayerGold".length() + 1).trim();
                        player.getGameWindow().setBroadText("玩家" + playerID + "金币变化" + goldNum);
                        PlayerData.changePlayerGold(playerID,Integer.parseInt(goldNum));
                    }
                    else if(msg.equals("YourRound"))
                    {
                        System.out.println("到你的回合了");
                        player.getGameWindow().setBroadText("到你的回合了");
                        player.isRound = true;
                    }
                    else if(msg.startsWith("UpdatePlayerPos"))
                    {
                        String playerPos = msg.substring("UpdatePlayerPos".length());
                        String[] parts = playerPos.split("\\|");
                        int id = Integer.parseInt(parts[0]);
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);

                        player.getGameWindow().setBroadText("玩家" + id + "移动到了" + x/100 + "," + y/100);
                        Player.playerDataList.get(id).x = x;
                        Player.playerDataList.get(id).y = y;
                        player.getGameWindow().repaint();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
