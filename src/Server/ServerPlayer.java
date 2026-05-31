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
    private int id;
    public boolean isStay = false;
    public boolean isAlive;
    public ServerPlayer(Socket socket,int id) {
        this.id = id;
        this.socket = socket;
        this.isAlive = true;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}