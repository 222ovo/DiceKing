package Client;

import java.io.Serializable;

public class BuildingGrid extends Grid implements Serializable {

    public BuildingGrid(int x, int y)
    {
        super(x,y);
    }
    //普通格子，空实现
    public void stepEvent() {}
}
