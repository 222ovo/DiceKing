package Client;

import java.util.ArrayList;

public class MapLoader {
    public static final String MAP_DATA = "map.dat";
    public static final ArrayList<Grid> grids = new ArrayList<>();  //存储所有格子的列表
    //第一次游戏初始化地图
    public static void initialMap()
    {
        BuildingGrid grid1 = new BuildingGrid(300,200,null);
        grids.add(grid1);
        BuildingGrid grid2 = new BuildingGrid(400,200,new Building("火锅街",1500,200));
        grids.add(grid2);
        BuildingGrid grid3 = new BuildingGrid(500,200,new Building("汉堡市",1000,150));
        grids.add(grid3);
        BuildingGrid grid4 = new BuildingGrid(600,200,new Building("烧烤城",1800,250));
        grids.add(grid4);
        BuildingGrid grid5 = new BuildingGrid(700,200,new Building("麻辣烫店",600,80));
        grids.add(grid5);
        BuildingGrid grid6 = new BuildingGrid(800,200,new Building("糖果世界",2500,400));
        grids.add(grid6);
        MoveGrid grid7 = new MoveGrid(900,200,4);
        grids.add(grid7);
        BuildingGrid grid8 = new BuildingGrid(1000,200,new Building("皇家曲奇店",1200,170));
        grids.add(grid8);
        BuildingGrid grid9 = new BuildingGrid(1100,200,new Building("北京麻辣香锅",2800,450));
        grids.add(grid9);
        BuildingGrid grid10 = new BuildingGrid(1200,200,new Building("超级大饭店",2000,350));
        grids.add(grid10);
        EventGrid grid11 = new EventGrid(1300,200);
        grids.add(grid11);
        BuildingGrid grid12 = new BuildingGrid(1300,300,new Building("良子焖子",900,130));
        grids.add(grid12);
        BuildingGrid grid13 = new BuildingGrid(1300,400,new Building("良子大饼卷肉",700,90));
        grids.add(grid13);
        BuildingGrid grid14 = new BuildingGrid(1300,500,new Building("良子板面",1100,160));
        grids.add(grid14);
        BuildingGrid grid15 = new BuildingGrid(1300,600,new Building("良子手工饺子",1300,180));
        grids.add(grid15);
        FateGrid grid16 = new FateGrid(1300,700);
        grids.add(grid16);
        BuildingGrid grid17 = new BuildingGrid(1200,700,new Building("良子大锅菜",1400,190));
        grids.add(grid17);
        MoveGrid grid18 = new MoveGrid(1100,700,-2);
        grids.add(grid18);
        BuildingGrid grid19 = new BuildingGrid(1000,700,new Building("良子品铺",1900,260));
        grids.add(grid19);
        BuildingGrid grid20 = new BuildingGrid(900,700,new Building("炉石酒馆",2200,370));
        grids.add(grid20);
        EventGrid grid21 = new EventGrid(800,700);
        grids.add(grid21);
        BuildingGrid grid22 = new BuildingGrid(700,700,new Building("夜雀食堂",1600,210));
        grids.add(grid22);
        BuildingGrid grid23 = new BuildingGrid(600,700,new Building("漏斗蛋糕店",2100,360));
        grids.add(grid23);
        BuildingGrid grid24 = new BuildingGrid(500,700,new Building("会员制餐厅",1145,180));
        grids.add(grid24);
        FateGrid grid25 = new FateGrid(400,700);
        grids.add(grid25);
        BuildingGrid grid26 = new BuildingGrid(300,700,new Building("疯狂星期寺",400,50));
        grids.add(grid26);
        BuildingGrid grid27 = new BuildingGrid(300,600,new Building("巧乐兹批发厂",1700,220));
        grids.add(grid27);
        MoveGrid grid28 = new MoveGrid(300,500,-3);
        grids.add(grid28);
        BuildingGrid grid29 = new BuildingGrid(300,400,new Building("山姆超市",3000,500));
        grids.add(grid29);
        BuildingGrid grid30 = new BuildingGrid(300,300,new Building("千溢小炒",1050,160));
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
