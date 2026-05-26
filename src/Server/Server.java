package Server;

import Client.Player;
import Client.Setting;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final ArrayList<ServerPlayer> players = new ArrayList<>();
    public static final int MAX_PLAYER = 5;
    public static void main(String[] args) throws Exception
    {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器已启动!");
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
            ServerPlayer player = new ServerPlayer(socket);
            players.add(player);
            new Thread(player).start();
            System.out.println("一名玩家加入了房间，当前房间人数：" + players.size());
            for(ServerPlayer serverPlayer : players) {
                serverPlayer.sendMessage(String.valueOf(players.size()));
            }
            //player.sendMessage(String.valueOf(players.size()));
        }
    }

}
