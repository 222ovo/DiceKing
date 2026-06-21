package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Thread.sleep;

public class GameLauncher extends JFrame {

    private JButton startBtn, aboutBtn, exitBtn;

    public GameLauncher()
    {
        setTitle("大富翁 · 局域网对战");
        setSize(1028, 628);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);//去掉标题栏，更美观

        //主面板 —— 自定义绘制渐变背景
        JPanel mainPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(25, 30, 60),
                        0, getHeight(), new Color(15, 18, 40)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                //标题文字
                g2d.setColor(new Color(220, 190, 120));
                g2d.setFont(new Font("楷体_GB2312", Font.BOLD, 78));
                String title = " 壕友局";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(title)) / 2;
                g2d.drawString(title, x, 140);

                g2d.setColor(new Color(160, 170, 210));
                g2d.setFont(new Font("楷体_GB2312", Font.PLAIN, 28));
                String sub = "— 局域网联机对战 —";
                fm = g2d.getFontMetrics();
                x = (getWidth() - fm.stringWidth(sub)) / 2;
                g2d.drawString(sub, x + 15, 200);

                g2d.setFont(new Font("微软雅黑", Font.PLAIN, 18));
                g2d.setColor(new Color(100, 108, 138));
                g2d.drawString("v1.0.0", 950, 620);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);

        // ---- 创建三个按钮 ----
        startBtn = createStyledButton("  开始游戏");
        aboutBtn = createStyledButton("  关 于");
        exitBtn  = createStyledButton("  退出游戏");

        // ---- 布局 ----
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 调整 insets：上间距加大让按钮整体下沉一点，左右间距缩小让按钮变宽更协调
        gbc.insets = new Insets(18, 60, 18, 60);

        gbc.weighty = 0.5;
        mainPanel.add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0;

        mainPanel.add(startBtn, gbc);
        mainPanel.add(aboutBtn, gbc);
        mainPanel.add(exitBtn, gbc);

        // 在最后一个按钮下方也加一个垂直填充
        gbc.weighty = 0.3;
        mainPanel.add(Box.createVerticalGlue(), gbc);

        // ---- 鼠标事件（悬停高亮） ----
        addHoverEffect(startBtn);
        addHoverEffect(aboutBtn);
        addHoverEffect(exitBtn);

        // ---- 点击事件 ----
        startBtn.addActionListener(e -> onStart());
        aboutBtn.addActionListener(e -> onAbout());
        exitBtn.addActionListener(e -> onExit());
    }

    // ==================== 工具方法 ====================

    //创建统一样式的按钮（圆角）
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                super.paintComponent(g);
                g2d.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(130, 155, 205));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
                g2d.dispose();
            }
        };
        btn.setFont(new Font("楷体_GB2312", Font.BOLD, 19));
        btn.setPreferredSize(new Dimension(270, 54));
        btn.setBackground(new Color(43, 59, 118));
        btn.setForeground(new Color(226, 211, 176));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    //添加鼠标悬停高亮效果
    private void addHoverEffect(JButton btn)
    {
        Color originalBg = btn.getBackground();      //原始深蓝
        Color hoverBg = new Color(68, 91, 162);       // 悬停亮蓝
        Color pressedBg = new Color(31, 41, 81);       // 按下深色
        Color originalFg = btn.getForeground();
        Color hoverFg = new Color(242, 222, 142);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverBg);
                btn.setForeground(hoverFg);
                btn.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(180, 210, 250), 2),
                        BorderFactory.createEmptyBorder(4, 26, 4, 26)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(originalBg);
                btn.setForeground(originalFg);
                btn.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(130, 155, 205), 2),
                        BorderFactory.createEmptyBorder(4, 24, 4, 24)
                ));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btn.setBackground(pressedBg);
                btn.setForeground(new Color(194, 174, 126));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(hoverBg);
                btn.setForeground(hoverFg);
                playClickSound();//点击音效
            }
        });
    }

    private void playClickSound()
    {
        Toolkit.getDefaultToolkit().beep();
    }

    // ==================== 事件处理 ====================

    private void onStart() {
        new Thread(Client::startGame).start();

        dispose();
    }

    private void onAbout() {
        JOptionPane.showMessageDialog(this,
                " DiceKing · 局域网联机版\n\n" +
                        "版本：v2.0\n" +
                        "完成时间 2026.6.20" +
                        "类型：局域网多人在线桌游\n" +
                        "玩法：掷骰子、买地产、收租金\n" +
                        "开发人员：王迁浩 8002125150\n\n" +
                        "开发人员：沈权 8002125147\n\n" +
                        "班级 软件工程2505班\n" +
                        " 请确保所有玩家在同一局域网内",
                " 关于",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void onExit() {
        int opt = JOptionPane.showConfirmDialog(this,
                "确定要退出游戏吗？",
                "退出确认",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ==================== 入口 ====================

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            GameLauncher launcher = new GameLauncher();
            launcher.setVisible(true);
        });
    }
}