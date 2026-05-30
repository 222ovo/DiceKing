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
                        msg = msg.substring("UpdatePlayerGold".length());
                        String[] parts = msg.split("\\|");
                        String playerID = parts[0];
                        String goldNum = parts[1];
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

                        Player.playerDataList.get(id).x = x;
                        Player.playerDataList.get(id).y = y;
                        player.getGameWindow().setBroadText("玩家" + id + "移动到了" + x/100 + "," + y/100);
                    }
                    else if(msg.startsWith("Buy"))
                    {
                        String buildingInfo = msg.substring("Buy".length());
                        String[] parts = buildingInfo.split("\\|");
                        String id = parts[0];
                        String name = parts[1];
                        int price = Integer.parseInt(parts[2]) * (-1);
                        System.out.println("玩家" + id + "花费" + price + "购买了" + name);

                        //处理逻辑
                        PlayerData.changePlayerGold(id,price);

                        for(Grid grid : MapLoader.map.values())
                        {
                            if(grid instanceof BuildingGrid)
                            {
                                Building building = ((BuildingGrid)grid).getBuilding();
                                if(building == null || building.getId() != -1) continue;

                                if(building.getName().trim().equals(name))
                                {
                                    building.setId(Integer.parseInt(id));
                                    System.out.println(building.getId());
                                }
                            }
                        }
                        player.getGameWindow().setBroadText("玩家" + id + "花费" + price + "购买了" + name);
                    }
                    else if(msg.startsWith("Pay"))
                    {
                        msg = msg.substring("Pay".length());
                        String[] parts = msg.split("\\|");
                        String id1 = parts[0];
                        String id2 = parts[1];;
                        int revenue = Integer.parseInt(parts[2]);

                        System.out.println("玩家" + id1 + "支付给玩家" + id2 + ":" + revenue);
                        //玩家id给玩家playerId revenue元
                        PlayerData.changePlayerGold(id1,revenue*(-1));
                        PlayerData.changePlayerGold(id2,revenue);
                        player.getGameWindow().setBroadText("玩家" + id1 + "支付给玩家" + id2 + ":" + revenue);
                    }
                    else if(msg.startsWith("BackToZero"))
                    {
                        String playerId = msg.substring("BackToZero".length());
                        System.out.println("不幸的玩家" + playerId + "回到了原点");
                        PlayerData.setToZero(playerId);
                        if(Integer.parseInt(playerId) == player.getId())
                        {
                            player.setMoveDir(Setting.INITIAL_DIR);
                        }
                        player.getGameWindow().setBroadText("不幸的玩家" + playerId + "回到了原点");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
