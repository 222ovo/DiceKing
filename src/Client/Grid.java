package Client;

import java.awt.*;
import java.io.Serializable;

public abstract class Grid implements Serializable {
    public int x;
    public int y;
    protected GridPos gridPos;  //网格坐标
    int width = Setting.GRID_WIDTH;
    int height = Setting.GRID_HEIGHT;
    Color color;
    public Grid(GridPos gridPos)
    {
        this.gridPos = gridPos;
        this.x = GridPos.changeToWorldPos(gridPos.x);
        this.y = GridPos.changeToWorldPos(gridPos.y);
    }
    //踩中格子的效果
    public abstract void stepEvent(Player player);

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(gridPos.x,gridPos.y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(gridPos.x,gridPos.y,width,height);
    }

    public GridPos getGridPos()
    {
        return gridPos;
    }
}
