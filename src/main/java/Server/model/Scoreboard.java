package Server.model;

import Server.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Scoreboard {

    public static LinkedHashMap<String, Integer> getSortedNicknameScore() {
        LinkedHashMap<String, Integer> nicknameScore = new LinkedHashMap<>();

        ArrayList<User> users = User.getUsers();

        for (User user : users) {
            String nickname = user.getNickname();
            Integer score = user.getScore();
            nicknameScore.put(nickname, score);
        }

        return sort(nicknameScore);
    }

    private static LinkedHashMap<String, Integer> sort(LinkedHashMap<String, Integer> nicknameScore) {
        ArrayList<String> keySetList = new ArrayList<>();
        keySetList.addAll(nicknameScore.keySet());

        for (int i = 0; i < keySetList.size() - 1; i++) {
            for (int j = 0; j < keySetList.size() - 1; j++) {
                String nickname1 = keySetList.get(j);
                Integer score1 = nicknameScore.get(nickname1);
                String nickname2 = keySetList.get(j + 1);
                Integer score2 = nicknameScore.get(nickname2);

                if (!isOrderCorrect(nickname1, score1, nickname2, score2)) {
                    Collections.swap(keySetList, j, j + 1);
                }
            }
        }

        LinkedHashMap<String, Integer> sortedNicknameScore = new LinkedHashMap<>();
        for (String key : keySetList) {
            sortedNicknameScore.put(key, nicknameScore.get(key));
        }

        return sortedNicknameScore;
    }

    private static boolean isOrderCorrect(String nickname1, Integer score1, String nickname2, Integer score2) {
        if (score1 > score2) return true;
        if (score2 > score1) return false;

        int result = nickname1.compareTo(nickname2);
        return result < 0;
    }

}
