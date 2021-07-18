package Server.controller;

import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import Server.model.card.Deck;
import Server.model.user.User;
import Client.view.DeckMenuGui;

import java.util.ArrayList;

public class DeckStarterController extends Controller {


    public DeckStarterController(String username) {
        super(username);
    }

    public ArrayList<Deck> getAllDeck(User user){
        return user.getUserDeck().getUserDecks();
    }

    public Deck getActiveDeck(User user){
        return user.getUserDeck().getActiveDeck();
    }

    public void setActiveDeck(String name) {
        user.getUserDeck().activateDeck(name);
    }

    public ArrayList<String> getDeckNames() {
        ArrayList<String> answer=new ArrayList<>();
        for(int i=0;i<user.getUserDeck().getUserDecks().size();i++){
            answer.add(user.getUserDeck().getUserDecks().get(i).getName());
        }
        return answer;
    }

    public String getActiveDeckName() {
        if(user.getUserDeck().getActiveDeck()!=null) {
            return user.getUserDeck().getActiveDeck().getName();
        }else{
            return "";
        }
    }

    public void startDeckForEdit() {

    }

    public void createDeck(String field) {
        user.getUserDeck().createDeck(field,user);
    }
}
