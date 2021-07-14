package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.DeckStarterController;
import Server.model.user.User;
import javafx.scene.control.ChoiceBox;

import java.net.Socket;

public class DeckStarterRequestHandler extends RequestHandler{
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
        DeckStarterController controller=new DeckStarterController(user.getUsername());
        switch (methodName){
            case "getAllDeck":
                answer=controller.getAllDeck(user);
                break;
            case "getActiveDeck":
                answer=controller.getActiveDeck(user);
                break;
            case "getUser":
                answer=user;
                break;
            case "createNewDeckAndGetIt":
                answer=controller.createNewDeckAndGetIt((String) fields[0],(User) fields[1]);
                break;
        }
        return fieldParser.getAnswer(answer);
    }
}
