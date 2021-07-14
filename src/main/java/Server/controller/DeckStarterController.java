package Server.controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Server.model.card.Deck;
import Server.model.user.User;
import Client.view.DeckMenuGui;

import java.util.ArrayList;

public class DeckStarterController extends Controller {


    public DeckStarterController(String username) {
        super(username);
    }


//    public void initialize(ChoiceBox<String> chooseActiveDeck, ChoiceBox<String> chooseEditDeck) {
//        User user = User.getUserByUsername(username);
//        ArrayList<Deck> allDecks = user.getUserDeck().getUserDecks();
//        for (Deck allDeck : allDecks) {
//            chooseActiveDeck.getItems().add(allDeck.getName());
//            chooseEditDeck.getItems().add(allDeck.getName());
//        }
//        Deck deck=user.getUserDeck().getActiveDeck();
//        if(deck!=null) {
//            chooseActiveDeck.setValue(deck.getName());
//        }
//    }

    public void initializeFromMenu(ChoiceBox<String> chooseActiveDeck, ChoiceBox<String> chooseEditDeck,
                                   ArrayList<Deck> allDecks,Deck activeDeck){
        for (Deck allDeck : allDecks) {
            chooseActiveDeck.getItems().add(allDeck.getName());
            chooseEditDeck.getItems().add(allDeck.getName());
        }
        if(activeDeck!=null){
            chooseActiveDeck.setValue(activeDeck.getName());
        }
    }

    public ArrayList<Deck> getAllDeck(User user){
        return user.getUserDeck().getUserDecks();
    }

    public Deck getActiveDeck(User user){
        return user.getUserDeck().getActiveDeck();
    }

//    public void startEditDeck(Stage stage, String name) {
//        User user = User.getUserByUsername(username);
//        DeckMenuGui deckMenuGui = new DeckMenuGui();
//        DeckMenuGui.setUsername(username);
//        DeckController deckController = new DeckController(username);
//        DeckMenuGui.setDeckController(deckController);
//        deckController.setDeck(user.getUserDeck().getDeckByName(name));
//        try {
//            deckMenuGui.start(stage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void startEditDeck(Stage stage,User user,String name){
        DeckMenuGui deckMenuGui = new DeckMenuGui();
        DeckMenuGui.setUsername(user.getUsername());
        DeckController deckController = new DeckController(user.getUsername());
        DeckMenuGui.setDeckController(deckController);
        deckController.setDeck(user.getUserDeck().getDeckByName(name));
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void startCreateADeck(Stage stage, TextField textField) {
//        User user = User.getUserByUsername(username);
//        DeckMenuGui deckMenuGui = new DeckMenuGui();
//        DeckMenuGui.setUsername(username);
//        DeckController deckController = new DeckController(username);
//        DeckMenuGui.setDeckController(deckController);
//        user.getUserDeck().createDeck(textField.getText(), user);
//        Deck deck = user.getUserDeck().getDeckByName(textField.getText());
//        deckController.setDeck(deck);
//        try {
//            deckMenuGui.start(stage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void startCreateADeck(Stage stage,User user,Deck deck) {
        DeckMenuGui deckMenuGui = new DeckMenuGui();
        DeckMenuGui.setUsername(user.getUsername());
        DeckController deckController = new DeckController(user.getUsername());
        DeckMenuGui.setDeckController(deckController);
        deckController.setDeck(deck);
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Deck createNewDeckAndGetIt(String name,User user){
        user.getUserDeck().createDeck(name,user);
        return user.getUserDeck().getDeckByName(name);
    }


    public void setActiveDeck(String name) {
        user.getUserDeck().activateDeck(name);
    }
}
