package Server;

import Client.PlayerData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerPlayer{

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

//    @Override
//    public void run() {
//        try {
//            while (true) {
//                String msg = in.readUTF();
//                if (msg == null) break;
//
//                System.out.println("收到：" + msg);
//            }
//        } catch (EOFException e) {
//            System.out.println("客户端正常断开");
//            removePlayer(this);
//            Server.updatePlayersNum();
//        } catch (IOException e) {
//            System.out.println("客户端异常断开");
//            removePlayer(this);
//            Server.updatePlayersNum();
//        }
//    }

    //发送信息
    public void sendMsg(String s)
    {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //接收信息
    public String receiveMsg()
    {
        try {
            return in.readUTF();
        } catch (SocketTimeoutException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    //移除玩家
//    public void removePlayer(ServerPlayer socket) {
//        Server.players.remove(socket);
//    }

    void safeDisconnect() {
        // 1️⃣ 先移除（防止再广播）
        Server.players.remove(this);
        PlayerManager.Instance.getReadyPlayers().remove(this);

        // 2️⃣ 关闭流
        try { if (in != null) in.close(); } catch (IOException ignored) {}
        try { if (out != null) out.close(); } catch (IOException ignored) {}

        // 3️⃣ 关闭 Socket
        try { if (socket != null && !socket.isClosed()) {
            socket.close();
        }} catch (IOException ignored) {}
    }
    public Socket getSocket()
    {
        return socket;
    }
}