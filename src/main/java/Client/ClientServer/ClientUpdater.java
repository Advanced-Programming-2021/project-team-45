package Client.ClientServer;

import Client.view.*;
import Network.PortConfig;
import Network.ServerHost;
import com.gilecode.yagson.YaGson;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUpdater extends Thread {
    private boolean isUpdate;

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ServerHost.HOST.getHostName(), PortConfig.UPDATE_CLIENT_PORT.getPort());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (isUpdate) {
                String updateRequest = SendRequest.getToken() + "\n"
                        + "UPDATE";
                dataOutputStream.writeUTF(updateRequest);
                dataOutputStream.flush();
                String serverResponse = dataInputStream.readUTF();
                if (!serverResponse.equals("null")) {
                    Platform.runLater(() -> handleResponse(serverResponse));
                }
                Thread.sleep(500);
            }
            socket.close();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private void handleResponse(String serverResponse) {
        String[] parts = serverResponse.split("\n");
        String methodName = parts[0];
        Object[] fields = getObjects(parts[1]);

        DuelMenuGui duelMenuGui = DuelMenuGui.getDuelMenuGui();
        MatchMakingMenuGui matchMakingMenuGui = MatchMakingMenuGui.getMatchMakingMenuGui();
        LobbyMenuGui lobbyMenuGui = LobbyMenuGui.getLobbyMenuGui();

        // DuelMenu Methods:
        if (methodName.equals("showGameWinner")) {
            duelMenuGui.showGameWinner((String) fields[0], (int) fields[1], (int) fields[2]);
        } else if (methodName.equals("showMatchWinner")) {
            duelMenuGui.showMatchWinner((String) fields[0], (int) fields[1], (int) fields[2]);
        } else if (methodName.equals("endGame")) {
            duelMenuGui.endGame();
        } else if (methodName.equals("showOutput")) {
            duelMenuGui.showOutput((String) fields[0]);
        } else if (methodName.equals("playWinMusic")) {
            duelMenuGui.playWinMusic();
        } else if (methodName.equals("playLoseMusic")) {
            duelMenuGui.playLoseMusic();
        } else if (methodName.equals("updateGameBoard")) {
            duelMenuGui.updateGameBoard();
        }

        // MatchMaking Methods:
        if (methodName.equals("startCoinTossMenu")) {
            MatchMakingMenuGui matchMakingMenuGuis = MatchMakingMenuGui.getMatchMakingMenuGui();
            matchMakingMenuGui.startCoinTossMenu((String) fields[0], (boolean) fields[1]);
        } else if (methodName.equals("askForDuel")) {
            lobbyMenuGui.askForDuel((String) fields[0], (int) fields[1]);
        } else if (methodName.equals("startLobbyCoinTossMenu"))
            lobbyMenuGui.startLobbyCoinTossMenu((String) fields[0], (boolean) fields[1]);
        else if (methodName.equals("startRefuseMatchView"))
            matchMakingMenuGui.startRefuseMatchView((String) fields[0]);
        else if (methodName.equals("startRefusedMatchView")) {

        }

        // Scoreboard Methods:
        if (methodName.equals("updateScoreboard")
                && MenuGui.isScoreboard()) {
            ScoreBoardMenuGui.updateScoreboard();
        }
    }

    private Object[] getObjects(String json) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, Object[].class);
    }
}
