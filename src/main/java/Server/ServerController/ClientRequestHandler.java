package Server.ServerController;

import Server.controller.DatabaseController;
import Server.model.user.User;

import java.net.Socket;

public class ClientRequestHandler extends RequestHandler {
    public ClientRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String className = parts[1];
        String methodName = parts[2];
        User user = DatabaseController.getUserByToken(token);
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";


        // TODO: get class and shove it up your ass


        return fieldParser.getAnswer(answer);
    }
}
