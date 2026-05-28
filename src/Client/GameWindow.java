package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameWindow extends JPanel{

    Player player;//当前客户端玩家
    //游戏主框架
    JFrame mainFrame = new JFrame("Game");
    JLayeredPane layeredPane = new JLayeredPane();
    //游戏背景图
    ImageIcon back = new ImageIcon("BackGround.jpg");
    JLabel bg = new JLabel(back);
    //准备按钮图
    ImageIcon ready = new ImageIcon("ready.png");
    JButton readyButton = new JButton(ready);
    //取消准备按钮图
    ImageIcon unReady = new ImageIcon("unready.png");
    //窗口位置
    int x;
    int y;
    //窗口大小
    int width;
    int height;
    public GameWindow()
    {
        this.setBounds(0, 0,
                back.getIconWidth(),
                back.getIconHeight());
        setOpaque(false);
        bg.setSize(back.getIconWidth(),back.getIconHeight());

        mainFrame.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));

        JPanel pan = (JPanel)mainFrame.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());

        layeredPane.setLayout(null);
        layeredPane.add(bg, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(this, JLayeredPane.POPUP_LAYER);

        mainFrame.setContentPane(layeredPane);
        mainFrame.setSize(back.getIconWidth(),back.getIconHeight());
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        readyButton.setContentAreaFilled(false);
        readyButton.setBorder(null);
        readyButton.setBounds(1920 / 2 - ready.getIconWidth() - 40, 1080 / 2 + ready.getIconHeight(), ready.getIconWidth()-1, ready.getIconHeight());
        readyButton.setOpaque(false);
        mainFrame.getLayeredPane().add(readyButton);

        readyButton.addActionListener(e -> {
            if(!player.isReady) {
                player.isReady = true;
                readyButton.setIcon(unReady);
                System.out.println("玩家" + player.getId() + "已准备");
                try {
                    player.out.writeUTF("ready");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(player.isReady){
                player.isReady = false;
                readyButton.setIcon(ready);
                System.out.println("玩家" + player.getId() + "取消准备");
                try {
                    player.out.writeUTF("unready");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    // 1. 发送退出消息
                      player.sendMsg("quit");
                    System.exit(0); // 直接结束进程

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
        });
//        (new Timer(16, (e) -> {
//            this.repaint();
//        })).start();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //画地图
        drawMap(g);
        //画玩家
        drawPlayers(g);
    }

    //画地图
    private void drawMap(Graphics g)
    {

    }

    private void drawPlayers(Graphics g)
    {
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            switch(i) {
                case 0:
                {
                    g.setColor(Color.PINK);
                    g.fillRect(50, 420, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.YELLOW);
                    g.fillRect(70, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(115, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 0) {
                        Font font = new Font("微软雅黑", Font.PLAIN, 32);
                        g.setFont(font);
                        g.drawString("我", 85, 550);
                    }
                }
                    break;
                case 1: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(780, 20, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(800, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(845, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 1) {
                        Font font = new Font("微软雅黑", Font.PLAIN, 32);
                        g.setFont(font);
                        g.drawString("我", 815, 150);
                    }
                }
                    break;
                case 2: {
                    g.setColor(Color.RED);
                    g.fillRect(1540, 420, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.YELLOW);
                    g.fillRect(1560, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1605, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 2) {
                        Font font = new Font("微软雅黑", Font.PLAIN, 32);
                        g.setFont(font);
                        g.drawString("我", 1575, 550);
                    }
                }
                    break;
                case 3: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(1060, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(1080, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1125, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 3) {
                        Font font = new Font("微软雅黑", Font.PLAIN, 32);
                        g.setFont(font);
                        g.drawString("我", 1095, 970);
                    }
                }
                    break;
                case 4: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(480, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(500, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(545, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 4) {
                        Font font = new Font("微软雅黑", Font.PLAIN, 32);
                        g.setFont(font);
                        g.drawString("我", 515, 970);
                    }
                }
                    break;
            }
        }
    }
}

