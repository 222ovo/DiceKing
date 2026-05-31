package Server;

import java.io.IOException;
import java.net.*;

public class UTFBroadcast extends Thread{

    InetAddress ip;
    public UTFBroadcast(InetAddress ip)
    {
        this.ip = ip;
    }
    @Override
    public void run(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while(true)
        {
            String msg = "Server" + ip.toString();

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = null;
            try {
                packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8888);
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
