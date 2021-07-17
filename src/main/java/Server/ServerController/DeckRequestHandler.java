package Server.ServerController;

import Client.view.DeckMenuGui;
import Server.controller.DatabaseController;
import Server.controller.DeckController;
import Server.controller.DeckStarterController;
import Server.model.card.Deck;
import Server.model.user.User;

import javax.crypto.spec.DESKeySpec;
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
            case "setDeck":
                deckController.setDeck(user.getUserDeck().getDeckByName((String) fields[0]));
                break;
            case "deleteDeck":
                user.getUserDeck().deleteDeckFromUserDecks(((Deck) fields[0]).getName());
                break;
            case "setController":
                setDeckController(new DeckController(DatabaseController.getUserByToken(token).getUsername()));
                break;

        }
        return fieldParser.getAnswer(answer);
    }
}
