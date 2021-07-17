package Client.ClientServer;

import Network.PortConfig;
import Server.model.card.Deck;

public class ClientDeckServer extends ClientServer{
    public ClientDeckServer() {
        super(PortConfig.DECK_PORT.getPort(), "DeckController");
    }

    public void setDeck(String deckName) {
        sendRequest.getMethodResult("setDeck",deckName);
    }

    public void deleteDeck(Deck deck) {
        sendRequest.getMethodResult("deleteDeck",deck);
    }

    public void setController() {
        sendRequest.getMethodResult("setController");
    }
}
