package model.card;

import model.user.User;

import java.util.ArrayList;

public class Deck {
    private String name;
    private User user;
    private ArrayList<Card> mainDeckCards;
    private ArrayList<Card> sideDeckCards;
    private ArrayList<Card> allCards;

    public Deck(String name,User user){
        this.name = name;
        this.mainDeckCards = new ArrayList<>();
        this.sideDeckCards = new ArrayList<>();
        (user.getUserDeck()).addDeckToUserDecks(this);
        //this.allCards ??
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getMainDeckCards() {
        return mainDeckCards;
    }

    public ArrayList<Card> getSideDeckCards() {
        return sideDeckCards;
    }
}
