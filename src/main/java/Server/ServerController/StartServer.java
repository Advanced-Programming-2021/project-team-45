package Server.ServerController;

import NetworkConfiguration.PortConfig;

public class StartServer {
    public static void main(String[] args) {
        ServerController loginServer = new ServerController(PortConfig.LOGIN_PORT.getPort());
        loginServer.start();
        // And so on for every port...
    }
}
