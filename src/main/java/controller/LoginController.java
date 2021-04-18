package controller;

public class LoginController {



    public int createUserErrorHandler(String username, String nickname, String password) {
        if (doesUsernameExist(username)) {
            return 1;

        } else if (doesNicknameExist(nickname)) {
            return 2;

        }
        createUser(username, nickname, password);
        return 0;
    }

    public int loginUserErrorHandler(String username, String password) {
        if (!isUserPassCorrect(username, password)) {
            return 1;
        }
        return 0;
    }

}
