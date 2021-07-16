package Server.ServerController;

import Network.PortConfig;
import Server.controller.DatabaseController;
import Server.model.user.User;
import com.gilecode.yagson.YaGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSendRequest {
    private final String host;

    public ServerSendRequest(String host) {
        this.host = host;
    }

    public static ServerSendRequest getUserServerSendRequest(User user) {
        return DatabaseController.getUserServerSendRequest(user);
    }

    public Object getMethodResult(String methodName, Object... fields) {
        String request = initRequest(methodName, fields);
        try {
            Socket socket = new Socket(host, PortConfig.CLIENT_PORT.getPort());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(request);
            dataOutputStream.flush();
            // get answer from server:
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String answer = dataInputStream.readUTF();
            socket.close();
            return processAnswer(answer);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private Object getObjectFromJson(Class<?> clazz, String json) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, clazz);
    }

    private Object processAnswer(String answer) {
        if (answer.equals("success"))
            return null;
        String[] parts = answer.split("\n");
        try {
            Class<?> clazz = Class.forName(parts[0]);
            return getObjectFromJson(clazz, parts[1]);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private String objectsToJson(Object[] objects) {
        YaGson yaGson = new YaGson();
        return yaGson.toJson(objects);
    }

    private String initRequest(String methodName, Object[] fields) {
        return "SERVER_REQUEST\n"
                + methodName + "\n"
                + objectsToJson(fields);
    }

    // Methods of GameController to call from Client:
    public void showGameWinner(String winnerUsername, int playerWins, int opponentWins) {
        getMethodResult("showGameWinner", winnerUsername, playerWins, opponentWins);
    }

    public void showMatchWinner(String winnerUsername, int playerWins, int opponentWins) {
        getMethodResult("showMatchWinner", winnerUsername, playerWins, opponentWins);
    }

    public void endGame() {
        getMethodResult("endGame");
    }

    public ArrayList<Integer> getCardsForTribute(int n) {
        return (ArrayList<Integer>) getMethodResult("getCardsForTribute", n);
    }

    public String getCardFromGraveYard(String view) {
        return (String) getMethodResult("getCardFromGraveYard", view);
    }

    public String getCardName() {
        return (String) getMethodResult("getCardName");
    }

    public void showOutput(String message) {
        getMethodResult("showOutput", message);
    }

    public int getNumber(String view) {
        return (int) getMethodResult("getNumber", view);
    }

    public Boolean getYesNoAnswer(String question) {
        return (Boolean) getMethodResult("getYesNoAnswer", question);
    }

    public void playWinMusic() {
        getMethodResult("playWinMusic");
    }

    public void playLoseMusic() {
        getMethodResult("playLoseMusic");
    }

    // Methods of Lobby to call from client:
    public void startCoinTossMenu(String opponentUsername, boolean isWinner) {
        getMethodResult("startCoinTossMenu", opponentUsername, isWinner);
    }
}
