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
    //建筑格子
    public void stepEvent(Player player) {
        if(building == null)
        {
            System.out.println("未找到建筑");
            return;
        }

        if(building.getId() == -1)
        {
            player.getGameWindow().getBuyButton().setVisible(true);
            player.getGameWindow().setBroadText("是否购费" + building.getPrice() + "元来购买" + building.getName() + "?");
            player.getGameWindow().repaint();
        }
        else if(building.getId() != -1 && building.getId() != player.getId())
        {
            //付钱
            //给玩家building的所有者付钱
            player.sendMsg("Pay" + building.getRevenue() + "|" + building.getId());
        }
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
