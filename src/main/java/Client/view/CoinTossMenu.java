package Client.view;

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
import java.util.Timer;
import java.util.TimerTask;

public class CoinTossMenu extends MenuGui {
    private static final String coinImageRoute = "src/main/resources/Client/view/Gold/Gold_";
    private static String playerUsername;
    private static String opponentUsername;
    private static String winnerUsername;
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

    public static void setUserNames(String playerUsername, String opponentUsername) {
        CoinTossMenu.playerUsername = playerUsername;
        CoinTossMenu.opponentUsername = opponentUsername;
    }

    public static void setWinner(boolean isWinner) {
        if (isWinner)
            winnerUsername = playerUsername;
        else
            winnerUsername = opponentUsername;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CoinTossMenu.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("CoinTossMenu.fxml"));
        stage.setScene(new Scene(root, 1080, 720));
    }

    @FXML
    void initialize() throws InterruptedException {
        firstPlayerText.setText(playerUsername);
        secondPlayerText.setText(opponentUsername);
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
        if (firstPlayerUserName.equals(playerUsername)) {
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
                showResult(winnerUsername);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0);
        canStartGame = true;
        button.setText("Start Game");
    }

    public void startGame() {
        MusicPlayer.muteMainMenu();
        DuelMenuGui duelMenuGui = DuelMenuGui.getDuelMenuGui();
        try {
            MusicPlayer.playDuelMenuMusic();
            MusicPlayer.unMuteDuelMenuMusic();
            duelMenuGui.start(stage);
        } catch (Exception ignored) {
            ignored.printStackTrace();
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
