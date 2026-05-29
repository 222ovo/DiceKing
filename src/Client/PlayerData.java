package Client;

import java.awt.*;

public class PlayerData {
    int x;
    int y;
    private int gold;
    private int id = -1;
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
}
