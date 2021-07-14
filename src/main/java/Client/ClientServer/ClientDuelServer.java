package Client.ClientServer;

import Client.view.DuelMenuGui;
import Network.GameData;
import Network.PortConfig;
import com.gilecode.yagson.YaGson;

public class ClientDuelServer extends ClientServer {
    private GameData gameData;
    private final ClientListener clientListener;

    public ClientDuelServer() {
        super(PortConfig.DUEL_PORT.getPort(), "GameController");
        this.clientListener = new ClientListener(sendRequest.getSocket(), sendRequest.getDataInputStream(),
                sendRequest.getDataOutputStream(), this);
        clientListener.start();
    }

    public void updateGameData() {
        gameData = (GameData) sendRequest.getMethodResult("getGameData");
    }

    public GameData getGameData() {
        return gameData;
    }

    // Methods related to response to server:
    @Override
    protected String handleServerRequest(String serverRequest) {
        String[] parts = serverRequest.split("\n");
        String methodName = parts[1];
        Object[] fields = getObjects(parts[2]);
        Object answer = "success";

        DuelMenuGui duelMenuGui = DuelMenuGui.getDuelMenuGui();

        if (methodName.equals("showGameWinner")) {
            duelMenuGui.showGameWinner((String) fields[0], (int) fields[1], (int) fields[2]);
        } else if (methodName.equals("showMatchWinner")) {
            duelMenuGui.showMatchWinner((String) fields[0], (int) fields[1], (int) fields[2]);
        } else if (methodName.equals("endGame")) {
            duelMenuGui.endGame();
        } else if (methodName.equals("getCardsForTribute")) {
            answer = duelMenuGui.getCardsForTribute((int) fields[0]);
        } else if (methodName.equals("getCardFromGraveYard")) {
            answer = duelMenuGui.getCardFromGraveYard((String) fields[0]);
        } else if (methodName.equals("getCardName")) {
            answer = duelMenuGui.getCardName();
        } else if (methodName.equals("showOutput")) {
            duelMenuGui.showOutput((String) fields[0]);
        } else if (methodName.equals("getNumber")) {
            answer = duelMenuGui.getNumber((String) fields[0]);
        } else if (methodName.equals("getYesNoAnswer")) {
            answer = duelMenuGui.getYesNoAnswer((String) fields[0]);
        } else if (methodName.equals("playWinMusic")) {
            duelMenuGui.playWinMusic();
        } else if (methodName.equals("playLoseMusic")) {
            duelMenuGui.playLoseMusic();
        }

        return getAnswer(answer);
    }

    private Object[] getObjects(String serverRequestJson) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(serverRequestJson, Object[].class);
    }

    private String getAnswer(Object answer) {
        if (answer.equals("success"))
            return "success";
        YaGson yaGson = new YaGson();
        return answer.getClass().getName() + "\n"
                + yaGson.toJson(answer);
    }
}
