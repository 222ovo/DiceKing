package Client;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel{

    JFrame mainFrame = new JFrame("Game");
    JLayeredPane layeredPane = new JLayeredPane();
    ImageIcon back = new ImageIcon("BackGround.jpg");
    JLabel bg = new JLabel(back);
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

        (new Timer(16, (e) -> {
            this.repaint();
        })).start();
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
        for(int i = 0 ; i < /*Client.playerNum*/5;i++)
        {
            switch(i)
            {
                case 0:
                    g.setColor(Color.PINK);
                    g.fillRect(50,420,Setting.PLAYER_HEAD_WIDTH,Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.YELLOW);
                    g.fillRect(70,440,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(115,440,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    break;
                case 1:
                    g.setColor(Color.YELLOW);
                    g.fillRect(780,20,Setting.PLAYER_HEAD_WIDTH,Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(800,40,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(845,40,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    break;
                case 2:
                    g.setColor(Color.RED);
                    g.fillRect(1540,420,Setting.PLAYER_HEAD_WIDTH,Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.YELLOW);
                    g.fillRect(1560,440,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1605,440,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    break;
                case 3:
                    g.setColor(Color.YELLOW);
                    g.fillRect(1060,840,Setting.PLAYER_HEAD_WIDTH,Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(1080,860,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(1125,860,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    break;
                case 4:
                    g.setColor(Color.YELLOW);
                    g.fillRect(480,840,Setting.PLAYER_HEAD_WIDTH,Setting.PLAYER_HEAD_HEIGHT);
                    g.setColor(Color.BLUE);
                    g.fillRect(500,860,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    g.fillRect(545,860,Setting.PLAYER_EYE_WIDTH,Setting.PLAYER_EYE_HEIGHT);
                    break;
            }
        }
    }
}

