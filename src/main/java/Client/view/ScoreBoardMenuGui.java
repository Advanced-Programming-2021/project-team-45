package Client.view;

import Client.ClientServer.ClientScoreBoardServer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ScoreBoardMenuGui extends MenuGui {
    private static Stage stage;
    private static ClientScoreBoardServer clientScoreBoardServer;
    public static BorderPane borderpane;
    public static GridPane scoreboard;

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardMenuGui.stage = stage;
        borderpane = new BorderPane();

        //top
        Label label = new Label("YU-GI-OH!");
        label.setFont(new Font("Arial Bold", 18));
        label.setStyle("-fx-text-fill: white");
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: black");
        vBox.setPrefSize(1080, 77);
        borderpane.setTop(vBox);

        //center
        scoreboard = new GridPane();
        scoreboard.setStyle("-fx-background-color: black");
        borderpane.setCenter(scoreboard);

        //bottom
        Button backButton = new Button("back");
        backButton.setPrefSize(100, 26);
        backButton.setOnMouseClicked(e -> backToMainMenu());
        backButton.setStyle("-fx-text-fill: white; -fx-background-color: red");
        backButton.setEffect(new DropShadow());
        VBox vBox1 = new VBox(backButton);
        vBox1.setStyle("-fx-background-color: black");
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPrefSize(1080, 66);
        borderpane.setBottom(vBox1);

        Scene scene = new Scene(borderpane, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("Score Board");
        update();
    }

    private static ClientScoreBoardServer getClientScoreBoardServer() {
        if (clientScoreBoardServer == null)
            clientScoreBoardServer = new ClientScoreBoardServer();

        return clientScoreBoardServer;
    }



    public static void update() {
        LinkedHashMap<String, Integer> list = getClientScoreBoardServer().getSortedNicknameScore();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(list.keySet());

        scoreboard.add(getLabel("rank", false), 0, 0);
        scoreboard.add(getLabel("nickname", false), 1, 0);
        scoreboard.add(getLabel("score", false), 2, 0);

        setList(0,  0, keyList.get(0), list.get(keyList.get(0)));
        int counter = 0;
        for (int i = 1; i < list.size(); i++) {
            String key = keyList.get(i);
            String beforeKey = keyList.get(i - 1);
            if (list.get(key) != list.get(beforeKey))
                counter++;
            setList(i, counter, key, list.get(key));
        }
    }

    private static void setList(int i, int rank, String nickName, Integer score) {
        boolean isLoginUser = false;
        if (nickName.equals(getClientScoreBoardServer().getNickname(username)))
            isLoginUser = true;

        scoreboard.add(getLabel(String.valueOf(rank + 1), isLoginUser), 0, i + 1);
        scoreboard.add(getLabel(nickName, isLoginUser), 1, i + 1);
        scoreboard.add(getLabel(String.valueOf(score), isLoginUser), 2, i + 1);
    }

    private static Label getLabel(String text, boolean isLoginUser) {
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

    public void backToMainMenu() {
        MainMenuGui mainMenuGui = new MainMenuGui();
        try {
            mainMenuGui.start(stage);
        } catch(Exception e) {
        }
    }
}
