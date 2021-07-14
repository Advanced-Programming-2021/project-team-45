package Server.controller;

import Server.ServerController.DuelRequestHandler;
import Server.model.user.User;

import java.util.HashMap;

public class MatchMakingController {
    private final static HashMap<User, GameController> userGameControllerHashMap;
    private final static HashMap<User, DuelRequestHandler> userDuelRequestHandlerHashMap;

    static {
        userGameControllerHashMap = new HashMap<>();
        userDuelRequestHandlerHashMap = new HashMap<>();
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
}
