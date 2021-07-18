package Client.ClientServer;

import Network.PortConfig;

public class ClientCardCreatorServer extends ClientServer {
    public ClientCardCreatorServer() {
        super(PortConfig.CARD_CREATOR_PORT.getPort(), "CardCreatorController");
    }

    public void createCard(Integer value, String text, int parseInt, String text1, String value1, String text2,
                           String text3) {
        sendRequest.getMethodResult("createNewCard",value,text,parseInt,text1,value1,text2,text3);
    }
}
