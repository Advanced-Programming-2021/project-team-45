package view.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShopMenuGui extends Application {
    private static Stage stage;
    public GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ShopMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("ShopMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("shop");
    }

    @FXML
    void initialize() {

    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}
