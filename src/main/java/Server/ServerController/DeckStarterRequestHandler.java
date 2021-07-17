package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.DeckStarterController;
import Server.model.card.Deck;
import Server.model.user.User;
import javafx.scene.control.ChoiceBox;

import java.net.Socket;

public class DeckStarterRequestHandler extends RequestHandler{
    private static DeckStarterController controller;
    public DeckStarterRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";
        User user = DatabaseController.getUserByToken(token);
        switch (methodName){
            case "getAllDeck":
                answer=controller.getAllDeck(user);
                break;
            case "getActiveDeck":
                answer=controller.getActiveDeck(user);
                break;
            case "getUser":
                answer=user.getUsername();
                break;
            case "getDeckNames":
                answer=controller.getDeckNames();
                break;
            case "getActiveDeckName":
                answer=controller.getActiveDeckName();
                break;
            case "startDeckForEdit":
                controller.startDeckForEdit();
                break;
            case "setDeckController":
                controller=new DeckStarterController(DatabaseController.getUserByToken(token).getUsername());
                break;
            case "createDeck":
                controller.createDeck((String) fields[0]);
                break;
            case "setActiveDeck":
                controller.setActiveDeck((String) fields[0]);
                break;
        }
        return fieldParser.getAnswer(answer);
    }
}
