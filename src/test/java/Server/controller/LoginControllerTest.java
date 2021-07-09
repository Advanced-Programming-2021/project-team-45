package Server.controller;

import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private static LoginController loginController;
    @BeforeAll
    public static void setBeforeTest() {
        new User("ali", "ali13", "ali");
        new User("hossein", "hos1380", "hossein");
        loginController = new LoginController();
    }

    @Test
    void loginUserErrorHandler() {
        assertEquals(1, loginController.loginUserErrorHandler("ali", "ali14"));
        assertEquals(0, loginController.loginUserErrorHandler("ali", "ali13"));
    }

    @Test
    void createUserErrorHandler() {
        assertEquals(1, loginController.createUserErrorHandler("ali", "1380", "1380"));
        assertEquals(2, loginController.createUserErrorHandler("hajji", "hossein", "1380"));
        assertEquals(0, loginController.createUserErrorHandler("hajji", "hajji", "hajji"));
        assertNotNull(User.getUserByUsername("hajji"));
    }



    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("ali");
        User.deleteUserByUsername("hossein");
    }
}