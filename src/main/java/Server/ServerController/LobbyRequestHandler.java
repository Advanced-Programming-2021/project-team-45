package Server.ServerController;

import Server.controller.ClientUpdateController;
import Server.controller.DatabaseController;
import Server.controller.MatchMakingController;
import Server.controller.MessengerController;
import Server.model.user.User;

import java.net.Socket;

public class LobbyRequestHandler extends RequestHandler {
    public LobbyRequestHandler(Socket socket) {
        super(socket);
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
        } else if (methodName.equals("getAllMessagesData")) {
            answer = MessengerController.getAllMessagesData();
        } else if (methodName.equals("setIsPinnedMessageById")) {
            MessengerController.setIsPinnedMessageById((int) fields[0], (boolean) fields[1]);
        } else if (methodName.equals("getNumberOfLoggedInUsers"))
            answer = DatabaseController.getNumberOfLoggedInUsers();

        // MatchMaking methods:
        if (methodName.equals("makeMatch")) {
            MatchMakingController.makeMatch(user, (int) fields[0]);
        } else if (methodName.equals("makeMatchWithAnotherUser")) {
            answer = MatchMakingController.makeMatchWithAnotherUser(user, (String) fields[0], (int) fields[1]);
        } else if (methodName.equals("startMatchWithAnotherUser")) {
            MatchMakingController.startMatchWithAnotherUser((String) fields[0], (String) fields[1], (int) fields[2]);
        } else if (methodName.equals("refuseMatch")) {
            MatchMakingController.refuseMatch((String) fields[0], (String) fields[1], (int) fields[2]);
        }
        else if (methodName.equals("stopMakeMatch")) {
            // TODO: stop match-making for user
        }

        return fieldParser.getAnswer(answer);
    }
}
