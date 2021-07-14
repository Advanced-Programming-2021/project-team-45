package Client.view;

import Client.ClientServer.ClientShopServer;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.gilecode.yagson.YaGson;
import com.google.gson.reflect.TypeToken;
import Server.controller.ShopController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.card.SpellTrapCard;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ImportExportMenuGui extends MenuGui {
    private static Stage stage;
    private static Stage popUpWindow;
    private static String loggedInUsername;
    private String selectedCardName;
    @FXML
    public GridPane gridPane = new GridPane();
    public BorderPane borderPane;
    public Text text;
    public ArrayList<Button> buttons;

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
        buttons = new ArrayList<>();
        text.setText("selected card: ");
        HashMap<String, Integer> cards = new ClientShopServer().getCardsPrices();
        for (int index = 0; index < cards.keySet().size(); index++) {
            String cardName = (String) cards.keySet().toArray()[index];
            buttons.add(new CardButton(cardName, this));
        }
        for (int i = 0; i < buttons.size(); i++) {
            int x = i % 7;
            int y = i / 7;
            gridPane.add(buttons.get(i), x, y);
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


    public void exportCardTOJSON(MouseEvent mouseEvent) {
        File file = GetInput.getDirectory();
        if (selectedCardName == null)
            ShowOutput.showOutput("Error", "you must choose a card");
        else {
            if (file != null) {
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

    public void exportCardToCSV(MouseEvent mouseEvent) throws IOException {
        File file = GetInput.getDirectory();
        if (selectedCardName == null)
            ShowOutput.showOutput("Error", "you must choose a card");
        else {
            if (file != null) {
                Card card = Card.getCardByName(selectedCardName);
                ArrayList<String[]> data = null;
                if (card instanceof MonsterCard)
                    data = getCardDataString(card, true);
                else
                    data = getCardDataString(card, false);
                CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsolutePath() + "\\" + selectedCardName + ".csv"));
                writer.writeAll(data);
                writer.flush();
                ShowOutput.showOutput("Success", selectedCardName + ".csv exported successfully");
            }
        }
    }

    private ArrayList<String[]> getCardDataString(Card card, boolean isMonsterCard) {
        ArrayList<String[]> data = new ArrayList<>();
        if (isMonsterCard) {
            String[] dataNames = {"Name", "Level", "Attribute", "Monster Type", "Card Type", "Atk", "Def", "Description", "Price"};
            data.add(dataNames);
            MonsterCard monsterCard = (MonsterCard) card;
            String[] mainData = {monsterCard.getCardName(), String.valueOf(monsterCard.getLevel()), monsterCard.getAttribute().toString(),
                    monsterCard.getType().toString(), monsterCard.getCardType(), String.valueOf(monsterCard.getAttack()),
                    String.valueOf(monsterCard.getDefense()), monsterCard.getCardDescription(), String.valueOf(monsterCard.getPrice())};
            data.add(mainData);
        } else {
            SpellTrapCard spellTrapCard = (SpellTrapCard) card;
            String[] dataNames = {"Name", "Type", "Icon (Property)", "Description", "Status", "Price"};
            data.add(dataNames);
            String[] mainData = {spellTrapCard.getCardName(), spellTrapCard.getCardType(), spellTrapCard.getIcon().toString(),
            spellTrapCard.getCardDescription(), spellTrapCard.getStatus(), String.valueOf(spellTrapCard.getPrice())};
            data.add(mainData);
        }
        return data;
    }


    public void importCard(MouseEvent mouseEvent) throws IOException, IllegalAccessException {
        File file = GetInput.chooseFile();
        if (file != null) {
            String[] parts = file.getName().split("\\.");
            System.out.println(parts[1]);
            if (parts[1].equals("json")) {
                File newFile = new File("src/main/resources/Client.view/gui/importFiles/" + file.getName());
                FileUtils.copyFile(file, newFile);
                String jsnStr = new String(Files.readAllBytes(Paths.get("src/main/resources/Client.view/gui/importFiles/"+ file.getName())));
                YaGson yaGson = new YaGson();
                Card card = yaGson.fromJson(jsnStr, new TypeToken<Card>(){}.getType());
                if (card != null)
                    showCardData(card, null, file.getName());
                else
                    ShowOutput.showOutput("Error", "you must import a Card Type Object");
            } else if (parts[1].equals("csv")) {
                try {
                    CSVReader reader = new CSVReader(new FileReader(file));
                    String[] nextLine = null;
                    ArrayList<String[]> results = new ArrayList<>();
                    while ((nextLine = reader.readNext()) != null) {
                        results.add(nextLine);
                    }
                    showCardData(null, results, file.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showCardData(Card card, ArrayList<String[]> results, String fileName) {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        BorderPane borderPane = new BorderPane();
        Text text = new Text(fileName + " imported successfully");
        text.setFont(new Font("Bold", 16));
        text.setStyle("-fx-fill: white");
        HBox hBox = new HBox(text);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: black");
        borderPane.setTop(hBox);

        if (card != null) {
            setCenterOfBorderPaneForJson(card, borderPane);
        } else {
            setCenterOfBorderPaneForCSV(results, borderPane);
        }

        Button button = new Button("ok");
        button.setStyle("-fx-background-color: red; -fx-text-fill: dark #050572");
        button.setOnAction(e -> popUpWindow.close());
        HBox hBox1 = new HBox(button);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setStyle("-fx-background-color: black");
        borderPane.setBottom(hBox1);

        Scene scene = new Scene(borderPane);
        popUpWindow.setScene(scene);
        popUpWindow.setTitle("card information");
        popUpWindow.showAndWait();
    }

    private void setCenterOfBorderPaneForJson(Card card, BorderPane borderPane) {
        Text centerText = new Text(Card.showCard(card) + "price: " + card.getPrice() + "\nspeed: " + card.getSpeed());
        centerText.setFont(new Font("Arial", 14));
        centerText.setStyle("-fx-fill: white");
        VBox vBox = new VBox(centerText);
        vBox.setStyle("-fx-background-color: black");
        borderPane.setCenter(vBox);
    }

    private void setCenterOfBorderPaneForCSV(ArrayList<String[]> results, BorderPane borderPane) {
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: black");
        for (int i = 0; i < results.get(0).length; i++) {
            Text centerText = new Text();
            String line = "";
            int counter = 0;
            for (String[] data : results) {
                if (counter == 0)
                    line = line + " " + data[i];
                else
                    line = line + ": " + data[i];
                counter++;

            }
            centerText.setText(line);
            centerText.setFont(new Font("Arial", 14));
            centerText.setStyle("-fx-fill: white");
            vBox.getChildren().add(centerText);
        }
        borderPane.setCenter(vBox);
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        MainMenuGui.setUsername(username);
        mainMenuGui.start(stage);
    }
}
