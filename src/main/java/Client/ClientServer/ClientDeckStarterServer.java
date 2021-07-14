package Client.ClientServer;

import Network.PortConfig;
import Server.model.card.Deck;
import Server.model.user.User;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ClientDeckStarterServer extends ClientServer{

    public ClientDeckStarterServer() {
        super(PortConfig.DeckStarter_PORT.getPort(), "DeckStarterController");
    }

    public ArrayList<Deck> getDecks() {
        return (ArrayList<Deck>)sendRequest.getMethodResult("getAllDeck");
    }


    public Deck getActiveDeck() {
        return (Deck) sendRequest.getMethodResult("getActiveDeck");
    }

    public User getUser() {
        return (User) sendRequest.getMethodResult("getUser");
    }

    public Deck createNewDeckAndGetIt(TextField nameOfDeck, User user) {
        return (Deck) sendRequest.getMethodResult("createNewDeckAndGetIt",nameOfDeck.getText(),user);
    }
}
