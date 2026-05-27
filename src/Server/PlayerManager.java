package Server;

import java.util.ArrayList;

public class PlayerManager {
    public static PlayerManager Instance = new PlayerManager();
    public ArrayList<ServerPlayer> readyPlayer = new ArrayList<>();//已经准备了的玩家

    public void addReady(ServerPlayer player)
    {
        if(!readyPlayer.contains(player)) {
            readyPlayer.add(player);
            System.out.println("玩家" + player + "已准备");
            System.out.println("当前准备玩家数:" + readyPlayer.size());
            System.out.println("当前房间玩家数:" + Server.players.size());
        }
    }

    public void removeReady(ServerPlayer player)
    {
        if(readyPlayer.contains(player)) {
            readyPlayer.remove(player);
            System.out.println("玩家" + player + "取消准备");
            System.out.println("当前准备玩家数:" + readyPlayer.size());
            System.out.println("当前房间玩家数:" + Server.players.size());
        }
    }
    public boolean ready()
    {
        if(readyPlayer.size() == Server.players.size())
        {
            System.out.println("所有玩家都准备了");
            return true;
        }
        System.out.println("有玩家没有准备");
        return false;
    }

    public void clearAllReadPlayers()
    {
        readyPlayer.clear();
    }
}
