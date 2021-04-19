package controller;

import model.user.User;

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

    public int loginUserErrorHandler(String username, String password) {
        if (!User.isUserPassCorrect(username, password)) {
            return 1;
        }
        return 0;
    }

}
