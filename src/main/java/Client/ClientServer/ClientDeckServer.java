package Client.ClientServer;

import Network.PortConfig;
import java.util.ArrayList;

public class ClientDeckServer extends ClientServer{
    public ClientDeckServer() {
        super(PortConfig.DECK_PORT.getPort(), "DeckController");
    }

    public void setDeck(String deckName) {
        sendRequest.getMethodResult("setDeck",deckName);
    }

    public void deleteDeck() {
        sendRequest.getMethodResult("deleteDeck");
    }

    public void setController() {
        sendRequest.getMethodResult("setController");
    }

    public void deleteCard(int i, int j, boolean isMain) {
        sendRequest.getMethodResult("deleteCardFromDeck",i,j,isMain);
    }

    public void addCardToMainDeck(String string) {
        sendRequest.getMethodResult("addCardToMainDeck",string);
    }

    public void addCardToSideDeck(String string) {
        sendRequest.getMethodResult("addCardToSideDeck",string);
    }

    public ArrayList<String> getAllCardNames() {
        return (ArrayList<String>) sendRequest.getMethodResult("getAllCardNames");
    }

    public ArrayList<String> getMainCardNames() {
        return (ArrayList<String>) sendRequest.getMethodResult("getMainCardNames");
    }

    public ArrayList<String> getSideCardNames() {
        return (ArrayList<String>) sendRequest.getMethodResult("getSideCardNames");
    }

    public void putCardToMainHashMap(int i, int j, String string) {
        sendRequest.getMethodResult("putCardToMainHashMap",i,j,string);
    }

    public void putCardToSideHashMap(int i, int j, String string) {
        sendRequest.getMethodResult("putCardToSideHashMap",i,j,string);
    }

    public void initialize() {
        sendRequest.getMethodResult("initialize");
    }

}
