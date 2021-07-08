package view.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheatMenu extends MenuGui {
    String[] CHEATS_REGEX = {
            "^(increase --LP (\\d+))$",
            "^(duel set-winner (\\w+))$",
            "^(increase --money (\\d+))$"
    };
    AnchorPane anchorPane;
    private static Stage stage;
    @FXML
    public TextField cheatField;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane= FXMLLoader.load(getClass().getResource("CheatMenu.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage1=new Stage();
        stage1.setOpacity(0.85);
        stage1.setTitle("cheat console");
        stage1.setScene(scene);
        stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void enterTheCheat(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String input = cheatField.getText();
            if (input.matches(CHEATS_REGEX[0]))
                DuelMenuGui.getDuelMenuGui().increaseLpCheat(input);
            if (input.matches(CHEATS_REGEX[1]))
                DuelMenuGui.getDuelMenuGui().setWinnerCheat(input);
            if (input.matches(CHEATS_REGEX[2]))
                ShopMenuGui.getShopMenuGui().increaseMoneyCheat(input);
            stage.close();
        }
    }
}
