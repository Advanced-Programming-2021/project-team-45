package Server.controller;

import Server.model.Scoreboard;
import Server.model.user.User;

import java.util.LinkedHashMap;

public class ScoreboardController {
    public static LinkedHashMap<String, Integer> getSortedNicknameScore() {
        return Scoreboard.getSortedNicknameScore();
    }

    public static String getNickname(String username) {
        return User.getUserByUsername(username).getNickname();
    }
}
