package Client;

import Server.ServerPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

enum GameState{
    BEFORE_START,
    RUNNING,
    OVER
}

public class Player{
    public static ArrayList<PlayerData> playerDataList = new ArrayList<>();    //存储所有玩家数据的列表
    DataInputStream in;
    DataOutputStream out;
    Socket socket = new Socket();
    boolean isReady = false;
    private int id;     //每个玩家对应一个编号，对应玩家数组的序号
    GameState gameState = GameState.BEFORE_START;   //游戏状态
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
        window.setPlayer(this);
    }

    public void sendMsg(String s)
    {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receiveMsg()
    {
        try {
            return in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState newGameState)
    {
        gameState = newGameState;
    }
}
