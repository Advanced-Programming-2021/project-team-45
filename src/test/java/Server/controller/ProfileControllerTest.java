package Server.controller;

import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {

    private static ProfileController profileController;
    @BeforeAll
    public static void setBeforeTest() {
        new User("hajji", "hajji", "amir");
        new User("reza", "rezaie", "amirreza");
        profileController = new ProfileController("hajji");
    }

    @Test
    void changePasswordErrorHandler() {
        assertEquals(1, profileController.changePasswordErrorHandler("haji", "haji1380"));
        assertEquals(2, profileController.changePasswordErrorHandler("hajji", "hajji"));
        assertEquals(0, profileController.changePasswordErrorHandler("hajji", "haji1380"));
        assertEquals("haji1380", User.getUserByUsername("hajji").getPassword());
    }


    @Test
    void changeNicknameErrorHandler() {
        assertEquals(1, profileController.changeNicknameErrorHandler("amirreza"));
        assertEquals(0, profileController.changeNicknameErrorHandler("haji"));
        assertEquals("haji", User.getUserByUsername("hajji").getNickname());
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
        User.doesUsernameExist("reza");
    }

}