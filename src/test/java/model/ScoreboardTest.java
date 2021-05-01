package model;

import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;


    @BeforeEach
    public void setUp() {
        user1 = new User("test1", "test1", "ali");
        user2 = new User("test2", "test2", "2kasra");
        user3 = new User("test3", "test3", "_hossein");
        user4 = new User("test4", "test4", "kysre");
        user5 = new User("test5", "test5", "hossein");
        user6 = new User("test6", "test6", "hossein1");
        user7 = new User("test7", "test7", "hossein2");
    }

    @Test
    public void testLexicographicalSorting() {
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();

        linkedMap.put("_hossein", 0);
        linkedMap.put("2kasra", 0);
        linkedMap.put("ali", 0);
        linkedMap.put("hossein", 0);
        linkedMap.put("hossein1", 0);
        linkedMap.put("hossein2", 0);
        linkedMap.put("kysre", 0);

        assertEquals(linkedMap, Scoreboard.getSortedNicknameScore());
    }

    @Test
    public void testScoreSorting() {
        user1.increaseScore(10);
        user2.increaseScore(20);
        user3.increaseScore(30);
        user4.increaseScore(40);
        user5.increaseScore(50);
        user6.increaseScore(100);
        user7.decreaseScore(10);

        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();

        linkedMap.put("hossein1", 100);
        linkedMap.put("hossein", 50);
        linkedMap.put("kysre", 40);
        linkedMap.put("_hossein", 30);
        linkedMap.put("2kasra", 20);
        linkedMap.put("ali", 10);
        linkedMap.put("hossein2", -10);

        
        LinkedHashMap<String, Integer> testMap = Scoreboard.getSortedNicknameScore();




    }

    @Test
    public void testSameScoreSorting() {
    }
}