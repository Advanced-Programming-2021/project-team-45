package view.gui;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private static String secondPlayerUserName;
    private static Stage stage;
    public Button button;
    private ImageView imageView;
    private boolean canStartGame = false;
    @FXML
    public BorderPane borderPane;
    public VBox vBox;
    public HBox secondPlayerHBox;
    public Text secondPlayerText;
    public VBox firstPlayerHBox;
    public Text firstPlayerText;

    public static void setUserNames(String firstUserName, String secondUsername) {
        CoinTossMenu.firstUserName = firstUserName;
        CoinTossMenu.secondUsername = secondUsername;
    }

    public void tossCoin() {
        int random = new Random().nextInt(2);
        if (random == 0) {
            firstPlayerUserName = firstUserName;
            secondPlayerUserName = secondUsername;
        } else {
            firstPlayerUserName = secondUsername;
            secondPlayerUserName = firstUserName;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CoinTossMenu.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("CoinTossMenu.fxml"));
        stage.setScene(new Scene(root, 1080, 720));
        stage.setTitle("Coin Toss");
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
    }

    public void startToss() throws InterruptedException {
        TimerTask timerTask = new TimerTask() {
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
                        break;
                    }
                }
                showResult(firstPlayerUserName);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0);
        canStartGame = true;
        button.setText("Start Game");
    }

    public void startGame() {
        GameController gameController = new GameController(firstPlayerUserName, secondPlayerUserName, 1);
        DuelMenuGui duelMenuGui = new DuelMenuGui();
        DuelMenuGui.setGameController(gameController);
        gameController.setPlayerDuelMenu(duelMenuGui);
        gameController.startGame();
        try {
            duelMenuGui.start(stage);
        } catch (Exception ignored) {
        }
    }

    public void handleButton(MouseEvent mouseEvent) {
        if (canStartGame) {
            startGame();
        } else {
            try {
                startToss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
