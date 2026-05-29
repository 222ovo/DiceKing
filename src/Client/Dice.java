package Client;

import java.util.Random;

public class Dice {
    private int points;

    public  int RollDice(Player player)
    {
        Random r = new Random();
        points = r.nextInt(1,7);
        player.sendMsg("RollDice" + points);
        return points;
    }
}
