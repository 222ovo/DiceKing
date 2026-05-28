package Client;

import java.io.Serializable;

//移动格子
public class MoveGrid extends Grid implements Serializable {

    public MoveGrid(int x, int y)
    {
        super(x,y);
    }
    @Override
    public void stepEvent() {

    }
}
