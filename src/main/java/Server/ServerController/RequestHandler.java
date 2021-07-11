package Server.ServerController;

import NetworkConfiguration.PortConfig;

import java.net.Socket;

public class RequestHandler extends Thread {
    public static RequestHandler getRequestHandler(Socket socket, int port) {
        RequestHandler requestHandler = null;
        if (port == PortConfig.LOGIN_PORT.getPort()) {
            requestHandler = new LoginRequestHandler(socket);
        } else if (port == PortConfig.PROFILE_PORT.getPort()) {
            requestHandler = new ProfileRequestHandler(socket);
        }
        return requestHandler;
    }
}
