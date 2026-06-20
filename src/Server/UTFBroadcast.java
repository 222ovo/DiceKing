package Server;

import java.io.IOException;
import java.net.*;

public class UTFBroadcast extends Thread{

    InetAddress ip;
    boolean isRunning;
    public UTFBroadcast(InetAddress ip)
    {
        this.ip = ip;
        this.isRunning = true;
    }
    @Override
    public void run(){
        if(!isRunning) return;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while(isRunning)
        {
            String msg = "Server" + ip.toString();

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = null;
            try {
                packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8888);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

            try {
                socket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
