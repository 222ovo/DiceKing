package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapLoader {
    public static final ArrayList<fateInterface> fateList = new ArrayList<>();
    public static final String MAP_DATA = "map.dat";
    public static final Map<GridPos,Grid> map = new HashMap<>();    //将格子以网格坐标存储
    //第一次游戏初始化地图
    public static void initialMap()
    {
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + 500);
            player.getGameWindow().setBroadText("你发现了新的食谱,获得利润500");});
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + -200);
            player.getGameWindow().setBroadText("在尝菜时食物中毒,花费200元治疗费");
        });
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + 300);
            player.getGameWindow().setBroadText("在厨神大赛获得300元奖励");});
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + -100);
            player.getGameWindow().setBroadText("遇到吃霸王餐的顾客,损失100元");
        });
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + -50);
            player.getGameWindow().setBroadText("买到了生瓜蛋子,损失50元");
        });
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + 800);
            player.getGameWindow().setBroadText("一位顾客在你的店拿出了百万英镑,名气大涨，获得800元");
        });
        fateList.add(player -> {});
        fateList.add(player -> {});
        EventGrid grid1 = new EventGrid(new GridPos(300,200),"起点",player -> {
        });
        map.put(grid1.getGridPos(),grid1);
        BuildingGrid grid2 = new BuildingGrid(new GridPos(400,200),new Building("火锅街",1500,200));
        map.put(grid2.getGridPos(),grid2);
        BuildingGrid grid3 = new BuildingGrid(new GridPos(500,200),new Building("汉堡市",1000,150));
        map.put(grid3.getGridPos(),grid3);
        BuildingGrid grid4 = new BuildingGrid(new GridPos(600,200),new Building("烧烤城",1800,250));
        map.put(grid4.getGridPos(),grid4);
        BuildingGrid grid5 = new BuildingGrid(new GridPos(700,200),new Building("麻辣烫店",600,80));
        map.put(grid5.getGridPos(),grid5);
        BuildingGrid grid6 = new BuildingGrid(new GridPos(800,200),new Building("糖果世界",2500,400));
        map.put(grid6.getGridPos(),grid6);
        MoveGrid grid7 = new MoveGrid(new GridPos(900,200),4);
        map.put(grid7.getGridPos(),grid7);
        BuildingGrid grid8 = new BuildingGrid(new GridPos(1000,200),new Building("皇家曲奇店",1200,170));
        map.put(grid8.getGridPos(),grid8);
        BuildingGrid grid9 = new BuildingGrid(new GridPos(1100,200),new Building("北京麻辣香锅",2800,450));
        map.put(grid9.getGridPos(),grid9);
        BuildingGrid grid10 = new BuildingGrid(new GridPos(1200,200),new Building("超级大饭店",2000,350));
        map.put(grid10.getGridPos(),grid10);
        EventGrid grid11 = new EventGrid(new GridPos(1300,200),"滞留一回合",player -> {
            player.sendMsg("Stay");
            player.getGameWindow().setBroadText("滞留一回合");
        });
        map.put(grid11.getGridPos(),grid11);
        BuildingGrid grid12 = new BuildingGrid(new GridPos(1300,300),new Building("良子焖子",900,130));
        map.put(grid12.getGridPos(),grid12);
        BuildingGrid grid13 = new BuildingGrid(new GridPos(1300,400),new Building("良子大饼卷肉",700,90));
        map.put(grid13.getGridPos(),grid13);
        BuildingGrid grid14 = new BuildingGrid(new GridPos(1300,500),new Building("良子板面",1100,160));
        map.put(grid14.getGridPos(),grid14);
        BuildingGrid grid15 = new BuildingGrid(new GridPos(1300,600),new Building("良子手工饺子",1300,180));
        map.put(grid15.getGridPos(),grid15);
        FateGrid grid16 = new FateGrid(new GridPos(1300,700));
        map.put(grid16.getGridPos(),grid16);
        BuildingGrid grid17 = new BuildingGrid(new GridPos(1200,700),new Building("良子大锅菜",1400,190));
        map.put(grid17.getGridPos(),grid17);
        MoveGrid grid18 = new MoveGrid(new GridPos(1100,700),3);
        map.put(grid18.getGridPos(),grid18);
        BuildingGrid grid19 = new BuildingGrid(new GridPos(1000,700),new Building("良子品铺",1900,260));
        map.put(grid19.getGridPos(),grid19);
        BuildingGrid grid20 = new BuildingGrid(new GridPos(900,700),new Building("炉石酒馆",2200,370));
        map.put(grid20.getGridPos(),grid20);
        EventGrid grid21 = new EventGrid(new GridPos(800,700),"回到原点",player -> {
            player.sendMsg("BackToZero");
        });
        map.put(grid21.getGridPos(),grid21);
        BuildingGrid grid22 = new BuildingGrid(new GridPos(700,700),new Building("夜雀食堂",1600,210));
        map.put(grid22.getGridPos(),grid22);
        BuildingGrid grid23 = new BuildingGrid(new GridPos(600,700),new Building("漏斗蛋糕店",2100,360));
        map.put(grid23.getGridPos(),grid23);
        BuildingGrid grid24 = new BuildingGrid(new GridPos(500,700),new Building("会员制餐厅",1145,180));
        map.put(grid24.getGridPos(),grid24);
        FateGrid grid25 = new FateGrid(new GridPos(400,700));
        map.put(grid25.getGridPos(),grid25);
        BuildingGrid grid26 = new BuildingGrid(new GridPos(300,700),new Building("疯狂星期寺",400,50));
        map.put(grid26.getGridPos(),grid26);
        BuildingGrid grid27 = new BuildingGrid(new GridPos(300,600),new Building("巧乐兹批发厂",1700,220));
        map.put(grid27.getGridPos(),grid27);
        MoveGrid grid28 = new MoveGrid(new GridPos(300,500),3);
        map.put(grid28.getGridPos(),grid28);
        BuildingGrid grid29 = new BuildingGrid(new GridPos(300,400),new Building("山姆超市",3000,500));
        map.put(grid29.getGridPos(),grid29);
        BuildingGrid grid30 = new BuildingGrid(new GridPos(300,300),new Building("千溢小炒",1050,160));
        map.put(grid30.getGridPos(),grid30);
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
