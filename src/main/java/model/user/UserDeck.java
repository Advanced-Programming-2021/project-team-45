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

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public boolean doesDeckExist(String deckName) {
        return getDeckByName(deckName) != null;
    }

    public boolean doesActiveDeckExist() {
        return activeDeck != null;
    }

    public boolean isActiveDeckValid() {
        return activeDeck.isDeckValid();
    }

    public void createDeck(String deckName, User user) {
        Deck deck = new Deck(deckName, user);
        userDecks.add(deck);
    }

    public void deleteDeckFromUserDecks(String deckName) {
        userDecks.removeIf(deck -> (deck.getName()).equals(deckName));
    }

    public void activateDeck(String deckName) {
        // ArrayList<Deck> activeDeck = new ArrayList<>();   //Hossein : ghazie in chie? cherra ye array list gozashtid
        for (Deck deck : this.userDecks) {
            if ((deck.getName()).equals(deckName)) {
                this.activeDeck = deck;
                //         activeDeck.add(deck);
            }
        }
        // (this.userDecks).remove(activeDeck.get(0));
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : userDecks) {
            if (deck.getName().equals(deckName)) return deck;
        }
        return null;
    }

    public boolean isDeckFull(String deckName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        if (isSideDeck) {
            int size = (deck.getSideDeck()).size();
            return size > 15;
        } else {
            int size = (deck.getMainDeck()).size();
            return size > 60;
        }
    }

    public boolean isDeckFullFromCard(String deckName, String cardName) {
        Deck deck = this.getDeckByName(deckName);
        return deck.isFullFromCard(cardName);
    }

    public void addCardToDeck(String deckName, String cardName, boolean isSideDeck, User user) {
        Deck deck = this.getDeckByName(deckName);
        deck.addCard(cardName, isSideDeck, user);
    }

    public boolean doesCardExistInDeck(String deckName, String cardName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        return deck.doesCardExist(cardName, isSideDeck);
    }

    public void deleteCardFromDeck(String deckName, String cardName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        deck.deleteCard(cardName, isSideDeck);
    }

    public String getActiveDeckStr() {
        if (this.activeDeck == null) return "";
        else return (this.activeDeck).toString();
    }

    public ArrayList<String> getSortedOtherDeckStr() {
        ArrayList<String> otherDecksStr = new ArrayList<>();
        for (Deck deck : this.userDecks) {
            otherDecksStr.add(deck.toString());
        }
        Collections.sort(otherDecksStr);
        return otherDecksStr;
    }

    public ArrayList<String> getMonstersDeckStr(String deckName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        return deck.getMonstersStr(isSideDeck);
    }

    public ArrayList<String> getSpellAndTrapsDeckStr(String deckName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        return deck.getSpellAndTrapStr(isSideDeck);
    }


}
