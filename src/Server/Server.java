package Server;

import Client.Player;
import Client.Setting;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final ArrayList<ServerPlayer> players = new ArrayList<>();
    public static final int MAX_PLAYER = 5;
    public static void main(String[] args) throws Exception
    {
        InetAddress localAddr = NetUtil.getLocalHostLANAddress();
        System.out.println("服务启动，自动绑定 IP: " + localAddr.getHostAddress());

        ServerSocket server = new ServerSocket(8888, 50, localAddr);
        System.out.println("服务器已启动!");
        new UTFBroadcast(localAddr).start();
        PlayerManager playerManager = new PlayerManager();
        while(true)
        {
            System.out.println("等待玩家加入");
            Socket socket = server.accept();

            if(players.size() == MAX_PLAYER)
            {
                System.out.println("房间已满，拒绝了一名玩家加入");
                socket.close();
                continue;
            }
            ServerPlayer player = new ServerPlayer(socket,players.size());
            //需要启动一个专门用来接受玩家信息的线程
            new PlayerMsg(player).start();
            players.add(player);
            System.out.println("一名玩家加入了房间，当前房间人数：" + players.size());

            updatePlayersNum();
        }
    }
    public static void updatePlayersNum()
    {
        //通过不同的字符串实现协议解析
        sendMsgForAll("UpdatePlayersNum" + players.size());
    }

    /**
     * @title: sendMsgForAll
     * @description: 给所有玩家发送消息
     * @String: 发送的消息
     */
    public static void sendMsgForAll(String s)
    {
        for(ServerPlayer serverPlayer : players)
        {
            serverPlayer.sendMsg(s);
        }
    }

    /**
     *
     * @title: 给所有除id玩家以外的玩家发消息
     * @param s 发送的消息
     * @param id 玩家id
     */
    public static void sendMsgForAll(String s,int id)
    {
        for(ServerPlayer serverPlayer : players)
        {
            if(serverPlayer.getId() != id)
                serverPlayer.sendMsg(s);
        }
    }
    /**
     * @description: 给编号为id的玩家发送消息
     * @param id 玩家的编号
     * @param s 发送的消息
     */
    public static void sendMsgTo(int id,String s)
    {
        players.get(id).sendMsg(s);
    }

    /**
     * @title: 玩家金币改变
     * @param id 需要改变的玩家id
     * @param changeNum 需要改变的数量
     */
    public static void playerGoldChange(String id,int changeNum)
    {
        sendMsgForAll("UpdatePlayerGold" + id + changeNum);
    }
}
