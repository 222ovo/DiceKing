package Client;

import java.awt.*;
import java.io.Serializable;

public class BuildingGrid extends Grid implements Serializable {

    private Building building;
    public BuildingGrid(int x, int y)
    {
        super(x,y);
        color = Setting.BUILDING_GRID_COLOR;
    }
    //普通格子，空实现
    public void stepEvent() {}

    @Override
    public void draw(Graphics g)
    {

    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
