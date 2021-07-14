package Client.ClientServer;

import Network.PortConfig;

public class ClientDeckServer extends ClientServer{
    protected ClientDeckServer() {
        super(PortConfig.DECK_PORT.getPort(), "DeckController");
    }
}
