package Client.ClientServer;

import Network.GameData;
import Network.PortConfig;

public class ClientDuelServer extends ClientServer {
    private GameData gameData;

    public ClientDuelServer() {
        super(PortConfig.DUEL_PORT.getPort(), "GameController");
    }

    public void updateGameData() {
        Object object = new Object();
        gameData = (GameData) sendRequest.getMethodResult("getGameData");
    }

    public GameData getGameData() {
        return gameData;
    }
}
