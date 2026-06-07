package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class PlayerMsg extends Thread{
    //一个玩家线程,用于选择房间到开始游戏之前的阶段
    private ServerPlayer player;//一个玩家
    private boolean isRunning = true; //控制线程进行

    public PlayerMsg(ServerPlayer player)
    {
        this.player = player;
    }
    /**

     * @title: run

     * @description: 玩家与服务器交流的多线程
     *

     */
    @Override
    public void run() {
        while (!gameReady());//所有玩家都准备后break
        //只开启一个游戏线程
        if(player.getId() == 0)
            new GameThread(Server.players).start();//开始游戏
    }
    /**

     * @title: gameReady
     *
     * @description: 玩家进入房间后准备阶段的交流
     *

     * @return boolean 返回true表示三位玩家都准备了,可以开始游戏;false表示玩家退出这个房间

     */
    public boolean gameReady(){
        String playerMessage;//放玩家消息
        while(isRunning){//每隔0.01秒服务器向玩家询问准备状态,若所有玩家都准备了,则房间信号置true
            if ( PlayerManager.Instance.ready()) {//房间开始信号
                player.sendMsg("start");//提示玩家开始游戏
                System.out.println("游戏开始");
                isRunning = false;
                return true;//返回true
            }
            else
                player.sendMsg("ready?");//房间准备信息
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playerMessage = player.receiveMsg();
            switch (playerMessage) {
                case "ready" -> {
                    PlayerManager.Instance.addReady(player);
                }
                case "unready" -> {
                    PlayerManager.Instance.removeReady(player);
                }
                case "quit" -> {
                    try {
                        sleep(500);//延迟关闭玩家线程，确保玩家客户端游戏关闭
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player.safeDisconnect();
                    Server.updatePlayersNum();
                    isRunning = false;
                }
            }
        }
        return false;
    }
}
