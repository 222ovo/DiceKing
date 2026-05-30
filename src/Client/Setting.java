package Client;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Setting {
    public static final int MAX_PLAYERS_NUM = 5;
    //格子大小
    public static final int GRID_WIDTH = 100;
    public static final int GRID_HEIGHT = 100;
    //地图大小
    public static final int MAP_WIDTH = 1100;
    public static final int MAP_HEIGHT = 600;

    //初始资金
    public static final int INITIAL_GOLD = 2000;

    //玩家头像尺寸参数
    public static final int PLAYER_HEAD_WIDTH = 100;
    public static final int PLAYER_HEAD_HEIGHT = 100;
    public static final int PLAYER_EYE_WIDTH = 15;
    public static final int PLAYER_EYE_HEIGHT = 40;

    //格子颜色
    public static final Color BUILDING_GRID_COLOR = Color.BLUE;
    public static final Color EVENT_GRID_COLOR = Color.ORANGE;
    public static final Color FATE_GRID_COLOR = Color.RED;
    public static final Color MOVE_GIRD_COLOR = Color.PINK;

    //玩家在格子中的大小
    public static final int PLAYER_WIDTH = 25;
    public static final int PLAYER_HEIGHT = 25;
    //玩家在格子的方位
    public static final int PLAYER0_X = 310;
    public static final int PLAYER0_Y = 210;
    public static final Color PLAYER0_COLOR = Color.PINK;
    public static final int PLAYER1_X = 340;
    public static final int PLAYER1_Y = 210;
    public static final Color PLAYER1_COLOR = Color.YELLOW;
    public static final int PLAYER2_X = 370;
    public static final int PLAYER2_Y = 210;
    public static final Color PLAYER2_COLOR = Color.RED;
    public static final int PLAYER3_X = 325;
    public static final int PLAYER3_Y = 240;
    public static final Color PLAYER3_COLOR = Color.ORANGE;
    public static final int PLAYER4_X = 355;
    public static final int PLAYER4_Y = 240;
    public static final Color PLAYER4_COLOR = Color.GRAY;
    //每回合玩家获得的金币
    public static final int ROUND_BOUNDS = 1000;
    //初始移动方向
    public static final MoveDir INITIAL_DIR = MoveDir.RIGHT;
}
