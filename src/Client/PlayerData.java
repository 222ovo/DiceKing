package Client;

public class PlayerData {
    int x;
    int y;
    private int gold;
    private int id;

    public PlayerData(int x,int y,int gold,int id)
    {
        this.x = x;
        this.y = y;
        this.gold = gold;
        this.id = id;
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
}
