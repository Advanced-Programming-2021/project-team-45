package Server.ServerController;

import Server.controller.MessengerController;

import java.net.Socket;

public class MessengerRequestHandler extends RequestHandler{

    public MessengerRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        if (methodName.equals("addMessage"))
            MessengerController.addMessage(token, (String) fields[0]);
        else if (methodName.equals("deleteMessageErrorHandler"))
            answer = MessengerController.deleteMessageErrorHandler(token, (Integer) fields[0]);
        else if (methodName.equals("editMessageErrorHandler"))
            answer = MessengerController.editMessageErrorHandler(token, (Integer) fields[0], (String) fields[1]);
        else if (methodName.equals("getMessageById"))
            answer = MessengerController.getMessageById((Integer) fields[0]);
        else if (methodName.equals("getAllMessages"))
            answer = MessengerController.getAllMessages();

        return fieldParser.getAnswer(answer);
    }
}
