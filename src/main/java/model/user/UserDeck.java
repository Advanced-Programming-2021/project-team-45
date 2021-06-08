package model.user;

import model.card.Card;
import model.card.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class UserDeck {

    private final ArrayList<Deck> userDecks;
    private Deck activeDeck;

    public UserDeck() {
        this.userDecks = new ArrayList<>();
    }


    public void initializeDecks() {
        for (Deck deck : userDecks) {
            deck.initializeDeck();
        }
        activeDeck.initializeDeck();
    }

    public void createDeck(String deckName, User user) {
        Deck deck = new Deck(deckName, user.getUsername());
        userDecks.add(deck);
    }

    public void deleteDeckFromUserDecks(String deckName) {
        userDecks.removeIf(deck -> (deck.getName()).equals(deckName));
    }

    public void activateDeck(String deckName) {
        for (Deck deck : userDecks) {
            if (deck.getName().equals(deckName)) {
                this.activeDeck = deck;
            }
        }
    }

    public boolean doesActiveDeckExist() {
        return activeDeck != null;
    }

    public boolean doesDeckExist(String deckName) {
        return getDeckByName(deckName) != null;
    }

    public boolean isActiveDeckValid() {
        return activeDeck.isDeckValid();
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : userDecks) {
            if (deck.getName().equals(deckName)) return deck;
        }
        return null;
    }

    public String getActiveDeckStr() {
        if (activeDeck == null) {
            return "";
        } else {
            return activeDeck.toString();
        }
    }

    public ArrayList<String> getSortedOtherDeckStr() {
        ArrayList<String> otherDecksStr = new ArrayList<>();
        for (Deck deck : userDecks) {
            if (!deck.equals(activeDeck))
                otherDecksStr.add(deck.toString());
        }
        Collections.sort(otherDecksStr);
        return otherDecksStr;
    }
}