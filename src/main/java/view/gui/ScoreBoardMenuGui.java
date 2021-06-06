package view.gui;

import controller.ScoreboardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoardMenuGui extends Application {
    private static Stage stage;

    @FXML
    public BorderPane borderpane;
    @FXML
    public GridPane scoreboard;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoardMenu.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("Score Board");
    }

    @FXML
    void initialize() {
        LinkedHashMap<String, Integer> list = ScoreboardController.getSortedNicknameScore();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(list.keySet());

        for (int i = 0; i < list.size(); i++) {
            String key = keyList.get(i);
            setList(i, key, list.get(key));
        }
    }

    private void setList(int i, String nickName, Integer score) {
        scoreboard.add(getLabel(String.valueOf(i + 1)), 0, i + 1);
        scoreboard.add(getLabel(nickName), 1, i + 1);
        scoreboard.add(getLabel(String.valueOf(score)), 2, i + 1);
    }

    private Label getLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Arial;" +
                "-fx-font-size: 13;" +
                "-fx-font-weight: bold;");
        label.setPrefWidth(100);
        label.setPrefHeight(26);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}
