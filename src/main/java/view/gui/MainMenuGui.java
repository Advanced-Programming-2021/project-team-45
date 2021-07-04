package view.gui;

import controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuGui extends MenuGui {
    private static Stage stage;
    private static MainMenuController mainMenuController;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuGui.fxml"));
        root.setStyle("-fx-background-color: #450000");
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
    }

    public void startDuel(MouseEvent mouseEvent) {

    }

    public void startDeckMenu(MouseEvent mouseEvent) {
        DeckMenuGui deckMenuGui = new DeckMenuGui();
        DeckMenuGui.setUsername(username);
        DeckController deckController = new DeckController(username);
        DeckMenuGui.setDeckController(deckController);
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
        ScoreBoardMenuGui scoreBoardMenuGui = new ScoreBoardMenuGui();
        ScoreBoardMenuGui.setUsername(username);
        scoreBoardMenuGui.start(stage);
    }

    public void startProfileMenu(MouseEvent mouseEvent) {
        ProfileMenuGui profileMenuGui = new ProfileMenuGui();
        ProfileMenuGui.setUsername(username);
        ProfileMenuGui.setProfileController(new ProfileController(username));
        try {
            profileMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startShopMenu(MouseEvent mouseEvent) throws Exception {
        ShopMenuGui shopMenuGui = new ShopMenuGui();
        ShopMenuGui.setUsername(username);
        shopMenuGui.start(stage);
    }

    public void startImportExportMenu(MouseEvent mouseEvent) throws Exception {
        ImportExportMenuGui importExportMenuGui = new ImportExportMenuGui();
        ImportExportMenuGui.setUsername(username);
        importExportMenuGui.start(stage);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        LoginMenuGui loginMenuGui = new LoginMenuGui();
        loginMenuGui.start(stage);
    }

    public void startCartCreatorMenu() {
        CardCreatorMenuGui menu = new CardCreatorMenuGui();
        CardCreatorMenuGui.setUsername(username);
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
