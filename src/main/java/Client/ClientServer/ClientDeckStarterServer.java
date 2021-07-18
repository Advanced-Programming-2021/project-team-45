package Client.ClientServer;

import Network.PortConfig;


import java.util.ArrayList;

public class ClientDeckStarterServer extends ClientServer{

    public ClientDeckStarterServer() {
        super(PortConfig.DeckStarter_PORT.getPort(), "DeckStarterController");
    }


    public String getUserName() {
        return (String) sendRequest.getMethodResult("getUser");
    }


    public ArrayList<String> getDeckNames() {
        return (ArrayList<String>) sendRequest.getMethodResult("getDeckNames");
    }

    public String getActiveDeckName() {
        return (String) sendRequest.getMethodResult("getActiveDeckName");
    }

    public void startDeckForEdit() {
        sendRequest.getMethodResult("startDeckForEdit");
    }

    public void setDeckStarterController() {
        sendRequest.getMethodResult("setDeckController");
    }

    public void createDeck(String text) {
        sendRequest.getMethodResult("createDeck",text);
    }

    public void setActiveDeck(String value) {
        sendRequest.getMethodResult("setActiveDeck",value);
    }
}
