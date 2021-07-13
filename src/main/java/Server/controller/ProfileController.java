package Server.controller;

import Server.model.user.User;

public class ProfileController extends Controller {

    public ProfileController(String username) {
        super(username);
    }


    public int changeNicknameErrorHandler(String nickname) {
        if (User.doesNicknameExist(nickname)) {
            return 1;

        } else {
            assert user != null;
            user.setNickname(nickname);
            return 0;

        }
    }

    public int changePasswordErrorHandler(String currentPassword, String newPassword) {
        if (!user.isPasswordCorrect(currentPassword)) {
            return 1;

        } else if (currentPassword.equals(newPassword)) {
            return 2;

        } else {
            user.setPassword(newPassword);
            return 0;
        }
    }

    public void setProfilePic(String path){
        getUser().setProfilePicturePath(path);
    }

    public User getUser(){
        return user;
    }

}
