package view.gui;

import com.gilecode.yagson.YaGson;
import com.google.gson.reflect.TypeToken;
import controller.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.card.Card;
import model.user.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ImportExportMenuGui extends MainMenuGui {
    private static Stage stage;
    private String selectedCardName;
    @FXML
    public GridPane gridPane = new GridPane();
    public BorderPane borderPane;
    public Text text;
    Button[][] buttons;

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
        HashMap<String, Integer> cards = new ShopController(username).getCardsPrices();
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
        File file = GetInput.getDirectory();
        if (file != null) {
            if (selectedCardName == null)
                ShowOutput.showOutput("Error", "you must choose a card before export");
            else {
                Card card = Card.getCardByName(selectedCardName);
                YaGson yaGson = new YaGson();
                File file1 = new File(file.getAbsolutePath() + "\\" + selectedCardName + ".json");
                try {
                    FileWriter fileWriter = new FileWriter(file1.getAbsolutePath());
                    yaGson.toJson(card, fileWriter);
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ShowOutput.showOutput("Success", selectedCardName + ".json exported successfully");
            }
        }
    }

    public void importCard(MouseEvent mouseEvent) throws IOException, IllegalAccessException {
        File file = GetInput.chooseJsonFile();
        if (file != null) {
            File newFile = new File("src/main/resources/view/gui/importJsonFiles/" + file.getName());
            FileUtils.copyFile(file, newFile);
            String jsnStr = new String(Files.readAllBytes(Paths.get("src/main/resources/view/gui/importJsonFiles/"+ file.getName())));
            YaGson yaGson = new YaGson();
            Card card = yaGson.fromJson(jsnStr, new TypeToken<Card>(){}.getType());
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.setUsername(username);
        mainMenuGui.start(stage);
    }
}
