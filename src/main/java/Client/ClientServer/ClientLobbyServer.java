package Client.ClientServer;

import Network.PortConfig;

public class ClientLobbyServer extends ClientServer {
    public ClientLobbyServer() {
        super(PortConfig.MESSENGER_PORT.getPort(), "Lobby");
    }

    public void makeMatch(int rounds) {
        sendRequest.getMethodResult("makeMatch", rounds);
    }
}
