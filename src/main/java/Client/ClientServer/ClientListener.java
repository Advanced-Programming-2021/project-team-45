package Client.ClientServer;

import Client.view.DuelMenuGui;
import Client.view.LobbyMenuGui;
import Network.PortConfig;
import com.gilecode.yagson.YaGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener extends Thread {
    private boolean isListen;

    public void setListen(boolean isListen) {
        this.isListen = isListen;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PortConfig.CLIENT_PORT.getPort());
            while (isListen) {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String serverRequest = dataInputStream.readUTF();
                String clientResponse = handleServerRequest(serverRequest);
                dataOutputStream.writeUTF(clientResponse);
                dataOutputStream.flush();
                socket.close();
            }
            serverSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String handleServerRequest(String serverRequest) {
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

        if (methodName.equals("startCoinToss")) {
            LobbyMenuGui.startCoinToss((String) fields[0], (boolean) fields[1]);
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
