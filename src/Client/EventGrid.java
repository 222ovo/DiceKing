package Client;

import java.io.Serializable;

public class EventGrid extends Grid implements Serializable {

    public EventGrid(int x,int y)
    {
        super(x,y);
        color = Setting.EVENT_GRID_COLOR;
    }
    //事件格子
    @Override
    public void stepEvent() {

    }
}
