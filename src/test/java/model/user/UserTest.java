package model.user;

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
    }

    @Test
    void isPasswordCorrect() {
    }

    @Test
    void testEquals() {
    }
}