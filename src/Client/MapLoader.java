package Client;

import java.util.ArrayList;

public class MapLoader {
    public static final String MAP_DATA = "map.dat";
    public static final ArrayList<Grid> grids = new ArrayList<>();  //存储所有格子的列表
    //第一次游戏初始化地图
    public static void initialMap()
    {
        BuildingGrid grid1 = new BuildingGrid(300,200);
        grids.add(grid1);
        BuildingGrid grid2 = new BuildingGrid(400,200);
        grids.add(grid2);
        BuildingGrid grid3 = new BuildingGrid(500,200);
        grids.add(grid3);
        BuildingGrid grid4 = new BuildingGrid(600,200);
        grids.add(grid4);
        BuildingGrid grid5 = new BuildingGrid(700,200);
        grids.add(grid5);
        BuildingGrid grid6 = new BuildingGrid(800,200);
        grids.add(grid6);
        BuildingGrid grid7 = new BuildingGrid(900,200);
        grids.add(grid7);
        BuildingGrid grid8 = new BuildingGrid(1000,200);
        grids.add(grid8);
        BuildingGrid grid9 = new BuildingGrid(1100,200);
        grids.add(grid9);
        BuildingGrid grid10 = new BuildingGrid(1200,200);
        grids.add(grid10);
        BuildingGrid grid11 = new BuildingGrid(1300,200);
        grids.add(grid11);
        BuildingGrid grid12 = new BuildingGrid(1300,300);
        grids.add(grid12);
        BuildingGrid grid13 = new BuildingGrid(1300,400);
        grids.add(grid13);
        BuildingGrid grid14 = new BuildingGrid(1300,500);
        grids.add(grid14);
        BuildingGrid grid15 = new BuildingGrid(1300,600);
        grids.add(grid15);
        BuildingGrid grid16 = new BuildingGrid(1300,700);
        grids.add(grid16);
        BuildingGrid grid17 = new BuildingGrid(1200,700);
        grids.add(grid17);
        BuildingGrid grid18 = new BuildingGrid(1100,700);
        grids.add(grid18);
        BuildingGrid grid19 = new BuildingGrid(1000,700);
        grids.add(grid19);
        BuildingGrid grid20 = new BuildingGrid(900,700);
        grids.add(grid20);
        BuildingGrid grid21 = new BuildingGrid(800,700);
        grids.add(grid21);
        BuildingGrid grid22 = new BuildingGrid(700,700);
        grids.add(grid22);
        BuildingGrid grid23 = new BuildingGrid(600,700);
        grids.add(grid23);
        BuildingGrid grid24 = new BuildingGrid(500,700);
        grids.add(grid24);
        BuildingGrid grid25 = new BuildingGrid(400,700);
        grids.add(grid25);
        BuildingGrid grid26 = new BuildingGrid(300,700);
        grids.add(grid26);
        BuildingGrid grid27 = new BuildingGrid(300,600);
        grids.add(grid27);
        BuildingGrid grid28 = new BuildingGrid(300,500);
        grids.add(grid28);
        BuildingGrid grid29 = new BuildingGrid(300,400);
        grids.add(grid29);
        BuildingGrid grid30 = new BuildingGrid(300,300);
        grids.add(grid30);
    }
    //从文件中加载已经生成好的地图
    public static void loadMapFromFile()
    {

    }
    //将生成好的地图保存到文件
    public static void saveMapToFile()
    {

    }
}
