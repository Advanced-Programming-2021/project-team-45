package view.gui;

import controller.ScoreboardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoardMenuGui extends Application {
    private static Stage stage;

    @FXML
    public BorderPane borderpane;

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoardMenu.fxml"));
        ListView listView = new ListView();
        LinkedHashMap<String,Integer> usersList = ScoreboardController.getSortedNicknameScore();
            for (Map.Entry<String, Integer> entry : usersList.entrySet()) {
                listView.getItems().add(entry.getKey() + " score: " + entry.getValue());
            }

        borderpane.setCenter(listView);
        Scene scene = new Scene(root, 720, 1080);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}
