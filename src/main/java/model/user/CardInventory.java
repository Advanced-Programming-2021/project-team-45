package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CardInventory {

    HashMap<String, Integer> cardsCount;
    private ArrayList<Card> cards;

    public CardInventory() {
        cardsCount = new HashMap<>();
        cards = new ArrayList<>();
    }

    public void addCardToInventory(Card card) {
        if (getCardByCardName(card.getCardName()) == null) {
            cards.add(card);
            cardsCount.put(card.getCardName(), 1);
        } else {
            int count = cardsCount.get(card.getCardName());
            cardsCount.put(card.getCardName(), count + 1);
        }
    }

    public int getCardCount(String cardName) {
        if (cardsCount.containsKey(cardName)) {
            return cardsCount.get(cardName);
        }
        return 0;
    }

    public boolean doesCardExistToAddToDeck(UserDeck userDeck, String deckName, String cardName) {
        if (getCardByCardName(cardName) == null) {
            return false;
        } else if (!userDeck.doesDeckExist(deckName)) {
            return true;
        } else {
            int usedCount = userDeck.getDeckByName(deckName).getCardCountInDeck(cardName);
            return cardsCount.get(cardName) > usedCount;
        }
    }

    public Card getCardByCardName(String cardName) {
        for (Card card : cards) {
            if (card.getCardName().equals(cardName)) {
                return Card.copy(card);
            }
        }
        return null;
    }

    public ArrayList<String> getAllCardsStr() {
        ArrayList<String> cardsStr = new ArrayList<>();
        for (Card card : cards) {
            cardsStr.add(card.getCardName() + ":" + card.getCardDescription());
        }
        Collections.sort(cardsStr);
        return cardsStr;
    }

    public void setAICardInventory() {
        ArrayList<Card> cards = Card.getAllCards();
        for (int i = 0; i < cards.size(); i++) {
            this.cards.add(Card.copy(cards.get(i)));
        }
    }
}
