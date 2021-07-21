package Server.controller;

import Server.model.user.User;

import java.util.*;

public class MatchMakingController {
    private final static HashMap<User, GameController> userGameControllerHashMap;
    public final static ArrayList<User> usersWaitingFor1RoundMatch;
    public final static ArrayList<User> usersWaitingFor3RoundMatch;
    private final static ArrayList<User> usersWaitingFor1RoundMatchWithAnotherUser;
    private final static ArrayList<User> usersWaitingFor3RoundMatchWithAnotherUser;
    public static long timeStamp;
    public static long timeStamp1;
    private final static Play1Round play1Round;
    private final static Play3Round play3Round;

    static {
        play1Round = new Play1Round();
        play3Round = new Play3Round();
        timeStamp = System.currentTimeMillis() / 1000;
        timeStamp1 = System.currentTimeMillis() / 1000;
        userGameControllerHashMap = new HashMap<>();
        usersWaitingFor1RoundMatch = new ArrayList<>();
        usersWaitingFor3RoundMatch = new ArrayList<>();
        usersWaitingFor1RoundMatchWithAnotherUser = new ArrayList<>();
        usersWaitingFor3RoundMatchWithAnotherUser = new ArrayList<>();
    }

    public synchronized static GameController getGameControllerByUser(User user) {
        if (userGameControllerHashMap.containsKey(user))
            return userGameControllerHashMap.get(user);
        return null;
    }

    public synchronized static void setGameController(User user, GameController gameController) {
        userGameControllerHashMap.put(user, gameController);
    }

    public synchronized static int makeMatchWithAnotherUser(User user, String opponentUserName, int rounds) {
        if (User.getUserByUsername(opponentUserName) == null)
            return 1;
        else if (user.getUsername().equals(opponentUserName))
            return 2;
        else if (!DatabaseController.isUserOnline(User.getUserByUsername(opponentUserName)))
            return 3;
        else if (usersWaitingFor1RoundMatchWithAnotherUser.contains(User.getUserByUsername(opponentUserName)) ||
                usersWaitingFor3RoundMatchWithAnotherUser.contains(User.getUserByUsername(opponentUserName)) ||
                usersWaitingFor1RoundMatch.contains(User.getUserByUsername(opponentUserName)) ||
                usersWaitingFor3RoundMatch.contains(User.getUserByUsername(opponentUserName)))
            return 4;
        else {
            ClientUpdateController.getClientUpdateController(User.getUserByUsername(opponentUserName)).askForDuel(user.getUsername(), rounds);
            usersWaitingFor1RoundMatchWithAnotherUser.add(user);
            return 0;
        }
    }

    public synchronized static void startMatchWithAnotherUser(String username, String opponentUsername, int rounds) {
        boolean result = true;
        if (rounds == 1) {
            if (usersWaitingFor1RoundMatchWithAnotherUser.contains(User.getUserByUsername(username)))
            usersWaitingFor1RoundMatchWithAnotherUser.remove(User.getUserByUsername(username));
            else result = false;
        }
        else {
            if (usersWaitingFor3RoundMatchWithAnotherUser.contains(User.getUserByUsername(username)))
                usersWaitingFor3RoundMatchWithAnotherUser.remove(User.getUserByUsername(username));
            else result = false;
        }

        if (result) {
            int rand = new Random().nextInt(2);
            if (rand == 0) {
                GameController gameController = new GameController(username, opponentUsername, rounds);
                gameController.createNewGame();
                startUserCoinTossMenu(User.getUserByUsername(username), opponentUsername, true);
                startLobbyUserCoinTossMenu(User.getUserByUsername(opponentUsername), username, false);
            } else {
                GameController gameController = new GameController(opponentUsername, username, rounds);
                gameController.createNewGame();
                startUserCoinTossMenu(User.getUserByUsername(username), opponentUsername, false);
                startLobbyUserCoinTossMenu(User.getUserByUsername(opponentUsername), username, true);
            }
        } else {
            ClientUpdateController clientUpdateController = ClientUpdateController.getClientUpdateController(
                    User.getUserByUsername(opponentUsername));

            clientUpdateController.startRefusedMatchView(username);
        }
    }

