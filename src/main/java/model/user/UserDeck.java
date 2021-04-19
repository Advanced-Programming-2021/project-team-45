package model.user;

import model.card.Card;
import model.card.Deck;

import java.util.ArrayList;

public class UserDeck {
    private ArrayList<Deck> userDecks;
    private Deck activeDeck;

    public UserDeck(){
        this.userDecks = new ArrayList<Deck>();
    }

    public ArrayList getUserDecks() {
        return userDecks;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public void addDeckToUserDecks(Deck deck){
        (this.userDecks).add(deck);
    }

    public void deleteDeckFromUserDecks(String deckName){
        ArrayList<Deck>toDelete = new ArrayList<>();
        for(Deck deck : this.userDecks){
            if((deck.getName()).equals(deckName))
                toDelete.add(deck);
        }
        for(Deck deck : toDelete) (this.userDecks).remove(deck);
    }

    public void activateDeck(String deckName){
        for(Deck deck : this.userDecks){
            if((deck.getName()).equals(deckName))
                this.activeDeck = deck;
        }
    }

    public Deck getDeckByName(String deckName){
        ArrayList<Deck> selections = new ArrayList<>();
        for(Deck deck : this.userDecks){
            if((deck.getName()).equals(deckName))
                selections.add(deck);
        }
        if(selections.size() == 0) return null;
        else return selections.get(0);
    }

    private void isMainDeckValid(Deck deck){

    }

    private void isSlideDeckValid(Deck deck){

    }

    private void isDeckFullFromXCard(Card card){

    }

    private void canUseSlideDeck(Deck deck){

    }

    public void changeActiveDeck(Deck deck){

    }

}
