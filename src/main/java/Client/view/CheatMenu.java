package Client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Client.view.elements.GetImage;

public class CheatMenu extends MenuGui {
    String[] CHEATS_REGEX = {
            "^(increase --LP (\\d+))$",
            "^(duel set-winner (\\w+))$",
            "^(increase --money (\\d+))$",
            "^setAvailable --cardname (.*)$",
            "^setUnavailable --cardname (.*)$",
            "^increaseCard --count (\\d+) --cardname (.*)$",
            "^decreaseCard --count (\\d+) --cardname (.*)$"
    };
    AnchorPane anchorPane;
    private static Stage stage;
    @FXML
    public TextField cheatField;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = FXMLLoader.load(getClass().getResource("CheatMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage1 = new Stage();
        CheatMenu.stage = stage1;
        stage1.setOpacity(0.85);
        stage1.setTitle("cheat console");
        stage1.setScene(scene);
        stage1.getIcons().add(GetImage.getGameIcon());
        stage1.setTitle("CHEAT");
        stage1.show();
    }

    public void enterTheCheat(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            stage.close();
            String input = cheatField.getText();
            if (input.matches(CHEATS_REGEX[0]))
                DuelMenuGui.getDuelMenuGui().increaseLpCheat(input);
            if (input.matches(CHEATS_REGEX[1]))
                DuelMenuGui.getDuelMenuGui().setWinnerCheat(input);
            if (input.matches(CHEATS_REGEX[2]))
                ShopMenuGui.getShopMenuGui().increaseMoneyCheat(input);
            if (input.matches(CHEATS_REGEX[3]))
                ShopMenuGui.getShopMenuGui().setAvailableCard(input);
            if (input.matches(CHEATS_REGEX[4]))
                ShopMenuGui.getShopMenuGui().setUnavailableCard(input);
            if (input.matches(CHEATS_REGEX[5]))
                ShopMenuGui.getShopMenuGui().increaseCard(input);
            if (input.matches(CHEATS_REGEX[6]))
                ShopMenuGui.getShopMenuGui().decreaseCard(input);
            //stage.close();
        }
    }
}
