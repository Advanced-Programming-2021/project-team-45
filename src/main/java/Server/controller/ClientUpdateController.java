package Server.controller;

import Server.ServerController.ClientUpdateHandler;
import Server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientUpdateController {
    private final static HashMap<User, ClientUpdateController> userClientUpdateControllerHashMap;
    private final User user;

    static {
        userClientUpdateControllerHashMap = new HashMap<>();
    }

    public ClientUpdateController(User user) {
        this.user = user;
    }

    public static synchronized ClientUpdateController getClientUpdateController(User user) {
        if (!userClientUpdateControllerHashMap.containsKey(user))
            userClientUpdateControllerHashMap.put(user, new ClientUpdateController(user));
        return userClientUpdateControllerHashMap.get(user);
    }

    // Methods of GameController to call from Client:
    public void showGameWinner(String winnerUsername, int playerWins, int opponentWins) {
        String update = ClientUpdateHandler.getUpdateStringFormat(
                "showGameWinner", winnerUsername, playerWins, opponentWins);
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void showMatchWinner(String winnerUsername, int playerWins, int opponentWins) {
        String update = ClientUpdateHandler.getUpdateStringFormat(
                "showMatchWinner", winnerUsername, playerWins, opponentWins);
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void endGame() {
        String update = ClientUpdateHandler.getUpdateStringFormat("endGame");
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void showOutput(String message) {
        String update = ClientUpdateHandler.getUpdateStringFormat("showOutput", message);
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void playWinMusic() {
        String update = ClientUpdateHandler.getUpdateStringFormat("playWinMusic");
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void playLoseMusic() {
        String update = ClientUpdateHandler.getUpdateStringFormat("playLoseMusic");
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    public void updateUserGameBoard(User user) {
        String update = ClientUpdateHandler.getUpdateStringFormat("updateGameBoard");
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    // Methods of Lobby to call from client:
    public void startCoinTossMenu(String opponentUsername, boolean isWinner) {
        String update = ClientUpdateHandler.getUpdateStringFormat(
                "startCoinTossMenu", opponentUsername, isWinner);
        ClientUpdateHandler.addClientUpdate(user, update);
    }

    // Methods that shouldn't be used:
    public ArrayList<Integer> getCardsForTribute(int n) {
//        return (ArrayList<Integer>) getMethodResult("getCardsForTribute", n);
        return null;
    }

    public String getCardFromGraveYard(String view) {
//        return (String) getMethodResult("getCardFromGraveYard", view);
        return null;
    }

    public String getCardName() {
//        return (String) getMethodResult("getCardName");
        return null;
    }

    public int getNumber(String view) {
//        return (int) getMethodResult("getNumber", view);
        return 0;
    }

    public Boolean getYesNoAnswer(String question) {
//        return (Boolean) getMethodResult("getYesNoAnswer", question);
        return null;
    }
}
