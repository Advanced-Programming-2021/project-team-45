package Client.ClientServer;

import Network.PortConfig;
import Server.model.Message;

import java.util.ArrayList;

public class ClientLobbyServer extends ClientServer {

    public ClientLobbyServer() {
        super(PortConfig.LOBBY_PORT.getPort(), "Lobby");
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

    public ArrayList<Object[]> getAllMessagesData() {
        Object answer = sendRequest.getMethodResult("getAllMessagesData");
        return (ArrayList<Object[]>) answer;
    }
}
