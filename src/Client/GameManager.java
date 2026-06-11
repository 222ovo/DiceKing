package Client;

public class GameManager {
    public static final GameManager Instance = new GameManager();

    private GameWindow gameWindow;
    private Player player;
    public GameManager()
    {

    }
    public GameManager(Player player)
    {
        this.player = player;
        Instance.player = player;
    }

    private GameManager getInstance()
    {
        return Instance;
    }

    //TODO:改
    public void checkGameOver()
    {
        PlayerData winPlayer = null;
        int n = 0;
        for(int i = 0 ; i < Client.playerNum;i++)
        {
            PlayerData playerData = Player.playerDataList.get(i);
            if(playerData.isAlive) {
                n++;
                winPlayer = playerData;
            }
        }
        if(n <= 1) {
            System.out.println("游戏结束");
            gameWindow.overView(winPlayer);
        }
        else{
            System.out.println("游戏继续");
        }
    }

    public void initEventHandlers()
    {
        gameWindow.addOnReady(()->gameWindow.playerReady());
        gameWindow.addOnBuy(() -> gameWindow.buyBuilding());
        gameWindow.addOnOver(() -> gameWindow.roundOver());
        gameWindow.addOnDice(() -> gameWindow.rollDice());
    }

    public void initGame()
    {
        MapLoader.loadGame();
        this.gameWindow = new GameWindow(player);
        Instance.gameWindow = gameWindow;
        player.setGameWindow(gameWindow);
        initEventHandlers();
    }
    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
