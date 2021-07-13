package Server.ServerController;

import Server.controller.CardCreatorController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.Socket;
import java.util.ArrayList;

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
            case "calculate":
                controller.calculate((ArrayList<CheckBox>) fields[0],(int)fields[1],(Spinner<Integer>) fields[2],
                        (Text) fields[3],(TextField) fields[4],(TextField) fields[5]);
                break;
            case "createACard":
                CardCreatorController.createACard((ArrayList<CheckBox>) fields[0],(int) fields[1],(String) fields[2],
                        (int) fields[3],(String) fields[4],(String) fields[5],(String) fields[6],(String) fields[7]);
                break;
            case "hasEnoughMoney":
                controller.hasEnoughMoney();


        }

        return fieldParser.getAnswer(answer);
    }
}
