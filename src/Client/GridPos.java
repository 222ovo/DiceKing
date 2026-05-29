package Client;

import java.util.Objects;

//坐标类
public class GridPos {
    public int x;
    public int y;

    private int scale = 100;    //缩放比例
    public GridPos(int x, int y)
    {
        this.x = changeToMeshPos(x);
        this.y = changeToMeshPos(y);
    }

    public int changeToWorldPos(int point)
    {
        return point*scale;
    }

    public int changeToMeshPos(int point)
    {
        return point/scale;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridPos)) return false;
        GridPos gridPos = (GridPos) o;
        return x == gridPos.x && y == gridPos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
