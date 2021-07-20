package Server.controller;

import Server.model.user.User;

public class LoginController {

    public int createUserErrorHandler(String username, String nickname, String password) {
        if (User.doesUsernameExist(username)) {
            return 1;

        } else if (User.doesNicknameExist(nickname)) {
            return 2;

        }
        // create new user:
        new User(username, password, nickname);
        return 0;
    }

    public String loginUserErrorHandler(String username, String password) {
        if (!User.isUserPassCorrect(username, password)) {
            return "null";
        }
        return getToken(username);
    }

    public void logout(String token) {
        DatabaseController.logout(token);
    }

    private String getToken(String username) {
        return DatabaseController.getToken(username);
    }
}
