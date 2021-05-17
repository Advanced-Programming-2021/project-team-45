package controller;

import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardControllerTest {

    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("kasra" ,"kasra", "kasra");
        user.increaseScore(1000);
        User user1 = new User("hajji" ,"hajji", "hajji");
        user1.increaseScore(500);
        User user2 = new User("hossein" ,"hossein", "hossein");
        user2.increaseScore(500);
        User user3 = new User("parsa" ,"parsa", "parsa");
        user3.increaseScore(250);
    }

    @Test
    void getSortedNicknameScore() {
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
        expected.put("kasra", 1000);
        expected.put("hajji", 500);
        expected.put("hossein", 500);
        expected.put("parsa", 250);
        assertEquals(expected, ScoreboardController.getSortedNicknameScore());
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("kasra");
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("hossein");
        User.deleteUserByUsername("parsa");
    }

}