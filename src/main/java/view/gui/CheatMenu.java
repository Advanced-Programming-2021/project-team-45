package view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheatMenu extends MenuGui{
    AnchorPane anchorPane;
    @Override
    public void start(Stage stage) throws Exception {
        anchorPane= FXMLLoader.load(getClass().getResource("CheatMenu.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage1=new Stage();
        stage1.setOpacity(0.77);
        stage1.setTitle("cheat console");
        stage1.setScene(scene);
        stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void enterTheCheat(KeyEvent keyEvent) {

    }
}
