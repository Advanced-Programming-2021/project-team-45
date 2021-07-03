package view.gui;

import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.user.User;
import view.gui.elements.GetImage;

import java.util.HashMap;
import java.util.concurrent.RecursiveAction;

public class ImportExportMenuGui extends MainMenuGui {
    private static String loggedInUsername;
    private static Stage stage;
    private String selectedCardName;
    @FXML
    public GridPane gridPane = new GridPane();
    public BorderPane borderPane;
    public Text text;
    Button[][] buttons;

    public static void setUsername(String username) {
        loggedInUsername = username;
    }

    public void setSelectedCardName(String cardName) {
        selectedCardName = cardName;
        text.setText("selected card: " + cardName);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImportExportMenuGui.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ImportExportMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("Import and Export Menu");
    }

    @FXML
    void initialize() {
        text.setText("selected card: ");
        buttons = new Button[10][7];
        HashMap<String, Integer> cards = new ShopController(loggedInUsername).getCardsPrices();
        for (int index = 0; index < cards.keySet().size(); index++) {
            int j = index % 7;
            int i = index / 7;
            String cardName = (String) cards.keySet().toArray()[index];
            buttons[i][j] = new CardButton(cardName, this);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                gridPane.add(buttons[i][j], j, i);
            }
        }
        ScrollPane scrollPane = new ScrollPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: black");
        scrollPane.setContent(gridPane);
        borderPane.setCenter(scrollPane);
        borderPane.getCenter().setStyle("-fx-background-color: black");
        scrollPane.setStyle("-fx-background: black");
    }


    public void exportCard(MouseEvent mouseEvent) {
    }

    public void importCard(MouseEvent mouseEvent) {
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.setUser(User.getUserByUsername(loggedInUsername));
        mainMenuGui.start(stage);
    }
}
