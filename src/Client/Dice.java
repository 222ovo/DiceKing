package Client;

import java.util.Random;

public class Dice {
    private int points;

    public  int RollDice(Player player)
    {
        Random r = new Random();
        points = r.nextInt(1,7);
        System.out.println("玩家" + player.getId() + "掷出的点数为" + points);
        player.sendMsg("RollDice" + player.getId() + points);
        return points;
    }
}
