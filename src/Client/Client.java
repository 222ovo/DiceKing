package Client;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static int playerNum = 0;
    public static void main(String[] args)
    {
        Player player = new Player();
        while(true)
        {
            try {
                String msg = player.in.readUTF();
                if(msg != null) {
                    playerNum = Integer.parseInt(msg);
                    System.out.println(playerNum);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
