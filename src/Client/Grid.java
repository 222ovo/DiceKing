package Client;

import java.awt.*;
import java.io.Serializable;

public abstract class Grid implements Serializable {
    int x;
    int y;
    int width = Setting.GRID_WIDTH;
    int height = Setting.GRID_HEIGHT;
    Color color;
    public Grid(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    //踩中格子的效果
    public abstract void stepEvent();

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
    }
}
