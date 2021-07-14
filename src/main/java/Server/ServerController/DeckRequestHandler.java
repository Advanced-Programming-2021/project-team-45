package Server.ServerController;

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

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";
        User user = DatabaseController.getUserByToken(token);
        DeckController deckController = new DeckController(user.getUsername());
        switch (methodName) {
            case "setDeck":
                if (user.getUserDeck().getActiveDeck() != null) {
                    if (user.getUserDeck().getActiveDeck().getName().equals(((Deck) fields[0]).getName())) {
                        user.getUserDeck().deleteDeckFromUserDecks(((Deck) fields[0]).getName());
                        user.getUserDeck().getUserDecks().add((Deck) fields[0]);
                        user.getUserDeck().activateDeck(((Deck) fields[0]).getName());
                    } else {
                        user.getUserDeck().deleteDeckFromUserDecks(((Deck) fields[0]).getName());
                        user.getUserDeck().getUserDecks().add((Deck) fields[0]);
                    }
                } else {
                    user.getUserDeck().deleteDeckFromUserDecks(((Deck) fields[0]).getName());
                    user.getUserDeck().getUserDecks().add((Deck) fields[0]);
                }
                break;
            case "deleteDeck":
                user.getUserDeck().deleteDeckFromUserDecks(((Deck) fields[0]).getName());

        }
        return fieldParser.getAnswer(answer);
    }
}
