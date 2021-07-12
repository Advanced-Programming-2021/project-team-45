package Server.ServerController;

import Server.controller.DatabaseController;
import Server.model.user.User;

import java.net.Socket;

public class DuelRequestHandler extends RequestHandler {
    public DuelRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        User user = DatabaseController.getUserByToken(token);

        if (methodName.equals("getGameData")) {

        }

        return fieldParser.getAnswer(answer);
    }
}
