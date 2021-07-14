package Server.ServerController;

import Server.controller.CardCreatorController;
import Server.model.card.Card;
import Server.model.card.SpellTrapCards.effects.Effect;
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
            case "addToCheckBoxes":
                controller.addToCheckBoxes((CheckBox) fields[0]);
            case "calculate":
                controller.calculate(controller.getCheckBoxes(),(int)fields[0],(Spinner<Integer>) fields[1],
                        (Text) fields[2],(TextField) fields[3],(TextField) fields[4]);
                break;
            case "createACard":
                controller.createACard((ArrayList<CheckBox>) fields[0],(int) fields[1],(String) fields[2],
                        (int) fields[3],(String) fields[4],(String) fields[5],(String) fields[6],(String) fields[7]);
                break;
            case "hasEnoughMoney":
                controller.hasEnoughMoney();
            case "addCardToTheNetWork":
                CardCreatorController.addCardToTheNetWork((Card)fields[2],(ArrayList<Effect>) fields[1],
                        (String) fields[0]);
        }

        return fieldParser.getAnswer(answer);
    }
}