    public static synchronized void refuseMatch(String username, String opponentUsername, int rounds) {
        if (rounds == 1)
            usersWaitingFor1RoundMatchWithAnotherUser.remove(User.getUserByUsername(username));
        else
            usersWaitingFor3RoundMatchWithAnotherUser.remove(User.getUserByUsername(username));

        ClientUpdateController clientUpdateController = ClientUpdateController.getClientUpdateController(User.getUserByUsername(username));
        clientUpdateController.startRefuseMatchView(opponentUsername);
    }

    public synchronized static void makeMatch(User user, int rounds) {
        if (rounds == 1) {
            usersWaitingFor1RoundMatch.add(user);
            if (!play1Round.isAlive()) play1Round.start();
        } else {
            usersWaitingFor3RoundMatch.add(user);
            if (play3Round.isAlive()) play3Round.start();
        }
    }

    public synchronized static void cancelMakeMatch(User user) {
        usersWaitingFor1RoundMatch.remove(user);
        usersWaitingFor3RoundMatch.remove(user);
        usersWaitingFor1RoundMatchWithAnotherUser.remove(user);
        usersWaitingFor3RoundMatchWithAnotherUser.remove(user);
    }

    public static void play3Round() {
        usersWaitingFor3RoundMatch.sort(Comparator.comparing(User::getScore));
        ArrayList<User> all = usersWaitingFor3RoundMatch;
        for (int i = 0; i < usersWaitingFor3RoundMatch.size(); i += 2) {
            if (all.get(i) != null && all.get(i + 1) != null) {
                startRandomGame(all.get(i), all.get(i + 1), 3);
            }
        }
        if (usersWaitingFor3RoundMatch.size() % 2 == 1) {
            User user = usersWaitingFor3RoundMatch.get(usersWaitingFor3RoundMatch.size() - 1);
            usersWaitingFor3RoundMatch.clear();
            usersWaitingFor3RoundMatch.add(user);
        } else {
            usersWaitingFor3RoundMatch.clear();
        }
    }

    public static void play1Round() {
        usersWaitingFor1RoundMatch.sort(Comparator.comparing(User::getScore));
        ArrayList<User> all = usersWaitingFor1RoundMatch;
        for (int i = 0; i < usersWaitingFor1RoundMatch.size(); i += 2) {
            if (all.get(i) != null && all.get(i + 1) != null) {
                startRandomGame(all.get(i), all.get(i + 1), 1);
            }
        }
        if (usersWaitingFor1RoundMatch.size() % 2 == 1) {
            User user = usersWaitingFor1RoundMatch.get(usersWaitingFor1RoundMatch.size() - 1);
            usersWaitingFor1RoundMatch.clear();
            usersWaitingFor1RoundMatch.add(user);

        } else {
            usersWaitingFor1RoundMatch.clear();
        }
    }

    public static void startUserCoinTossMenu(User user, String opponentUsername, boolean isWinner) {
        ClientUpdateController clientUpdateController = ClientUpdateController.getClientUpdateController(user);
        clientUpdateController.startCoinTossMenu(opponentUsername, isWinner);
    }

    public static void startLobbyUserCoinTossMenu(User user, String opponentUsername, boolean isWinner) {
        ClientUpdateController clientUpdateController = ClientUpdateController.getClientUpdateController(user);
        clientUpdateController.startLobbyCoinTossMenu(opponentUsername, isWinner);
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

class Play1Round extends Thread {
    @Override
    public void run() {
        long firstTime = MatchMakingController.timeStamp;
        while ((System.currentTimeMillis() / 1000) - firstTime < 5) {
            MatchMakingController.usersWaitingFor1RoundMatch.sort(Comparator.comparing(User::getScore));
        }
        MatchMakingController.timeStamp = System.currentTimeMillis() / 1000;
        MatchMakingController.play1Round();
    }
}

class Play3Round extends Thread {
    @Override
    public void run() {
        long firstTime = MatchMakingController.timeStamp1;
        while ((System.currentTimeMillis() / 1000) - firstTime < 5) {
            MatchMakingController.usersWaitingFor3RoundMatch.sort(Comparator.comparing(User::getScore));
        }
        MatchMakingController.timeStamp1 = System.currentTimeMillis() / 1000;
        MatchMakingController.play3Round();
    }
}
