package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameWindow extends JPanel{

    public int i = 1;
    //当前客户端玩家
    Player player;
    //骰子
    Dice dice = new Dice();
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
    //骰子的图片
    ImageIcon dice1 = new ImageIcon("Dice1.png");
    ImageIcon dice2 = new ImageIcon("Dice2.png");
    ImageIcon dice3 = new ImageIcon("Dice3.png");
    ImageIcon dice4 = new ImageIcon("Dice4.png");
    ImageIcon dice5 = new ImageIcon("Dice5.png");
    ImageIcon dice6 = new ImageIcon("Dice6.png");
    //投资帧动画
    ImageIcon diceAnim1 = new ImageIcon("DiceFrame1.png");
    ImageIcon diceAnim2 = new ImageIcon("DiceFrame2.png");
    ImageIcon diceAnim3 = new ImageIcon("DiceFrame3.png");
    ImageIcon diceAnim4 = new ImageIcon("DiceFrame4.png");
    JButton diceButton = new JButton(dice1);
//    //窗口位置
//    int x;
//    int y;
//    //窗口大小
//    int width;
//    int height;
    public GameWindow()
    {
        MapLoader.initialMap();
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

        diceButton.setContentAreaFilled(false);
        diceButton.setBorder(null);
        diceButton.setBounds(1920 / 2 - diceButton.getIcon().getIconWidth() - 40, 1080 / 2 - diceButton.getIcon().getIconHeight() + 80, diceButton.getIcon().getIconWidth()-1,diceButton.getIcon().getIconHeight());
        diceButton.setOpaque(false);
        mainFrame.getLayeredPane().add(diceButton);

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

        diceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceAnimation();
                dice.RollDice(player);
            }
        });
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();
                if(160 <= x && x <= 200 && 420 <= y && y <= 480)
                {
                    System.out.println("打开玩家" + 0 + "的房产本");
                }
                if(890 <= x && x <= 930 && 20 <= y && y <= 80)
                {
                    System.out.println("打开玩家" + 1 + "的房产本");
                }
                if(1650 <= x && x <= 1690 && 420 <= y && y <= 480)
                {
                    System.out.println("打开玩家" + 2 + "的房产本");
                }
                if(1170 <= x && x <= 1190 && 840 <= y && y <= 900)
                {
                    System.out.println("打开玩家" + 3 + "的房产本");
                }
                if(270 <= x && x <= 290 && 420 <= y && y <= 480)
                {
                    System.out.println("打开玩家" + 4 + "的房产本");
                }
            }
        });
//        (new Timer(100, (e) -> {
//            i = (i+1) % 6;
//            switch (i)
//            {
//                case 0:
//                    diceButton.setIcon(new ImageIcon("Dice1.png"));
//                    System.out.println(i);
//                    break;
//                case 1:
//                    diceButton.setIcon(new ImageIcon("Dice2.png"));
//                    System.out.println(i);
//                    break;
//                case 2:
//                    diceButton.setIcon(new ImageIcon("Dice3.png"));
//                    System.out.println(i);
//                    break;
//                case 3:
//                    diceButton.setIcon(new ImageIcon("Dice4.png"));
//                    System.out.println(i);
//                    break;
//                case 4:
//                    diceButton.setIcon(new ImageIcon("Dice5.png"));
//                    System.out.println(i);
//                    break;
//                case 5:
//                    diceButton.setIcon(new ImageIcon("Dice6.png"));
//                    System.out.println(i);
//                    break;
//            }
//        })).start();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Font font = new Font("微软雅黑", Font.PLAIN, 32);
        g.setFont(font);

        super.paintComponent(g);
        //画玩家
        drawPlayers(g);
        //画金币
        drawGold(g);
