package model.card;

import model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private String name;
    private User user;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> sideDeck;
    private ArrayList<Card> allCards;

    public Deck(String name, User user) {
        this.name = name;
        this.user = user;
        this.mainDeck = new ArrayList<>();
        this.sideDeck = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getMainDeck() {
        return mainDeck;
    }

    public ArrayList<Card> getSideDeck() {
        return sideDeck;
    }

    public boolean isFullFromCard(String cardName) {
        int numberOfCard = 0;
        for (Card card : this.mainDeck) {
            if ((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        for (Card card : this.sideDeck) {
            if ((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        return numberOfCard > 2;
    }

    public void addCard(String cardName, boolean isSideDeck, User user) {
        Card card = user.getCardInventory().getCardByCardName(cardName);
        if (isSideDeck) {
            sideDeck.add(card);
        } else {
            mainDeck.add(card);
        }
    }

    public Card getCard() {
        Card card = (this.mainDeck).get(0);
        (this.mainDeck).remove(card);
        return card;
    }

    public Card getAFieldCard() {
        ArrayList<Card> targetCard = new ArrayList<>();
        for (Card card : this.mainDeck) {
            if (card instanceof SpellTrapCard) {
                if (((SpellTrapCard) card).getIcon().equals("Field")) {
                    targetCard.add(card);
                    break;
                }
            }
        }
        this.mainDeck.remove(targetCard.get(0));
        return targetCard.get(0);
    }

    public Card getRandomCard() {
        Random random = new Random();
        int index = random.nextInt((this.mainDeck).size());
        Card card = (this.mainDeck).get(index);
        (this.mainDeck).remove(card);
        return card;
    }

    public boolean doesCardExist(String cardName, boolean isSideDeck) {
        if (isSideDeck) {
            for (Card card : this.sideDeck) {
                if ((card.getCardName()).equals(cardName)) {
                    return true;
                }
            }
        } else {
            for (Card card : this.mainDeck) {
                if ((card.getCardName()).equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCardCountInDeck(String cardName) {
        int count = 0;
        for (Card card : sideDeck) {
            if (card.getCardName().equals(cardName)) count++;
        }
        for (Card card : mainDeck) {
            if (card.getCardName().equals(cardName)) count++;
        }
        return count;
    }

    public boolean isDeckValid() {
        return (this.mainDeck).size() <= 60 && (this.mainDeck).size() >= 40 && (this.sideDeck).size() <= 15;
    }

    public void deleteCard(String cardName, boolean isSideDeck) {
        ArrayList<Card> targetCard = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeck) {
                if ((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        } else {
            for (Card card : this.mainDeck) {
                if ((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        }
        if (isSideDeck) (this.sideDeck).remove(targetCard.get(0));
        else (this.mainDeck).remove(targetCard.get(0));
        ((this.user).getCardInventory()).addCardToCardInventory(targetCard.get(0));
    }

    @Override
    public String toString() {
        String validity;
        if ((this.mainDeck).size() < 60 && (this.sideDeck).size() < 15) validity = "valid";
        else validity = "invalid";

        return this.name + ": main deck " + (this.mainDeck).size() +
                ", side deck " + (this.sideDeck).size() + ", " + validity;
    }

    public ArrayList<String> getMonstersStr(boolean isSideDeck) {
        ArrayList<String> monstersStr = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeck) {
                if (card instanceof MonsterCard)
                    monstersStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        } else {
            for (Card card : this.mainDeck) {
                if (card instanceof MonsterCard)
                    monstersStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        }
        Collections.sort(monstersStr);
        return monstersStr;
    }

    public ArrayList<String> getSpellAndTrapStr(boolean isSideDeck) {
        ArrayList<String> spellAndTrapStr = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeck) {
                if (card instanceof SpellTrapCard)
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        } else {
            for (Card card : this.mainDeck) {
                if (card instanceof SpellTrapCard)
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        }
        Collections.sort(spellAndTrapStr);
        return spellAndTrapStr;
    }
}
