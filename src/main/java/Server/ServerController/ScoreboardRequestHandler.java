package Server.ServerController;

import Server.controller.ScoreboardController;

import java.net.Socket;

public class ScoreboardRequestHandler extends RequestHandler{
    public ScoreboardRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        if (methodName.equals("getSortedNicknameScore"))
            answer = ScoreboardController.getSortedNicknameScore();
        else if (methodName.equals("getNickname"))
            answer = ScoreboardController.getNickname((String) fields[0]);
        return fieldParser.getAnswer(answer);
    }
}
