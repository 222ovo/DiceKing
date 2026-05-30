package Client;

import java.awt.*;
import java.io.Serializable;

public class EventGrid extends Grid implements Serializable{

    private final eventInterface event;
    private String eventInfo;   //事件描述
    public EventGrid(GridPos gridPos,String eventInfo,eventInterface event)
    {
        super(gridPos);
        color = Setting.EVENT_GRID_COLOR;
        this.eventInfo = eventInfo;
        this.event = event;
    }

    //事件格子
    @Override
    public void stepEvent(Player player) {
        event.event(player);
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
        font = new Font("微软雅黑", Font.PLAIN, 18);
        g.setFont(font);
        g.drawString(eventInfo, x+10, y + 45);
    }

}
