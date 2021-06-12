package view.gui;

import javafx.scene.layout.*;
import view.gui.elements.GameCard;
import view.gui.elements.GetGameElements;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DuelMenuGui extends MenuGui {
    public static void main(String[] args) {
        launch(args);
    }

    private static GameController gameController;
    private static Stage stage;
    @FXML
    public Pane fieldPane;

    @Override
    public void start(Stage stage) throws IOException {
        DuelMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("DuelMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
        stage.show();
    }

    @FXML
    private void initialize() {
        Image image = GetGameElements.getCardFieldImage();
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        fieldPane.setBackground(new Background(backgroundImage));

        fieldPane.getChildren().add(new GameCard(fieldPane, 110, 95));
    }


}
