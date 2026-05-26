package Client;

import Server.ServerPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Player{
    public ArrayList<PlayerData> playerDataList = new ArrayList<>();    //存储所有玩家数据的列表
    DataInputStream in;
    DataOutputStream out;
    Socket socket = new Socket();
    public Player()
    {
        GameWindow window = new GameWindow();
        try {
            String ip = "127.0.0.1";
            InetSocketAddress socketAddress = new InetSocketAddress(ip,8888);
            socket.connect(socketAddress);
            System.out.println("连接成功");
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());

            for(int i = 0;i < Client.playerNum;i++)
            {
                PlayerData data = new PlayerData(Setting.INITIAL_X,Setting.INITIAL_Y,Setting.INITIAL_GOLD,i);
                playerDataList.add(data);
            }
        } catch (IOException e) {
            System.out.println("断开连接");
        }
    }

    public void sendMessage(String s)
    {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
