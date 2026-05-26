package Client;

import java.awt.*;

public abstract class Grid {
    int x;
    int y;
    int width = Setting.GRID_WIDTH;
    int height = Setting.GRID_HEIGHT;
    Color color;
    public Grid(int x,int y)
    {
        x = this.x;
        y = this.y;
    }
    //踩中格子的效果
    public abstract void stepEvent();

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawRect(x,y,width,height);
    }
}
