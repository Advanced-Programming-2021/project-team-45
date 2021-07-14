package Server.controller;

import Server.ServerController.DuelRequestHandler;
import Server.ServerController.LobbyRequestHandler;
import Server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MatchMakingController {
    private final static HashMap<User, GameController> userGameControllerHashMap;
    private final static HashMap<User, DuelRequestHandler> userDuelRequestHandlerHashMap;
    private final static HashMap<User, LobbyRequestHandler> userLobbyRequestHandlerHashMap;
    private final  static ArrayList<User> usersWaitingFor1RoundMatch;
    private final  static ArrayList<User> usersWaitingFor3RoundMatch;

    // TODO: move this to server side (VERY IMPORTANT) after match is made below code should be executed:
//    GameController gameController = new GameController(firstPlayerUserName, secondPlayerUserName, rounds);
//        gameController.createNewGame();


    static {
        userGameControllerHashMap = new HashMap<>();
        userDuelRequestHandlerHashMap = new HashMap<>();
        userLobbyRequestHandlerHashMap = new HashMap<>();
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

    public synchronized static DuelRequestHandler getDuelRequestHandler(User user) {
        if (userDuelRequestHandlerHashMap.containsKey(user))
            return userDuelRequestHandlerHashMap.get(user);
        return null;
    }

    public synchronized static void setDuelRequestHandler(User user, DuelRequestHandler duelRequestHandler) {
        userDuelRequestHandlerHashMap.put(user, duelRequestHandler);
    }

    public synchronized static void makeMatch(User user, int rounds, LobbyRequestHandler lobbyRequestHandler) {
        userLobbyRequestHandlerHashMap.put(user, lobbyRequestHandler);
        // tokhmi algorithm:
        if (rounds == 1) {
            if (usersWaitingFor1RoundMatch.size() == 1) {
                User user2 = usersWaitingFor1RoundMatch.get(0);
                usersWaitingFor1RoundMatch.remove(0);
                startRandomGame(user, user2, 1);
            }
        }
    }

    public static void startUserCoinTossMenu(User user, String opponentUsername, boolean isWinner) {
        LobbyRequestHandler lobbyRequestHandler = userLobbyRequestHandlerHashMap.get(user);
        lobbyRequestHandler.startCoinTossMenu(opponentUsername, isWinner);
    }

    public static void startRandomGame(User user1, User user2, int rounds) {
        int rand = new Random().nextInt(2);
        LobbyRequestHandler user1LobbyRequestHandler = userLobbyRequestHandlerHashMap.get(user1);
        LobbyRequestHandler user2LobbyRequestHandler = userLobbyRequestHandlerHashMap.get(user2);
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
