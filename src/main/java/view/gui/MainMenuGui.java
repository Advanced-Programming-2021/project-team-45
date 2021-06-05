package view.gui;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenuGui extends Application {
    private static Stage stage;
    private static MainMenuController mainMenuController;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
    }

    public void startDuel(MouseEvent mouseEvent) {
    }

    public void startDeckMenu(MouseEvent mouseEvent) {
    }

    public void startScoreBoardMenu(MouseEvent mouseEvent) {
    }

    public void startProfileMenu(MouseEvent mouseEvent) {
    }

    public void startShopMenu(MouseEvent mouseEvent) {
    }

    public void startImportExportMenu(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) {
    }
}
