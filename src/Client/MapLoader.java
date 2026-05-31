package Client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapLoader {
    public static final ArrayList<fateInterface> fateList = new ArrayList<>();
    public static final ArrayList<eventInterface> eventList = new ArrayList<>();
    public static final String MAP_DATA = "map.dat";
    public static Map<GridPos,Grid> map = new HashMap<>();    //将格子以网格坐标存储

    //加载游戏数据
    public static void loadGame()
    {
        Path path = Paths.get("map.dat");

        if (!Files.exists(path)) {
            // 文件不存在
            initialMap();
        }
        else {
            loadMapFromFile();
        }

        initialFate();
        initialEvent();
    }
    //第一次游戏初始化地图
    public static void initialMap()
    {
        System.out.println("正在初始化地图");
        Grid grid1 = new EventGrid(new GridPos(300,200));
        map.put(grid1.getGridPos(),grid1);
        Grid grid2 = new BuildingGrid(new GridPos(400,200),new Building("火锅街",1500,200));
        map.put(grid2.getGridPos(),grid2);
        Grid grid3 = new BuildingGrid(new GridPos(500,200),new Building("汉堡市",1000,150));
        map.put(grid3.getGridPos(),grid3);
        Grid grid4 = new BuildingGrid(new GridPos(600,200),new Building("烧烤城",1800,250));
        map.put(grid4.getGridPos(),grid4);
        Grid grid5 = new BuildingGrid(new GridPos(700,200),new Building("麻辣烫店",600,80));
        map.put(grid5.getGridPos(),grid5);
        Grid grid6 = new BuildingGrid(new GridPos(800,200),new Building("糖果世界",2500,400));
        map.put(grid6.getGridPos(),grid6);
        Grid grid7 = new MoveGrid(new GridPos(900,200),4);
        map.put(grid7.getGridPos(),grid7);
        Grid grid8 = new BuildingGrid(new GridPos(1000,200),new Building("皇家曲奇店",1200,170));
        map.put(grid8.getGridPos(),grid8);
        Grid grid9 = new BuildingGrid(new GridPos(1100,200),new Building("北京麻辣香锅",2800,450));
        map.put(grid9.getGridPos(),grid9);
        Grid grid10 = new BuildingGrid(new GridPos(1200,200),new Building("超级大饭店",2000,350));
        map.put(grid10.getGridPos(),grid10);
        Grid grid11 = new EventGrid(new GridPos(1300,200));
        map.put(grid11.getGridPos(),grid11);
        Grid grid12 = new BuildingGrid(new GridPos(1300,300),new Building("良子焖子",900,130));
        map.put(grid12.getGridPos(),grid12);
        Grid grid13 = new BuildingGrid(new GridPos(1300,400),new Building("良子大饼卷肉",700,90));
        map.put(grid13.getGridPos(),grid13);
        Grid grid14 = new BuildingGrid(new GridPos(1300,500),new Building("良子板面",1100,160));
        map.put(grid14.getGridPos(),grid14);
        Grid grid15 = new BuildingGrid(new GridPos(1300,600),new Building("良子手工饺子",1300,180));
        map.put(grid15.getGridPos(),grid15);
        Grid grid16 = new FateGrid(new GridPos(1300,700));
        map.put(grid16.getGridPos(),grid16);
        Grid grid17 = new BuildingGrid(new GridPos(1200,700),new Building("良子大锅菜",1400,190));
        map.put(grid17.getGridPos(),grid17);
        Grid grid18 = new MoveGrid(new GridPos(1100,700),3);
        map.put(grid18.getGridPos(),grid18);
        Grid grid19 = new BuildingGrid(new GridPos(1000,700),new Building("良子品铺",1900,260));
        map.put(grid19.getGridPos(),grid19);
        Grid grid20 = new BuildingGrid(new GridPos(900,700),new Building("炉石酒馆",2200,370));
        map.put(grid20.getGridPos(),grid20);
        Grid grid21 = new EventGrid(new GridPos(800,700));
        map.put(grid21.getGridPos(),grid21);
        Grid grid22 = new BuildingGrid(new GridPos(700,700),new Building("夜雀食堂",1600,210));
        map.put(grid22.getGridPos(),grid22);
        Grid grid23 = new BuildingGrid(new GridPos(600,700),new Building("漏斗蛋糕店",2100,360));
        map.put(grid23.getGridPos(),grid23);
        Grid grid24 = new BuildingGrid(new GridPos(500,700),new Building("会员制餐厅",1145,180));
        map.put(grid24.getGridPos(),grid24);
        Grid grid25 = new FateGrid(new GridPos(400,700));
        map.put(grid25.getGridPos(),grid25);
        Grid grid26 = new BuildingGrid(new GridPos(300,700),new Building("疯狂星期寺",400,50));
        map.put(grid26.getGridPos(),grid26);
        Grid grid27 = new BuildingGrid(new GridPos(300,600),new Building("巧乐兹批发厂",1700,220));
        map.put(grid27.getGridPos(),grid27);
        Grid grid28 = new MoveGrid(new GridPos(300,500),3);
        map.put(grid28.getGridPos(),grid28);
        Grid grid29 = new BuildingGrid(new GridPos(300,400),new Building("山姆超市",3000,500));
        map.put(grid29.getGridPos(),grid29);
        Grid grid30 = new BuildingGrid(new GridPos(300,300),new Building("千溢小炒",1050,160));
        map.put(grid30.getGridPos(),grid30);
        saveMapToFile();
    }

    //第一次游戏加载命运
    public static void initialFate()
    {
        System.out.println("正在初始化命运");
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
            player.getGameWindow().setBroadText("美食评论家点评了你的餐馆，名气大涨，获得800元");
        });
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + -150);
            player.getGameWindow().setBroadText("给员工发了工资,消耗150元");
        });
        fateList.add(player -> {
            player.sendMsg("UpdatePlayerGold" + -180);
            player.getGameWindow().setBroadText("购买了喜欢的周边，花了180元");
        });
        System.out.println("命运初始化完成");
    }
    //第一次游戏加载事件
    public static void initialEvent()
    {
        System.out.println("正在初始化事件");
        eventList.add(player -> {
            player.sendMsg("Stay");
            player.getGameWindow().setBroadText("滞留一回合");
        });
        eventList.add(player -> {
            player.sendMsg("BackToZero");
        });
        System.out.println("事件初始化完成");
    }

    //从文件中加载已经生成好的地图
    @SuppressWarnings("unchecked")
    public static void loadMapFromFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("map.dat"))) {

            map = (Map<GridPos, Grid>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("加载地图成功");
    }

    //将生成好的地图保存到文件
    public static void saveMapToFile()
    {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(MAP_DATA));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("地图保存成功");
    }
}
