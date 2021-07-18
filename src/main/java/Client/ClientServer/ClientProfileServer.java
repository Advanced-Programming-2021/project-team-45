package Client.ClientServer;

import Network.PortConfig;

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

    public String getUserName() {
        return (String) sendRequest.getMethodResult("getUser");
    }

    public String getNickName(){
        return (String) sendRequest.getMethodResult("getNickName");
    }
}