//        //画房产本
//        drawBuildingBook(g);
        //画地图
        drawMap(g);
    }

    //画地图
    private void drawMap(Graphics g)
    {
        for(Grid grid : MapLoader.grids)
        {
            grid.draw(g);
        }
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
                        g.drawString("我", 85, 550);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(0).getGold(),40,580);
                }
                    break;
                case 1: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(780, 20, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(800, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(845, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 1) {
                        g.drawString("我", 815, 150);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(1).getGold(),770,180);
                }
                    break;
                case 2: {
                    g.setColor(Color.RED);
                    g.fillRect(1540, 420, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.YELLOW);
                    g.fillRect(1560, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1605, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 2) {
                        g.drawString("我", 1575, 550);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(2).getGold(),1530,580);
                }
                    break;
                case 3: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(1060, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(1080, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1125, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 3) {
                        g.drawString("我", 1095, 970);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(3).getGold(),1050,1000);
                }
                    break;
                case 4: {
                    g.setColor(Color.YELLOW);
                    g.fillRect(480, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(500, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(545, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 4) {
                        g.drawString("我", 515, 970);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(4).getGold(),470,1000);
                }
                    break;
            }
        }
    }

    public void drawGold(Graphics g)
    {
        g.setColor(Color.yellow);
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            switch(i) {
                case 0:
                {
                    g.drawString("金币:" + Player.playerDataList.get(0).getGold(),40,580);
                }
                break;
                case 1: {
                    g.drawString("金币:" + Player.playerDataList.get(1).getGold(),770,180);
                }
                break;
                case 2: {
                    g.drawString("金币:" + Player.playerDataList.get(2).getGold(),1530,580);
                }
                break;
                case 3: {
                    g.drawString("金币:" + Player.playerDataList.get(3).getGold(),1050,1000);
                }
                break;
                case 4: {
                    g.drawString("金币:" + Player.playerDataList.get(4).getGold(),470,1000);
                }
                break;
            }
        }
    }

//    public void drawBuildingBook(Graphics g)
//    {
//        Font font = new Font("微软雅黑", Font.PLAIN, 24);
//        g.setFont(font);
//        for(int i = 0 ; i < Client.playerNum;i++)
//        {
//            switch(i) {
//                case 0:
//                {
//                    g.setColor(Color.ORANGE);
//                    g.fillRect(160,420,40,60);
//                    g.setColor(Color.GRAY);
//                    g.drawString("地",167,440);
//                    g.drawString("产",167,475);
//                }
//                break;
//                case 1: {
//                    g.setColor(Color.ORANGE);
//                    g.fillRect(890,20,40,60);
//                    g.setColor(Color.GRAY);
//                    g.drawString("地",897,40);
//                    g.drawString("产",897,75);
//                }
//                break;
//                case 2: {
//                    g.setColor(Color.ORANGE);
//                    g.fillRect(1650,420,40,60);
//                    g.setColor(Color.GRAY);
//                    g.drawString("地",1657,440);
//                    g.drawString("产",1657,475);
//                }
//                break;
//                case 3: {
//                    g.setColor(Color.ORANGE);
//                    g.fillRect(1170,840,40,60);
//                    g.setColor(Color.GRAY);
//                    g.drawString("地",1177,840);
//                    g.drawString("产",1177,895);
//                }
//                break;
//                case 4: {
//                    g.setColor(Color.ORANGE);
//                    g.fillRect(270,420,40,60);
//                    g.setColor(Color.GRAY);
//                    g.drawString("地",277,440);
//                    g.drawString("产",277,475);;
//                }
//                break;
//            }
//        }
//    }

    public void initGame()
    {
        readyButton.setVisible(false);

    }

    public void diceAnimation()
    {
        int[] timer = {0};
        int animationDuration = 12;

        Timer swingTimer = new Timer(500, e -> {
            switch (timer[0] % 4) {
                case 0 -> diceButton.setIcon(diceAnim1);
                case 1 -> diceButton.setIcon(diceAnim2);
                case 2 -> diceButton.setIcon(diceAnim3);
                case 3 -> diceButton.setIcon(diceAnim4);
            }
            timer[0]++;
            if (timer[0] >= animationDuration) {
                ((Timer) e.getSource()).stop();
            }
        });

        swingTimer.start();

    }
}
