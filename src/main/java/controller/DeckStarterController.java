package controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.card.Deck;
import model.user.User;
import view.gui.DeckMenuGui;

import java.util.ArrayList;

public class DeckStarterController extends Controller{


    public DeckStarterController(String username) {
        super(username);
    }


    public void initialize(ChoiceBox<String> chooseActiveDeck, ChoiceBox<String> chooseEditDeck) {
        User user=User.getUserByUsername(username);
        ArrayList<Deck> allDecks=user.getUserDeck().getUserDecks();
        for (Deck allDeck : allDecks) {
            chooseActiveDeck.getItems().add(allDeck.getName());
            chooseEditDeck.getItems().add(allDeck.getName());
        }
    }

    public void startEditDeck(Stage stage,String name) {
        User user=User.getUserByUsername(username);
        DeckMenuGui deckMenuGui=new DeckMenuGui();
        DeckMenuGui.setUsername(username);
        DeckController deckController = new DeckController(username);
        DeckMenuGui.setDeckController(deckController);
        deckController.setDeck(user.getUserDeck().getDeckByName(name));
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startCreateADeck(Stage stage, TextField textField) {
        User user=User.getUserByUsername(username);
        DeckMenuGui deckMenuGui=new DeckMenuGui();
        DeckMenuGui.setUsername(username);
        DeckController deckController = new DeckController(username);
        DeckMenuGui.setDeckController(deckController);
        user.getUserDeck().createDeck(textField.getText(),user);
        Deck deck=user.getUserDeck().getDeckByName(textField.getText());
        deckController.setDeck(deck);
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActiveDeck(String name) {
        user.getUserDeck().activateDeck(name);
    }
}
