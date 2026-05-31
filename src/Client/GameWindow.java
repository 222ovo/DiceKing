package Client;

import Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameWindow extends JPanel{
    //当前客户端玩家
    Player player;
    //骰子
    Dice dice = new Dice();
    public boolean isDicing = false;
    private Grid grid;  //玩家当前所在的格子
    public int round;   //清算倒计时
    public int magnification = Setting.MAGIFICATION;    //基础倍率
    public int baseRent = Setting.BASE_RENT; //基础租金
    private int currentPlayerID = -1;
    //游戏主框架
    JFrame mainFrame = new JFrame("Game");
    JLayeredPane layeredPane = new JLayeredPane();
    //游戏背景图
    ImageIcon back = new ImageIcon("Image/BackGround.jpg");
    JLabel bg = new JLabel(back);
    //准备按钮图
    ImageIcon ready = new ImageIcon("Image/ready.png");
    private JButton readyButton = new JButton(ready);
    //取消准备按钮图
    ImageIcon unReady = new ImageIcon("Image/unready.png");
    //购买按钮图
    ImageIcon buy = new ImageIcon("Image/Buy.png");
    private JButton buyButton = new JButton(buy);
    //结束按钮图
    ImageIcon over = new ImageIcon("Image/Over.png");
    JButton overButton = new JButton(over);
    //广播文字框
    Image broadcast = Toolkit.getDefaultToolkit()
            .getImage(getClass().getResource("BroadcastField.png"));
    private String broadText = "玩家获得了2000金币";
    private final Timer timer;
    //骰子的图片
    ImageIcon dice1 = new ImageIcon("Image/Dice1.png");
    ImageIcon dice2 = new ImageIcon("Image/Dice2.png");
    ImageIcon dice3 = new ImageIcon("Image/Dice3.png");
    ImageIcon dice4 = new ImageIcon("Image/Dice4.png");
    ImageIcon dice5 = new ImageIcon("Image/Dice5.png");
    ImageIcon dice6 = new ImageIcon("Image/Dice6.png");
    //投资帧动画
    ImageIcon diceAnim1 = new ImageIcon("Image/DiceFrame1.png");
    ImageIcon diceAnim2 = new ImageIcon("Image/DiceFrame2.png");
    ImageIcon diceAnim3 = new ImageIcon("Image/DiceFrame3.png");
    ImageIcon diceAnim4 = new ImageIcon("Image/DiceFrame4.png");
    JButton diceButton = new JButton(dice1);
    ImageIcon win0 = new ImageIcon("Image/0Win.png");
    ImageIcon win1 = new ImageIcon("Image/1Win.png");
    ImageIcon win2 = new ImageIcon("Image/2Win.png");
    ImageIcon win3 = new ImageIcon("Image/3Win.png");

    ImageIcon win4 = new ImageIcon("Image/4Win.png");
    JLabel win = new JLabel(win0);
    public GameWindow()
    {
        this.setBounds(0, 0,
                back.getIconWidth(),
                back.getIconHeight());
        setOpaque(false);
        bg.setSize(back.getIconWidth(),back.getIconHeight());

        mainFrame.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));

        win.setSize(win.getIcon().getIconWidth(),win.getIcon().getIconWidth());
        win.setBounds(new Rectangle(340,-10,win.getIcon().getIconWidth(),win.getIcon().getIconWidth()));
        mainFrame.getLayeredPane().add(win,new Integer(Integer.MIN_VALUE));
        layeredPane.add(win, JLayeredPane.DEFAULT_LAYER);
        win.setVisible(false);

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

        buyButton.setContentAreaFilled(false);
        buyButton.setBorder(null);
        buyButton.setBounds(1920 / 2 - 2 * buyButton.getIcon().getIconWidth() - 120, 1080 / 2  + 70, buyButton.getIcon().getIconWidth()-1,buyButton.getIcon().getIconHeight());
        buyButton.setOpaque(false);
        buyButton.setVisible(false);
        mainFrame.getLayeredPane().add(buyButton);

        overButton.setContentAreaFilled(false);
        overButton.setBorder(null);
        overButton.setBounds(1920 / 2 + overButton.getIcon().getIconWidth()/2 - 40, 1080 / 2  + 70, overButton.getIcon().getIconWidth()-1,overButton.getIcon().getIconHeight());
        overButton.setOpaque(false);
        overButton.setVisible(false);
        mainFrame.getLayeredPane().add(overButton);

        //定期给服务端发送消息，避免线程阻塞
        timer = new Timer(1000,e -> {
            try {
                player.getOutputStream().writeUTF("Fresh");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        timer.start();
        readyButton.addActionListener(e -> {
            if(!player.isReady) {
                player.isReady = true;
                readyButton.setIcon(unReady);
                System.out.println("玩家" + player.getId() + "已准备");
                try {
                    player.getOutputStream().writeUTF("ready");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(player.isReady){
                player.isReady = false;
                readyButton.setIcon(ready);
                System.out.println("玩家" + player.getId() + "取消准备");
                try {
                    player.getOutputStream().writeUTF("unready");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buyButton.addActionListener(e -> {
            Building building = ((BuildingGrid)grid).getBuilding();
            if(Player.playerDataList.get(player.getId()).getGold() < building.getPrice())
            {
                System.out.println("你的金币不足以购买" + building.getName());
                setBroadText("你的金币不足以购买" + building.getName());
                return;
            }

            building.setId(player.getId());
            player.sendMsg("Buy" + building.getName() + "|" + building.getPrice());
            setBroadText("你购买了" + building.getName());
            buyButton.setVisible(false);
            repaint();
        });

        overButton.addActionListener(e -> {
            overButton.setVisible(false);
            buyButton.setVisible(false);
            player.sendMsg("Over");
            player.isRound = false;
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
                if(player.isRound)
                    rollDice();
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
        //画房产本
        drawBuildingBook(g);
        //画地图
        drawMap(g);
        //在地图上绘制玩家
        drawPlayersOnMap(g);
        //绘制当前玩家的边框
        drawCurrentPlayer(g);
        //绘制广播文字
        drawBroadText(g);
        //绘制地图上的建筑
        drawBuilding(g);
        //画回合数
        drawRound(g);
    }

    //画地图
    private void drawMap(Graphics g)
    {
        for(Grid grid : MapLoader.map.values())
        {
            grid.draw(g);
        }
    }

    private void drawCurrentPlayer(Graphics g)
    {
        g.setColor(Color.GREEN);
        switch(currentPlayerID)
        {
            case 0:
                g.drawRect(45,415,110,110);
                break;
            case 1:
                g.drawRect(775,15,110,110);
                break;
            case 2:
                g.drawRect(1535,415,110,110);
                break;
            case 3:
                g.drawRect(1055,835,110,110);
                break;
            case 4:
                g.drawRect(475,835,110,110);
                break;
        }
    }
    private void drawPlayers(Graphics g)
    {
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            switch(i) {
                case 0:
                {
                    g.setColor(Color.GRAY);
                    if(Player.playerDataList.get(0).isAlive) g.setColor(Color.PINK);
                    g.fillRect(50, 420, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);

                    if(Player.playerDataList.get(0).isAlive) g.setColor(Color.YELLOW);
                    g.fillRect(70, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(115, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 0) {
                        g.drawString("我", 85, 550);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(0).getGold(),40,580);
                }
                break;
                case 1: {
                    g.setColor(Color.GRAY);
                    if(Player.playerDataList.get(1).isAlive) g.setColor(Color.YELLOW);
                    g.fillRect(780, 20, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);

                    if(Player.playerDataList.get(1).isAlive) g.setColor(Color.WHITE);
                    g.fillRect(800, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(845, 40, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 1) {
                        g.drawString("我", 815, 150);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(1).getGold(),770,180);
                }
                break;
                case 2: {
                    g.setColor(Color.GRAY);
                    if(Player.playerDataList.get(2).isAlive) g.setColor(Color.RED);
                    g.fillRect(1540, 420, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);

                    if(Player.playerDataList.get(2).isAlive) g.setColor(Color.YELLOW);
                    g.fillRect(1560, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1605, 440, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 2) {
                        g.drawString("我", 1575, 550);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(2).getGold(),1530,580);
                }
                break;
                case 3: {
                    g.setColor(Color.GRAY);
                    if(Player.playerDataList.get(3).isAlive) g.setColor(Color.ORANGE);
                    g.fillRect(1060, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);

                    if(Player.playerDataList.get(3).isAlive) g.setColor(Color.GREEN);
                    g.fillRect(1080, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1125, 860, Setting.PLAYER_EYE_WIDTH, Setting.PLAYER_EYE_HEIGHT);
                    if (player.getId() == 3) {
                        g.drawString("我", 1095, 970);
                    }
                    g.drawString("金币:" + Player.playerDataList.get(3).getGold(),1050,1000);
                }
                break;
                case 4: {
                    g.setColor(Color.GRAY);
                    if(Player.playerDataList.get(4).isAlive) g.setColor(Color.GREEN);
                    g.fillRect(480, 840, Setting.PLAYER_HEAD_WIDTH, Setting.PLAYER_HEAD_HEIGHT);

                    if(Player.playerDataList.get(4).isAlive) g.setColor(Color.BLUE);
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

    public void drawBroadText(Graphics g)
    {
        g.drawImage(broadcast,495,340,this);
        Font font = new Font("微软雅黑", Font.PLAIN, 24);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(broadText,530, 368);
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

    public void drawBuildingBook(Graphics g)
    {
        Font font = new Font("微软雅黑", Font.PLAIN, 24);
        g.setFont(font);
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            switch(i) {
                case 0:
                {
                    g.setColor(Color.ORANGE);
                    g.fillRect(160,420,40,60);
                    g.setColor(Color.GRAY);
                    g.drawString("地",167,440);
                    g.drawString("产",167,475);
                }
                break;
                case 1: {
                    g.setColor(Color.ORANGE);
                    g.fillRect(890,20,40,60);
                    g.setColor(Color.GRAY);
                    g.drawString("地",897,40);
                    g.drawString("产",897,75);
                }
                break;
                case 2: {
                    g.setColor(Color.ORANGE);
                    g.fillRect(1650,420,40,60);
                    g.setColor(Color.GRAY);
                    g.drawString("地",1657,440);
                    g.drawString("产",1657,475);
                }
                break;
                case 3: {
                    g.setColor(Color.ORANGE);
                    g.fillRect(1170,840,40,60);
                    g.setColor(Color.GRAY);
                    g.drawString("地",1177,840);
                    g.drawString("产",1177,895);
                }
                break;
                case 4: {
                    g.setColor(Color.ORANGE);
                    g.fillRect(270,420,40,60);
                    g.setColor(Color.GRAY);
                    g.drawString("地",277,440);
                    g.drawString("产",277,475);;
                }
                break;
            }
        }
    }

    public void initGame()
    {
        timer.stop();
        readyButton.setVisible(false);
        round = Client.playerNum * Client.playerNum;
        repaint();
    }

    public void rollDice()
    {
        if(isDicing) return;
        isDicing = true;
        int[] timer = {0};
        int animationDuration = 12;
        int[] points = {0};

        Timer swingTimer = new Timer(100, e -> {
            if (timer[0] >= animationDuration) {
                points[0] = dice.RollDice(player);
                switch (points[0]) {
                    case 1 -> diceButton.setIcon(dice1);
                    case 2 -> diceButton.setIcon(dice2);
                    case 3 -> diceButton.setIcon(dice3);
                    case 4 -> diceButton.setIcon(dice4);
                    case 5 -> diceButton.setIcon(dice5);
                    case 6 -> diceButton.setIcon(dice6);
                }
                ((Timer) e.getSource()).stop();
                isDicing = false;
                player.Move(points[0]);
                overButton.setVisible(true);
                return;
            }
            switch (timer[0] % 4) {
                case 0 -> diceButton.setIcon(diceAnim1);
                case 1 -> diceButton.setIcon(diceAnim2);
                case 2 -> diceButton.setIcon(diceAnim3);
                case 3 -> diceButton.setIcon(diceAnim4);
            }
            timer[0]++;
        });

        swingTimer.start();
    }

    public void drawRound(Graphics g)
    {
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("距离收租金还剩" + round + "回合" + "，当前租金：" + baseRent*magnification,530,400);
    }
    public void drawPlayersOnMap(Graphics g)
    {
        if(player.getGameState() == GameState.RUNNING)
        {
            for(int i = 0 ; i < Client.playerNum;i++)
            {
                PlayerData playerData = Player.playerDataList.get(i);
                g.setColor(playerData.getColor());
                g.fillRect(playerData.x,playerData.y,Setting.PLAYER_WIDTH,Setting.PLAYER_HEIGHT);
            }
        }
    }

    public void drawBuilding(Graphics g)
    {
        for(Grid grid : MapLoader.map.values())
        {
            if(grid instanceof BuildingGrid)
            {
                Building building = ((BuildingGrid)grid).getBuilding();
                if(building == null || building.getId() == -1) continue;

                switch(building.getId())
                {
                    case 0:
                        g.setColor(Setting.PLAYER0_COLOR);
                        break;
                    case 1:
                        g.setColor(Setting.PLAYER1_COLOR);
                        break;
                    case 2:
                        g.setColor(Setting.PLAYER2_COLOR);
                        break;
                    case 3:
                        g.setColor(Setting.PLAYER3_COLOR);
                        break;
                    case 4:
                        g.setColor(Setting.PLAYER4_COLOR);
                        break;
                }
                g.fillRect(grid.x, grid.y + Setting.GRID_HEIGHT-5,Setting.GRID_WIDTH,5);
            }
        }
    }

    public JButton getBuyButton()
    {
        return buyButton;
    }

    public JButton getOverButton()
    {
        return overButton;
    }

    public void setBroadText(String s)
    {
        this.broadText = s;
        repaint();
    }

    public void updateGrid(Grid grid)
    {
        this.grid = grid;
        grid.stepEvent(player);
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public void checkGameOver()
    {
        PlayerData winPlayer = null;
        int n = 0;
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            PlayerData playerData = Player.playerDataList.get(i);
            if(playerData.isAlive) {
                n++;
                winPlayer = playerData;
            }
        }
        if(n <= 1) {
            System.out.println("游戏结束");
            gameOver(winPlayer);
        }
        else{
            System.out.println("游戏继续");
        }
    }

    public void gameOver(PlayerData playerData)
    {
        win.setVisible(true);
        diceButton.setVisible(false);
        switch (playerData.getId())
        {
            case 0:
                win.setIcon(win0);
                break;
            case 1:
                win.setIcon(win1);
                break;
            case 2:
                win.setIcon(win2);
                break;
            case 3:
                win.setIcon(win3);
                break;
            case 4:
                win.setIcon(win4);
                break;
        }
    }
}
