package Client;

import Server.ServerPlayer;

import java.awt.*;
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
    private DataInputStream in;
    private DataOutputStream out;
    Socket socket = new Socket();
    boolean isReady = false;
    private int id = -1;     //每个玩家对应一个编号，对应玩家数组的序号
    private GameState gameState = GameState.BEFORE_START;   //游戏状态
    private GameWindow gameWindow;
    public Player()
    {
        try {
            String ip = "127.0.0.1";
            InetSocketAddress socketAddress = new InetSocketAddress(ip,8888);
            socket.connect(socketAddress);
            System.out.println("连接成功");
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());

            PlayerData data = null;
            for(int i = 0;i < Setting.MAX_PLAYERS_NUM;i++)
            {
                switch(i)
                {
                    case 0:
                        data = new PlayerData(Setting.PLAYER0_X,Setting.PLAYER0_Y,Setting.INITIAL_GOLD,0,Setting.PLAYER0_COLOR);
                        break;
                    case 1:
                        data = new PlayerData(Setting.PLAYER1_X,Setting.PLAYER1_Y,Setting.INITIAL_GOLD,1,Setting.PLAYER1_COLOR);
                        break;
                    case 2:
                        data = new PlayerData(Setting.PLAYER2_X,Setting.PLAYER2_Y,Setting.INITIAL_GOLD,2,Setting.PLAYER2_COLOR);
                        break;
                    case 3:
                        data = new PlayerData(Setting.PLAYER3_X,Setting.PLAYER3_Y,Setting.INITIAL_GOLD,3,Setting.PLAYER3_COLOR);
                        break;
                    case 4:
                        data = new PlayerData(Setting.PLAYER4_X,Setting.PLAYER4_Y,Setting.INITIAL_GOLD,4,Setting.PLAYER4_COLOR);
                        break;
                }
                playerDataList.add(data);
            }
        } catch (IOException e) {
            System.out.println("断开连接");
        }
        gameWindow = new GameWindow();
        gameWindow.setPlayer(this);
    }

    /**
     * @title: 玩家移动
     * @param points 移动格子数
     */
    public void Move(int points)
    {
        PlayerData data = playerDataList.get(id);
        data.x += Setting.GRID_WIDTH * points;
        GridPos gridPos = new GridPos(data.x, data.y);
        System.out.println(MapLoader.map.get(gridPos));
        System.out.println("你移动了" + points + "格");
        gameWindow.repaint();
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
        System.out.println("你的游戏id为" + id);
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

    public GameWindow getGameWindow()
    {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow)
    {
        this.gameWindow = gameWindow;
    }

    public DataOutputStream getOutputStream()
    {
        return out;
    }

    public DataInputStream getInputStream()
    {
        return in;
    }

}
