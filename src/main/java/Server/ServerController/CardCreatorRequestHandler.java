package Server.ServerController;

import Server.controller.CardCreatorController;
import java.net.Socket;


public class CardCreatorRequestHandler extends RequestHandler{


    public CardCreatorRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer ="";
        CardCreatorController controller=new CardCreatorController();
        switch (methodName){
            case "createNewCard":
                controller.createNewCard((Integer) fields[0],(String) fields[1],(int) fields[2],(String) fields[3],
                        (String) fields[4],(String) fields[5],(String) fields[6]);
                break;
        }

        return fieldParser.getAnswer(answer);
    }
}
