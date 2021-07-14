package Client.view;

import Client.ClientServer.ClientDeckStarterServer;
import Server.controller.DeckStarterController;
import Server.model.card.Deck;
import Server.model.user.User;
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
    private static DeckStarterController deckStarterController;
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
        deckStarterController = new DeckStarterController(username);
        ArrayList<Deck> allUserDeck=getServer().getDecks();
        Deck deck=getServer().getActiveDeck();
        deckStarterController.initializeFromMenu(chooseActiveDeck, chooseEditDeck,allUserDeck,deck);
    }

    public void editADeck(MouseEvent mouseEvent) {
        if (chooseEditDeck.getValue() == null) {
            ShowOutput.showOutput("error box", "you have to choose a deck");
        } else {
            User user=getServer().getUser();
            deckStarterController.startEditDeck(stage,user, chooseEditDeck.getValue());
        }
    }

    public void createANewDeck(MouseEvent mouseEvent) {
        if (nameOfDeck.getText() == null || nameOfDeck.getText().equals("")) {
            ShowOutput.showOutput("error box", "you have to write a name for your deck");
        } else {
            ShowOutput.showOutput("AlertBox", "new deck created successfully");
            User user=getServer().getUser();
            Deck deck=getServer().createNewDeckAndGetIt(nameOfDeck,user);
            deckStarterController.startCreateADeck(stage, user,deck);
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
            deckStarterController.setActiveDeck(chooseActiveDeck.getValue());
        }
    }
}
