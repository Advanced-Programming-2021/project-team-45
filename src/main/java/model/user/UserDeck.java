package model.user;

import model.card.Card;

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
