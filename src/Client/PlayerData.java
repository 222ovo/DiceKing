package Client;

import java.awt.*;

public class PlayerData {
    int x;
    int y;
    private int gold;
    private int id = -1;
    public boolean isAlive = true;
    private Color color;

    public PlayerData(int x,int y,int gold,int id,Color color)
    {
        this.x = x;
        this.y = y;
        this.gold = gold;
        this.id = id;
        this.color = color;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor()
    {
        return color;
    }
    /**
     *
     * @title:改变玩家金币数量
     * @param id 玩家id
     */
    public static void changePlayerGold(String id,int goldNum)
    {
        PlayerData player = Player.playerDataList.get(Integer.parseInt(id));
        player.setGold(player.getGold() + goldNum);
    }

    public static void setToZero(String playerId)
    {
        int id = Integer.parseInt(playerId);
        switch(id)
        {
            case 0:
                System.out.println("玩家0");
                Player.playerDataList.get(id).x = Setting.PLAYER0_X;
                Player.playerDataList.get(id).y = Setting.PLAYER0_Y;
                break;
            case 1:
                System.out.println("玩家1");
                Player.playerDataList.get(id).x = Setting.PLAYER1_X;
                Player.playerDataList.get(id).y = Setting.PLAYER1_Y;
                break;
            case 2:
                System.out.println("玩家2");
                Player.playerDataList.get(id).x = Setting.PLAYER2_X;
                Player.playerDataList.get(id).y = Setting.PLAYER2_Y;
                break;
            case 3:
                System.out.println("玩家3");
                Player.playerDataList.get(id).x = Setting.PLAYER3_X;
                Player.playerDataList.get(id).y = Setting.PLAYER3_Y;
                break;
            case 4:
                System.out.println("玩家4");
                Player.playerDataList.get(id).x = Setting.PLAYER4_X;
                Player.playerDataList.get(id).y = Setting.PLAYER4_Y;
                break;
        }
    }
}
