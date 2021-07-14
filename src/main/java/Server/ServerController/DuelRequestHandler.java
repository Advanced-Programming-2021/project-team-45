package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.GameController;
import Server.controller.MatchMakingController;
import Server.model.user.User;

import java.net.Socket;
import java.util.ArrayList;

public class DuelRequestHandler extends RequestHandler {
    private final ServerSendRequest serverSendRequest;

    public DuelRequestHandler(Socket socket) {
        super(socket);
        this.serverSendRequest = new ServerSendRequest(socket, dataInputStream, dataOutputStream);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        User user = DatabaseController.getUserByToken(token);
        GameController gameController = MatchMakingController.getGameControllerByUser(user);
        MatchMakingController.setDuelRequestHandler(user, this);

        if (methodName.equals("getGameData")) {
            answer = gameController.getGameData(user);
        }

        return fieldParser.getAnswer(answer);
    }

    public void showGameWinner(String winnerUsername, int playerWins, int opponentWins) {
        serverSendRequest.getMethodResult("showGameWinner", winnerUsername, playerWins, opponentWins);
    }

    public void showMatchWinner(String winnerUsername, int playerWins, int opponentWins) {
        serverSendRequest.getMethodResult("showMatchWinner", winnerUsername, playerWins, opponentWins);
    }

    public void endGame() {
        serverSendRequest.getMethodResult("endGame");
    }

    public ArrayList<Integer> getCardsForTribute(int n) {
        return (ArrayList<Integer>) serverSendRequest.getMethodResult("getCardsForTribute", n);
    }

    public String getCardFromGraveYard(String view) {
        return (String) serverSendRequest.getMethodResult("getCardFromGraveYard", view);
    }

    public String getCardName() {
        return (String) serverSendRequest.getMethodResult("getCardName");
    }

    public void showOutput(String message) {
        serverSendRequest.getMethodResult("showOutput", message);
    }

    public int getNumber(String view) {
        return (int) serverSendRequest.getMethodResult("getNumber", view);
    }

    public Boolean getYesNoAnswer(String question) {
        return (Boolean) serverSendRequest.getMethodResult("getYesNoAnswer", question);
    }

    public void playWinMusic() {
        serverSendRequest.getMethodResult("playWinMusic");
    }

    public void playLoseMusic() {
        serverSendRequest.getMethodResult("playLoseMusic");
    }
}
