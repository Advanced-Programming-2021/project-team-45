package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.DeckController;
import Server.model.user.User;

import java.net.Socket;

public class DeckRequestHandler extends RequestHandler {
    public DeckRequestHandler(Socket socket) {
        super(socket);
    }

    private static DeckController deckController;

    public static void setDeckController(DeckController deckController) {
        DeckRequestHandler.deckController = deckController;
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";
        User user = DatabaseController.getUserByToken(token);
        switch (methodName) {
            case "initialize":
                deckController.initialize();
                break;
            case "setDeck":
                deckController.setDeck(user.getUserDeck().getDeckByName((String) fields[0]));
                break;
            case "deleteDeck":
                deckController.deleteDeck();
                break;
            case "setController":
                setDeckController(new DeckController(DatabaseController.getUserByToken(token).getUsername()));
                break;
            case "deleteCardFromDeck":
                deckController.deleteCardFromDeck((int) fields[0], (int) fields[1], (boolean) fields[2]);
                break;
            case "addCardToSideDeck":
                deckController.addCardToSideDeck((String) fields[0]);
                break;
            case "addCardToMainDeck":
                deckController.addCardToMainDeck((String) fields[0]);
                break;
            case "getAllCardNames":
                answer=deckController.getAllCardsName();
                break;
            case "getMainCardNames":
                answer=deckController.getMainCardNames();
                break;
            case "getSideCardNames":
                answer=deckController.getSideCardNames();
                break;
            case "putCardToMainHashMap":
                deckController.putCardToMainHashMap((int)fields[0],(int)fields[1],(String)fields[2]);
                break;
            case "putCardToSideHashMap":
                deckController.putCardToSideHashMap((int)fields[0],(int)fields[1],(String)fields[2]);
                break;


        }
        return fieldParser.getAnswer(answer);
    }
}
