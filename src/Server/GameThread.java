package Server;

import java.util.ArrayList;

enum GameState
{
    BEFORE_START,//游戏还没开始
    RUNNING,    //游戏中
    OVER        //游戏已结束
}
class GameThread extends Thread {//一局游戏的多线程类
    ArrayList<ServerPlayer> players;

    public GameThread(ArrayList<ServerPlayer> players) {
        this.players = players;
    }

    @Override
    public void run() {
        GameRound game = new GameRound(players);
        Server.utfBroadcast.isRunning = false;
        game.gameStart();//开始一轮游戏
    }
}
