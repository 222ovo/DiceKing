package Client;

import java.awt.*;
import java.io.Serializable;

public class FateGrid extends Grid implements Serializable {
    public FateGrid(int x,int y)
    {
        super(x,y);
        color = Setting.FATE_GRID_COLOR;
    }


    @Override
    public void stepEvent() {

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
        g.drawString("命运", x+10, y + 45);
        g.drawString("?",x+38,y+80);
    }
}
