package Client.ClientServer;

import Client.view.LobbyMenuGui;
import Network.PortConfig;
import Server.model.Message;
import com.gilecode.yagson.YaGson;

import java.util.ArrayList;

public class ClientLobbyServer extends ClientServer {
    private ClientListener clientListener;

    public ClientLobbyServer() {
        super(PortConfig.LOBBY_PORT.getPort(), "Lobby");
        clientListener = new ClientListener(sendRequest.getSocket(), sendRequest.getDataInputStream(),
                sendRequest.getDataOutputStream(), this);

        // TODO: fix this shit:
//        clientListener.start();
    }

    // Sending Request to server:
    public void makeMatch(int rounds) {
        sendRequest.getMethodResult("makeMatch", rounds);
    }

    public void addMessage(String message) {
        sendRequest.getMethodResult("addMessage", message);
    }

    public int deleteMessageErrorHandler(int id) {
        Object answer = sendRequest.getMethodResult("deleteMessageErrorHandler", id);
        return (int) answer;
    }

    public int editMessageErrorHandler(int id, String newMessage) {
        Object answer = sendRequest.getMethodResult("editMessageErrorHandler", id, newMessage);
        return (int) answer;
    }

    public void setIsPinnedMessageById(int id, boolean isPinned) {
        sendRequest.getMethodResult("setIsPinnedMessageById", id, isPinned);
    }

    public ArrayList<Message> getAllMessages() {
        Object answer = sendRequest.getMethodResult("getAllMessages");
        return (ArrayList<Message>) answer;
    }

    // Response to server:
    @Override
    protected String handleServerRequest(String serverRequest) {
        String[] parts = serverRequest.split("\n");
        String methodName = parts[1];
        Object[] fields = getObjects(parts[2]);
        Object answer = "success";

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
