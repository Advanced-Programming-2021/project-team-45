package view.gui;

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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CoinTossMenu extends Application {
    private static final String coinImageRoute = "src\\main\\resources\\view\\gui\\Assets\\Project-Assets-1.0.0\\Assets\\Items\\Coins\\Gold\\Gold_";
    private static String firstUserName = "haji";
    private static String secondUsername = "hossein";
    private static Stage stage;
    private ImageView imageView;
    private Timer timer;
    private TimerTask timerTask;
    private static String firstPlayerUserName;
    @FXML
    public BorderPane borderPane;
    public VBox vBox;
    public HBox secondPlayerHBox;
    public Text secondPlayerText;
    public HBox firstPlayerHBox;
    public Text firstPlayerText;

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
        imageView = new ImageView(new Image(coinImageRoute + "1.png"));
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);
        vBox.getChildren().add(imageView);
        tossCoin();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void tossCoin() throws InterruptedException {
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
                    imageView.setImage(new Image(coinImageRoute + i + ".png"));
                    if (counter == 100) {
                        int random = new Random().nextInt(2);
                        showResult(random);
                        timer.cancel();
                        break;
                    }
                }
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 2000);
    }

    public void showResult(int random) {
        if (random == 0) {
            firstPlayerText.setText(firstPlayerText.getText() + " plays first");
            imageView.setImage(new Image(coinImageRoute + "1.png"));
            firstPlayerHBox.setStyle("-fx-background-color: green");
            secondPlayerHBox.setStyle("-fx-background-color: red");
            firstPlayerUserName = firstUserName;
        }
        else {
            secondPlayerText.setText(secondPlayerText.getText() + " plays first");
            imageView.setImage(new Image(coinImageRoute + "11.png"));
            firstPlayerHBox.setStyle("-fx-background-color: red");
            secondPlayerHBox.setStyle("-fx-background-color: green");
            firstPlayerUserName = secondUsername;
        }
    }


    public static void setUserNames(String firstUserName, String secondUsername) {
        CoinTossMenu.firstUserName = firstUserName;
        CoinTossMenu.secondUsername = secondUsername;
    }
}
