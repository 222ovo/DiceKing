package Server;

import java.util.ArrayList;

public class PlayerManager {
    public static PlayerManager Instance = new PlayerManager();
    private ArrayList<ServerPlayer> readyPlayers = new ArrayList<>();//已经准备了的玩家
    private boolean isRunning = false;  //游戏已经开始

    public void addReady(ServerPlayer player)
    {
        if(!readyPlayers.contains(player)) {
            readyPlayers.add(player);
            System.out.println("玩家" + player + "已准备");
            System.out.println("当前准备玩家数:" + readyPlayers.size());
            System.out.println("当前房间玩家数:" + Server.players.size());
        }
    }

    public void removeReady(ServerPlayer player)
    {
        if(readyPlayers.contains(player)) {
            readyPlayers.remove(player);
            System.out.println("玩家" + player + "取消准备");
            System.out.println("当前准备玩家数:" + readyPlayers.size());
            System.out.println("当前房间玩家数:" + Server.players.size());
        }
    }
    public boolean ready()
    {
        if( Server.players.size() >= 2 ) //如果至少有两个玩家加入游戏，且全部准备
        {
            System.out.println("玩家数量足够可以开始游戏");
            if(readyPlayers.size() == Server.players.size()) {
                System.out.println("所有玩家都准备了");
                return true;
            }
            System.out.println("有玩家没有准备");
            return false;
        }
        System.out.println("玩家数量太少，无法开始游戏");
        return false;
    }

    public void clearAllReadPlayers()
    {
        readyPlayers.clear();
    }

    public ArrayList<ServerPlayer> getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(ArrayList<ServerPlayer> readyPlayers)
    {
        this.readyPlayers = readyPlayers;
    }
}
