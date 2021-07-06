package view.gui;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CoinTossMenu extends Application {
    private static final String coinImageRoute = "src/main/resources/view/gui/Gold/Gold_";
    private static String firstUserName;
    private static String secondUsername;
    private static String firstPlayerUserName;
    private static Stage stage;
    private ImageView imageView;
    private Timer timer;
    private TimerTask timerTask;
    private static String[] players;
    @FXML
    public BorderPane borderPane;
    public VBox vBox;
    public HBox secondPlayerHBox;
    public Text secondPlayerText;
    public HBox firstPlayerHBox;
    public Text firstPlayerText;

    public static void setUserNames(String firstUserName, String secondUsername) {
        CoinTossMenu.firstUserName = firstUserName;
        CoinTossMenu.secondUsername = secondUsername;
    }

    public String[] tossCoin() {
        String[] result = new String[2];
        int random = new Random().nextInt(2);
        if (random == 0) {
            firstPlayerUserName = firstUserName;
            result[0] = firstUserName;
            result[1] = secondUsername;
        } else {
            firstPlayerUserName = secondUsername;
            result[0] = secondUsername;
            result[1] = firstUserName;
        }
        players = result;
        return result;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CoinTossMenu.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("CoinTossMenu.fxml"));
        stage.setScene(new Scene(root, 1080, 720));
        stage.setTitle("Coin Toss");
        stage.show();
    }

    @FXML
    void initialize() throws InterruptedException {
        firstPlayerText.setText(firstUserName);
        secondPlayerText.setText(secondUsername);
        try {
            imageView = new ImageView(new Image(new FileInputStream(coinImageRoute + "1.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);
        vBox.getChildren().add(imageView);
        showTossCoin();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showTossCoin() throws InterruptedException {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int i = 1;
                int counter = 0;
                while (true) {
                    i++;
                    counter++;
                    if (i == 21)
                        i = 1;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        imageView.setImage(new Image(new FileInputStream(coinImageRoute + i + ".png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (counter == 100) {
                        showResult(firstPlayerUserName);
                        timer.cancel();
                        break;
                    }
                }
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 2000);
    }

    public void startGame() {
        int rounds = 1;
        GameController gameController = new GameController(players[0], players[1], rounds);
        DuelMenuGui duelMenuGui = new DuelMenuGui();
        DuelMenuGui.setGameController(gameController);
        gameController.setPlayerDuelMenu(duelMenuGui);
        gameController.startGame();
        try {
            duelMenuGui.start(stage);
        } catch (Exception ignored) {
        }
    }

    public void showResult(String firstPlayerUserName) {
        if (firstPlayerUserName.equals(firstUserName)) {
            firstPlayerText.setText(firstPlayerText.getText() + " plays first");
            try {
                imageView.setImage(new Image(new FileInputStream(coinImageRoute + "1.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            firstPlayerHBox.setStyle("-fx-background-color: green");
            secondPlayerHBox.setStyle("-fx-background-color: red");
        } else {
            secondPlayerText.setText(secondPlayerText.getText() + " plays first");
            try {
                imageView.setImage(new Image(new FileInputStream(coinImageRoute + "11.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            firstPlayerHBox.setStyle("-fx-background-color: red");
            secondPlayerHBox.setStyle("-fx-background-color: green");
        }
        // go to next scene ...
    }
}
