package model.user;

import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    public void setUp() {
        new User("test1", "test1", "t1");
        new User("test2", "test2", "t2");
        new User("test3", "test3", "t3");
        new User("test4", "test4", "t4");
    }

    @Test
    void doesNicknameExist() {
        assertFalse(User.doesNicknameExist("ali"));
    }

    @Test
    void doesUsernameExist() {
        assertTrue(User.doesUsernameExist("test1"));
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void isUserPassCorrect() {
        assertFalse(User.isUserPassCorrect("test", "test1"));
        assertFalse(User.isUserPassCorrect("test1", "test"));
        assertTrue(User.isUserPassCorrect("test1", "test1"));
    }

    @Test
    void isPasswordCorrect() {
        User user = User.getUserByUsername("test1");
        assert user != null;
        assertFalse(user.isPasswordCorrect("test"));
        assertTrue(user.isPasswordCorrect("test1"));
    }

    @Test
    void testEquals() {
        User user1 = User.getUserByUsername("test1");
        User user2 = User.getUserByUsername("test2");
        User user3 = User.getUserByUsername("test1");

        assertNotEquals(user1, null);
        assertNotEquals(user2, user1);
        assertNotEquals(user1, new Card("test"));
        assertEquals(user3, user1);
    }
}