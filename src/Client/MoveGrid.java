package Client;

import java.awt.*;
import java.io.Serializable;

//移动格子
public class MoveGrid extends Grid implements Serializable {

    private int move;
    public MoveGrid(GridPos gridPos,int move)
    {
        super(gridPos);
        this.move = move;
        color = Setting.MOVE_GIRD_COLOR;
    }
    //玩家按照移动数移动
    @Override
    public void stepEvent(Player player) {
        player.Move(move);
    }

    @Override
    public void draw(Graphics g)
    {
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
        if(move > 0) {
            font = new Font("微软雅黑", Font.PLAIN, 40);
            g.setFont(font);
            g.drawString("前进", x+10, y + 45);
            g.drawString(String.valueOf(move),x+38,y+80);
        }
        else if(move < 0)
        {
            font = new Font("微软雅黑", Font.PLAIN, 40);
            g.setFont(font);
            g.drawString("后退", x+10, y + 45);
            g.drawString(String.valueOf(move),x+30,y+80);
        }
    }
}
