package model.user;

import model.card.Card;
import model.card.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class UserDeck {
    private ArrayList<Deck> userDecks;
    private Deck activeDeck;

    public UserDeck() {
        this.userDecks = new ArrayList<Deck>();
    }

    public ArrayList getUserDecks() {
        return userDecks;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public boolean doesDeckExist(String deckName) {
        if (this.getDeckByName(deckName) == null) return false;
        else return true;
    }

    public boolean doesExistActiveDeck() {
        return this.activeDeck != null;
    }

    public boolean isActiveDeckValid() {
        return this.activeDeck.isDeckValid();
    }

    public void createDeck(String deckName, User user) {
        Deck deck = new Deck(deckName, user);
        (this.userDecks).add(deck);
    }

    public void deleteDeckFromUserDecks(String deckName, User user) {
        ArrayList<Deck> toDelete = new ArrayList<>();
        for (Deck deck : this.userDecks) {
            if ((deck.getName()).equals(deckName))
                toDelete.add(deck);
        }
        Deck toDeleteDeck = toDelete.get(0);
        if ((toDeleteDeck.getMainDeckCards()).size() != 0) {
            for (Card card : toDeleteDeck.getMainDeckCards()) {
                (user.getCardInventory()).addCardToCardInventory(card);
            }
        }
        if ((toDeleteDeck.getSideDeckCards()).size() != 0) {
            for (Card card : toDeleteDeck.getSideDeckCards()) {
                (user.getCardInventory()).addCardToCardInventory(card);
            }
        }
        (this.userDecks).remove(toDeleteDeck);
    }

    public void activateDeck(String deckName) {
        ArrayList<Deck> activeDeck = new ArrayList<>();
        for (Deck deck : this.userDecks) {
            if ((deck.getName()).equals(deckName)) {
                this.activeDeck = deck;
                activeDeck.add(deck);
            }
        }
        (this.userDecks).remove(activeDeck.get(0));
    }

    private Deck getDeckByName(String deckName) {
        ArrayList<Deck> selections = new ArrayList<>();
        for (Deck deck : this.userDecks) {
            if ((deck.getName()).equals(deckName))
                selections.add(deck);
        }
        if (selections.size() == 0) return null;
        else return selections.get(0);
    }

    public boolean isDeckFull(String deckName, boolean isSideDeck) {
        Deck deck = this.getDeckByName(deckName);
        if (isSideDeck) {
            int size = (deck.getSideDeckCards()).size();
            return size > 15;
        } else {
            int size = (deck.getMainDeckCards()).size();
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
