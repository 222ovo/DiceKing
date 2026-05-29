package Client;

import java.awt.*;
import java.io.Serializable;

public class BuildingGrid extends Grid implements Serializable {

    private Building building;
    public BuildingGrid(GridPos gridPos,Building building)
    {
        super(gridPos);
        this.building = building;
        color = Setting.BUILDING_GRID_COLOR;
    }
    //普通格子，空实现
    public void stepEvent() {}

    @Override
    public void draw(Graphics g)
    {
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
        if(building == null) return;
        g.drawString(building.getName(), x,y+35);
        g.drawString("价格：" + building.getPrice(),x,y+60);
        g.drawString("收益：" + building.getRevenue(),x,y+85);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
