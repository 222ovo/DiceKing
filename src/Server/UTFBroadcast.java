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
            socket.setBroadcast(true);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while(isRunning)
        {
            String msg = "Server " + ip.getHostAddress();

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = null;
            try {
                packet = new DatagramPacket(bytes, bytes.length,
                        InetAddress.getByName("255.255.255.255"), 8888);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

            // 3.开始正式发送这个数据包的数据出去了
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
