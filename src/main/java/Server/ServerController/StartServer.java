package Server.ServerController;

import Network.PortConfig;

public class StartServer {
    public static void main(String[] args) {
        ServerController loginServer = new ServerController(PortConfig.LOGIN_PORT.getPort());
        loginServer.start();
        ServerController duelServer = new ServerController(PortConfig.DUEL_PORT.getPort());
        duelServer.start();
        // And so on for every port...
    }
}
