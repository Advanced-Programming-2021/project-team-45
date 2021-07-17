package Client.view;

import Client.ClientServer.ClientDeckServer;
import Client.ClientServer.ClientDeckStarterServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DeckStarterMenuGui extends MenuGui {
    @FXML
    public ChoiceBox<String> chooseActiveDeck;
    public ChoiceBox<String> chooseEditDeck;
    public TextField nameOfDeck;
    private static AnchorPane anchorPane;
    private static Stage stage;
    private static ClientDeckStarterServer deckStarterServer;

    private ClientDeckStarterServer getServer(){
        if(deckStarterServer!=null) return deckStarterServer;
        deckStarterServer=new ClientDeckStarterServer();
        return deckStarterServer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = FXMLLoader.load(getClass().getResource("DeckStarterMenuGui.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        DeckStarterMenuGui.stage = stage;
    }

    @FXML
    void initialize() {
        getServer().setDeckStarterController();
        ArrayList<String> allDeckNames=getServer().getDeckNames();
        String activeDeck=getServer().getActiveDeckName();
        for (String allDeckName : allDeckNames) {
            chooseEditDeck.getItems().add(allDeckName);
            chooseActiveDeck.getItems().add(allDeckName);
        }
        if(activeDeck!=null){
            chooseActiveDeck.setValue(activeDeck);
        }
    }

    public void editADeck(MouseEvent mouseEvent) {
        if (chooseEditDeck.getValue() == null) {
            ShowOutput.showOutput("error box", "you have to choose a deck");
        } else {
            startDeckMenuForEdit();
        }
    }

    private void startDeckMenuForEdit() {
        DeckMenuGui deckMenuGui=new DeckMenuGui();
        DeckMenuGui.setUsername(getServer().getUserName());
        ClientDeckServer deckServer=new ClientDeckServer();
        deckServer.setController();
        deckServer.setDeck(chooseEditDeck.getValue());
        DeckMenuGui.setDeckServer(deckServer);
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createANewDeck(MouseEvent mouseEvent) {
        if (nameOfDeck.getText() == null || nameOfDeck.getText().equals("")) {
            ShowOutput.showOutput("error box", "you have to write a name for your deck");
        } else {
            ShowOutput.showOutput("AlertBox", "new deck created successfully");
            startCreateDeck();
        }
    }

    private void startCreateDeck() {
        DeckMenuGui deckMenuGui=new DeckMenuGui();
        DeckMenuGui.setUsername(getServer().getUserName());
        getServer().createDeck(nameOfDeck.getText());
        ClientDeckServer deckServer=new ClientDeckServer();
        deckServer.setController();
        DeckMenuGui.setDeckServer(deckServer);
        deckServer.setDeck(nameOfDeck.getText());
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(MouseEvent mouseEvent) {
        try {
            new MainMenuGui().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseActiveDeck(MouseEvent mouseEvent) {
        if (chooseActiveDeck.getValue() == null || chooseActiveDeck.getValue().equals("")) {
            ShowOutput.showOutput("Error Box", "please choose a active deck");
        } else {
            getServer().setActiveDeck(chooseActiveDeck.getValue());
        }
    }
}
