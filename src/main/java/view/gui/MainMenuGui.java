package view.gui;

import controller.MainMenuController;
import controller.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.user.User;

import java.io.IOException;

public class MainMenuGui extends MenuGui {
    private static Stage stage;
    private static MainMenuController mainMenuController;
    private User user;

    public MainMenuGui(User user) {
        this.user=user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
    }

    public void startDuel(MouseEvent mouseEvent) {
    }

    public void startDeckMenu(MouseEvent mouseEvent) {
    }

    public void startScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
        ScoreBoardMenuGui scoreBoardMenuGui = new ScoreBoardMenuGui();
        scoreBoardMenuGui.start(stage);
    }

    public void startProfileMenu(MouseEvent mouseEvent) {
        new ProfileMenuGui(new ProfileController(""));
    }

    public void startShopMenu(MouseEvent mouseEvent) {
    }

    public void startImportExportMenu(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        LoginMenuGui loginMenuGui = new LoginMenuGui();
        loginMenuGui.start(stage);
    }
}
