package Client.ClientServer;

import Network.PortConfig;
import Server.controller.CardCreatorController;

public class ClientCardCreatorServer extends ClientServer{
    public ClientCardCreatorServer() {
        super(PortConfig.CARD_CREATOR_PORT.getPort(), "CardCreatorController");
    }

    public void addCardToTheNetwork(CardCreatorController controller) {
        sendRequest.getMethodResult("addCardToTheNetWork",controller.getCardName(),
                controller.getAllEffects(), controller.getCreatedCard());
    }
}
