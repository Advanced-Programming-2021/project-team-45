package model.card;

import model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private String name;
    private User user;
    private ArrayList<Card> mainDeckCards = new ArrayList<>();
    private ArrayList<Card> sideDeckCards = new ArrayList<>();
    private ArrayList<Card> allCards;

    public Deck(String name, User user) {
        this.name = name;
        this.user = user;
        this.mainDeckCards = new ArrayList<>();
        this.sideDeckCards = new ArrayList<>();
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

    public boolean isFullFromCard(String cardName) {
        int numberOfCard = 0;
        for (Card card : this.mainDeckCards) {
            if ((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        for (Card card : this.sideDeckCards) {
            if ((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        if(numberOfCard == 3) return true;else return false;
    }

    public void addCard(String cardName, boolean isSideDeck, User user) {
        Card card = (user.getCardInventory()).getCardByCardName(cardName);
        if (isSideDeck) {
            (this.sideDeckCards).add(card);
            (user.getCardInventory()).deleteCardFromCardInventory(card);
        } else {
            this.mainDeckCards.add(card);
        }
    }

    public Card getCard() {
        Card card = (this.mainDeckCards).get(0);
        (this.mainDeckCards).remove(card);
        return card;
    }

    public Card getAFieldCard() {
        ArrayList<Card> targetCard = new ArrayList<>();
        for(Card card : this.mainDeckCards){
            if(card instanceof SpellTrapCard){
                if(((SpellTrapCard) card).getIcon().equals("Field")){
                    targetCard.add(card);
                    break;
                }
            }
        }
        this.mainDeckCards.remove(targetCard.get(0));
        return targetCard.get(0);
    }

    public Card getRandomCard() {
        Random random = new Random();
        int index = random.nextInt((this.mainDeckCards).size());
        Card card = (this.mainDeckCards).get(index);
        (this.mainDeckCards).remove(card);
        return card;
    }

    public boolean doesCardExist(String cardName, boolean isSideDeck) {
        int existence = 0;
        if (isSideDeck) {
            for (Card card : this.sideDeckCards) {
                if ((card.getCardName()).equals(cardName))
                    existence++;
            }
        } else {
            for (Card card : this.mainDeckCards) {
                if ((card.getCardName()).equals(cardName))
                    existence++;
            }
        }
        if (existence == 0) return false;
        else return true;
    }

    public boolean isDeckValid() {
        if ((this.mainDeckCards).size() > 60 || (this.mainDeckCards).size() < 40 || (this.sideDeckCards).size() > 15)
            return false;
        else return true;
    }

    public void deleteCard(String cardName, boolean isSideDeck) {
        ArrayList<Card> targetCard = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeckCards) {
                if ((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        } else {
            for (Card card : this.mainDeckCards) {
                if ((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        }
        if (isSideDeck) (this.sideDeckCards).remove(targetCard.get(0));
        else (this.mainDeckCards).remove(targetCard.get(0));
        ((this.user).getCardInventory()).addCardToCardInventory(targetCard.get(0));
    }

    @Override
    public String toString() {
        String validity;
        if ((this.mainDeckCards).size() < 60 && (this.sideDeckCards).size() < 15) validity = "valid";
        else validity = "invalid";

        return this.name + ": main deck " + (this.mainDeckCards).size() +
                ", side deck " + (this.sideDeckCards).size() + ", " + validity;
    }

    public ArrayList<String> getMonstersStr(boolean isSideDeck) {
        ArrayList<String> monstersStr = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeckCards) {
                if (card instanceof MonsterCard)
                    monstersStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        } else {
            for (Card card : this.mainDeckCards) {
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
            for (Card card : this.sideDeckCards) {
                if (card instanceof SpellTrapCard)
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        } else {
            for (Card card : this.mainDeckCards) {
                if (card instanceof SpellTrapCard)
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
            }
        }
        Collections.sort(spellAndTrapStr);
        return spellAndTrapStr;
    }
}
