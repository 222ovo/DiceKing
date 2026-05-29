package Client;

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
                        System.out.println("当前玩家数量:" + playerNum);
                        if(player.getId() == -1)
                            player.setID(playerNum - 1);
                    }
                    else if(msg.equals("start"))
                    {
                        System.out.println("start");
                        player.setGameState(GameState.RUNNING);
                        player.getGameWindow().initGame();
                    }
                    else if(msg.startsWith("UpdatePlayerGold"))
                    {
                        String playerID = msg.substring("UpdatePlayerGold".length(),"UpdatePlayerGold".length() + 1);
                        String goldNum = msg.substring("UpdatePlayerGold".length() + 1).trim();
                        System.out.println("玩家" + playerID + "金币变化" + goldNum);
                        PlayerData.changePlayerGold(playerID,Integer.parseInt(goldNum));
                    }
                    else if(msg.equals("YourRound"))
                    {
                        System.out.println("到你的回合了");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
