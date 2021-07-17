package Server.controller;

import Server.ServerController.LobbyRequestHandler;
import Server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MatchMakingController {
    private final static HashMap<User, GameController> userGameControllerHashMap;
    private final static ArrayList<User> usersWaitingFor1RoundMatch;
    private final static ArrayList<User> usersWaitingFor3RoundMatch;

    static {
        userGameControllerHashMap = new HashMap<>();
        usersWaitingFor1RoundMatch = new ArrayList<>();
        usersWaitingFor3RoundMatch = new ArrayList<>();
    }

    // we have to set the gameController for both users after MatchMaking: (I put it in GameController constructor)
    public synchronized static GameController getGameControllerByUser(User user) {
        if (userGameControllerHashMap.containsKey(user))
            return userGameControllerHashMap.get(user);
        return null;
    }

    public synchronized static void setGameController(User user, GameController gameController) {
        userGameControllerHashMap.put(user, gameController);
    }

    public synchronized static void makeMatch(User user, int rounds) {
        // TODO: better algorithm:
        if (rounds == 1) {
            if (usersWaitingFor1RoundMatch.size() > 0) {
                User user2 = usersWaitingFor1RoundMatch.get(0);
                usersWaitingFor1RoundMatch.remove(0);
                startRandomGame(user, user2, 1);
            } else {
                usersWaitingFor1RoundMatch.add(user);
            }
        }
    }

    public static void startUserCoinTossMenu(User user, String opponentUsername, boolean isWinner) {
        ClientUpdateController clientUpdateController = ClientUpdateController.getClientUpdateController(user);
        clientUpdateController.startCoinTossMenu(opponentUsername, isWinner);
    }

    public static void startRandomGame(User user1, User user2, int rounds) {
        int rand = new Random().nextInt(2);
        if (rand == 0) {
            GameController gameController = new GameController(user1.getUsername(), user2.getUsername(), rounds);
            gameController.createNewGame();
            startUserCoinTossMenu(user1, user2.getUsername(), true);
            startUserCoinTossMenu(user2, user1.getUsername(), false);
        } else {
            GameController gameController = new GameController(user2.getUsername(), user1.getUsername(), rounds);
            gameController.createNewGame();
            startUserCoinTossMenu(user1, user2.getUsername(), false);
            startUserCoinTossMenu(user2, user1.getUsername(), true);
        }
    }
}
