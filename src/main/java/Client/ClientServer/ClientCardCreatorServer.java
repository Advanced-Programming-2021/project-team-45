package Client.ClientServer;

import Network.PortConfig;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ClientCardCreatorServer extends ClientServer{
    public ClientCardCreatorServer() {
        super(PortConfig.CARD_CREATOR_PORT.getPort(), "CardCreatorController");
    }

    public void calculate(ArrayList<CheckBox> checkBoxes, int price, Spinner<Integer> level,
                          Text price1, TextField attack, TextField defense) {
        sendRequest.getMethodResult("calculate",checkBoxes,price,level,price1,attack,defense);
    }

    public void createACard(ArrayList<CheckBox> allOnEffects, Integer value, String text, int parseInt,
                            String text1, String value1, String text2, String text3) {
        sendRequest.getMethodResult("createACard",allOnEffects,value,text,parseInt, text1,value1,text2,
                text3);
    }

    public boolean hasEnoughMoney() {
        return (boolean) sendRequest.getMethodResult("hasEnoughMoney");
    }
}
