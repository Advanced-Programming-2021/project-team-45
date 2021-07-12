package Client.view;

import Server.controller.ScoreboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ScoreBoardMenuGui extends MenuGui {
    private static Stage stage;
    @FXML
    public BorderPane borderpane;
    @FXML
    public GridPane scoreboard;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoardMenu.fxml"));
        ScoreBoardMenuGui.stage = stage;
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("Score Board");
    }

    @FXML
    void initialize() {
        LinkedHashMap<String, Integer> list = ScoreboardController.getSortedNicknameScore();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(list.keySet());

        scoreboard.add(getLabel("rank", false), 0, 0);
        scoreboard.add(getLabel("nickname", false), 1, 0);
        scoreboard.add(getLabel("score", false), 2, 0);
        for (int i = 0; i < list.size(); i++) {
            String key = keyList.get(i);
            setList(i, key, list.get(key));
        }
    }

    private void setList(int i, String nickName, Integer score) {
        boolean isLoginUser = false;
        if (nickName.equals(ScoreboardController.getNickname(username)))
            isLoginUser = true;

        scoreboard.add(getLabel(String.valueOf(i + 1), isLoginUser), 0, i + 1);
        scoreboard.add(getLabel(nickName, isLoginUser), 1, i + 1);
        scoreboard.add(getLabel(String.valueOf(score), isLoginUser), 2, i + 1);
    }

    private Label getLabel(String text, boolean isLoginUser) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Arial;" +
                "-fx-font-size: 13;" +
                "-fx-font-weight: bold; -fx-text-fill: white");
        label.setPrefWidth(500);
        label.setPrefHeight(26);
        label.setAlignment(Pos.CENTER);
        if (isLoginUser)
            label.setBackground(new Background(new
                    BackgroundFill(Color.rgb(255, 0, 0, 0.7), new CornerRadii(0), new Insets(0))));
        else
            label.setBackground(new Background(new
                    BackgroundFill(Color.rgb(0, 104, 196, 0.7), new CornerRadii(0), new Insets(0))));
        return label;
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}