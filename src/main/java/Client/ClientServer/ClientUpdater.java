package Client.ClientServer;

import Client.view.DuelMenuGui;
import Client.view.LobbyMenuGui;
import Network.PortConfig;
import Network.ServerHost;
import com.gilecode.yagson.YaGson;

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
                if (!serverResponse.equals("null"))
                    handleResponse(serverResponse);
                Thread.sleep(500);
            }
            socket.close();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private void handleResponse(String  serverResponse) {
        String[] parts = serverResponse.split("\n");
        String methodName = parts[0];
        Object[] fields = getObjects(parts[1]);

        DuelMenuGui duelMenuGui = DuelMenuGui.getDuelMenuGui();

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
        }

        if (methodName.equals("startCoinToss")) {
            LobbyMenuGui.startCoinToss((String) fields[0], (boolean) fields[1]);
        }
    }

    private Object[] getObjects(String json) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, Object[].class);
    }
}
