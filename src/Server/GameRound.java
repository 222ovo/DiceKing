package Server;

import java.util.ArrayList;

public class GameRound {
    private ArrayList<ServerPlayer> players;//玩家列表

    public GameRound(ArrayList<ServerPlayer> players)
    {
        this.players = players;
    }

    public void gameStart()
    {
        Server.playerGoldChange("1",2000);
    }
}
