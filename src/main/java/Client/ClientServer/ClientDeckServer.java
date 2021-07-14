package Client.ClientServer;

import Network.PortConfig;
import Server.model.card.Deck;

public class ClientDeckServer extends ClientServer{
    public ClientDeckServer() {
        super(PortConfig.DECK_PORT.getPort(), "DeckController");
    }

    public void setDeck(Deck deck) {
        sendRequest.getMethodResult("setDeck",deck);
    }

    public void deleteDeck(Deck deck) {
        sendRequest.getMethodResult("deleteDeck",deck);
    }
}
