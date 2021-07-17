package Client.ClientServer;

import Network.PortConfig;
import Server.model.user.User;
import javafx.scene.image.Image;


public class ClientProfileServer extends ClientServer{
    public ClientProfileServer() {
        super(PortConfig.PROFILE_PORT.getPort(), "ProfileController");
    }

    public void setProfilePicPath(String path) {
        sendRequest.getMethodResult("setProfilePic",path);
    }


    public int changeNickNameErrorHandler(String text) {
        return (int) sendRequest.getMethodResult("changeNicknameErrorHandler",text);
    }

    public int changePasswordErrorHandler(String text, String text1) {
        return (int) sendRequest.getMethodResult("changePasswordErrorHandler",text,text1);
    }

    public User getUser() {
        return (User) sendRequest.getMethodResult("getUser");
    }

    public String  getProfileImage() {
        return (String) sendRequest.getMethodResult("getProfileImage");
    }
}
