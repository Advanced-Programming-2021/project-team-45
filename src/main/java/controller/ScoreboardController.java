package controller;

import model.Scoreboard;

import java.util.LinkedHashMap;

public class ScoreboardController {

    public static LinkedHashMap<String, Integer> getSortedNicknameScore() {
        return Scoreboard.getSortedNicknameScore();
    }

}
