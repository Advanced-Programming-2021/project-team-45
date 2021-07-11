package Client.ClientServer;

import NetworkConfiguration.PortConfig;

public class ClientDuelServer extends ClientServer{
    public ClientDuelServer() {
        super(PortConfig.DUEL_PORT.getPort(), "GameController");
    }



}
