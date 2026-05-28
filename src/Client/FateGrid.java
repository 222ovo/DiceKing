package Client;

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
}
