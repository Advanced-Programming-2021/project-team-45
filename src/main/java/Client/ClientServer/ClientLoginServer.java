package Client.ClientServer;

import Network.PortConfig;

public class ClientLoginServer extends ClientServer {
    public ClientLoginServer() {
        super(PortConfig.LOGIN_PORT.getPort(), "LoginController");
    }

    public int createUserErrorHandler(String username, String nickname, String password) {
        Object result = sendRequest.getMethodResult("createUserErrorHandler", username, nickname, password);
        return (int) result;
    }

    public int loginUserErrorHandler(String username, String password) {
        Object result = sendRequest.getMethodResult("loginUserErrorHandler", username, password);
        String token = (String) result;
        if (!token.equals("null")) {
            SendRequest.setToken(token);
            // Client starts listening for server requests:
            ClientListener clientListener = new ClientListener();
            clientListener.setListen(true);
            clientListener.start();
            return 0;
        } else {
            return 1;
        }
    }
}
