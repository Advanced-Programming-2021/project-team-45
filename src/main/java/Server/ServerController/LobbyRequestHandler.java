package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.MatchMakingController;
import Server.controller.MessengerController;
import Server.model.user.User;

import java.net.Socket;

public class LobbyRequestHandler extends RequestHandler {
    private final ServerSendRequest serverSendRequest;

    public LobbyRequestHandler(Socket socket) {
        super(socket);
        this.serverSendRequest = new ServerSendRequest(socket, dataInputStream, dataOutputStream);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        User user = DatabaseController.getUserByToken(token);
        Object answer = "";

        // Messaging methods:
        if (methodName.equals("addMessage")) {
            MessengerController.addMessage(user, (String) fields[0]);
        } else if (methodName.equals("deleteMessageErrorHandler")) {
            answer = MessengerController.deleteMessageErrorHandler(user, (Integer) fields[0]);
        } else if (methodName.equals("editMessageErrorHandler")) {
            answer = MessengerController.editMessageErrorHandler(user, (Integer) fields[0], (String) fields[1]);
        } else if (methodName.equals("getMessageById")) {
            answer = MessengerController.getMessageById((Integer) fields[0]);
        } else if (methodName.equals("getAllMessages")) {
            answer = MessengerController.getAllMessages();
        }

        // MatchMaking methods:
        if (methodName.equals("makeMatch")) {
            MatchMakingController.makeMatch(user, (int) fields[0], this);
        }

        return fieldParser.getAnswer(answer);
    }

    // Sending requests to client:
    public void startCoinTossMenu(String opponentUsername, boolean isWinner) {
        serverSendRequest.getMethodResult("startCoinTossMenu", opponentUsername, isWinner);
    }
}
