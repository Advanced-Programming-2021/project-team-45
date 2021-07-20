package Server.ServerController;

import Network.PortConfig;
import Server.controller.DatabaseController;

import java.util.Scanner;

public class StartServer {
    public static void main(String[] args) {
        ServerController loginServer = new ServerController(PortConfig.LOGIN_PORT.getPort());
        loginServer.start();
        ServerController duelServer = new ServerController(PortConfig.DUEL_PORT.getPort());
        duelServer.start();
        ServerController shopServer = new ServerController(PortConfig.SHOP_PORT.getPort());
        shopServer.start();
        ServerController profileServer = new ServerController(PortConfig.PROFILE_PORT.getPort());
        profileServer.start();
        ServerController cardCreatorServer = new ServerController(PortConfig.CARD_CREATOR_PORT.getPort());
        cardCreatorServer.start();
        ServerController DeckServer = new ServerController(PortConfig.DECK_PORT.getPort());
        DeckServer.start();
        ServerController deckStarterServer = new ServerController(PortConfig.DeckStarter_PORT.getPort());
        deckStarterServer.start();
        ServerController lobbyServer = new ServerController(PortConfig.LOBBY_PORT.getPort());
        lobbyServer.start();
        ServerController updateClientServer = new ServerController(PortConfig.UPDATE_CLIENT_PORT.getPort());
        updateClientServer.start();
        ServerController scoreboardServer = new ServerController(PortConfig.SCOREBOARD_PORT.getPort());
        scoreboardServer.start();
        // And so on for every port...


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String ignored = scanner.nextLine();
            DatabaseController.exportUsers();
        }
    }
}
