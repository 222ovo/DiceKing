package Server;

import Client.PlayerData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerPlayer implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ServerPlayer(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readUTF();
                if (msg == null) break;

                System.out.println("收到：" + msg);
            }
        } catch (EOFException e) {
            System.out.println("客户端正常断开");
            removePlayer(this);
            for(ServerPlayer serverPlayer : Server.players) {
                serverPlayer.sendMessage(String.valueOf(Server.players.size()));
            }
        } catch (IOException e) {
            System.out.println("客户端异常断开");
            removePlayer(this);
            for(ServerPlayer serverPlayer : Server.players) {
                serverPlayer.sendMessage(String.valueOf(Server.players.size()));
            }
        }
    }

    public void sendMessage(String s)
    {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePlayer(ServerPlayer socket)
    {
        Server.players.remove(socket);
    }
}