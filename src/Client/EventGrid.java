package Client;

import java.awt.*;
import java.io.Serializable;

public class EventGrid extends Grid implements Serializable {

    public EventGrid(GridPos gridPos)
    {
        super(gridPos);
        color = Setting.EVENT_GRID_COLOR;
    }
    //事件格子
    @Override
    public void stepEvent(Player player) {

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
        font = new Font("微软雅黑", Font.PLAIN, 40);
        g.setFont(font);
        g.drawString("事件", x+10, y + 45);
        g.drawString("?",x+38,y+80);
    }
}
