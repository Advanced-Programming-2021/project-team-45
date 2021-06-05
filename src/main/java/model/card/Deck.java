package model.card;

import model.user.User;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final String name;
    private final String username;
    private final ArrayList<Card> mainDeck = new ArrayList<>();
    private final ArrayList<Card> sideDeck = new ArrayList<>();

    public Deck(String name, String username) {
        this.name = name;
        this.username = username;
    }


    public User getUser() {
        return User.getUserByUsername(username);
    }

    public void addCard(String cardName, boolean isSideDeck, User user) {
        Card card = user.getCardInventory().getCardByCardName(cardName);
        if (isSideDeck) {
            sideDeck.add(card);
        } else {
            mainDeck.add(card);
        }
    }

    public void deleteCard(String cardName, boolean isSideDeck) {
        if (isSideDeck) {
            sideDeck.removeIf(card -> card.getCardName().equals(cardName));
        } else {
            mainDeck.removeIf(card -> card.getCardName().equals(cardName));
        }
    }

    public boolean doesCardExist(String cardName, boolean isSideDeck) {
        if (isSideDeck) {
            if (sideDeck.size() != 0) {
                for (int i = 0; i < sideDeck.size(); i++) {
                    if (sideDeck.get(i) != null) {
                        if (sideDeck.get(i).getCardName().equals(cardName)) {
                            return true;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < mainDeck.size(); i++) {
                if (mainDeck.get(i) != null) {
                    if (mainDeck.get(i).getCardName().equals(cardName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getCardCountInDeck(String cardName) {
        int count = 0;
        for (Card card : sideDeck) {
            if (card != null) {
                if (card.getCardName().equals(cardName)) count++;
            }
        }
        for (Card card : mainDeck) {
            if (card.getCardName().equals(cardName)) count++;
        }
        return count;
    }

    public boolean isFullFromCard(String cardName) {
        return getCardCountInDeck(cardName) > 2;
    }

    public boolean isDeckFull(boolean isSideDeck) {
        if (isSideDeck) {
            return sideDeck.size() >= 15;
        } else {
            return mainDeck.size() >= 60;
        }
    }

    public boolean isDeckValid() {
        return mainDeck.size() <= 60 && mainDeck.size() >= 40 && sideDeck.size() <= 15;
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

    public ArrayList<String> getMonstersStr(boolean isSideDeck) {
        ArrayList<String> monstersStr = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : sideDeck) {
                if (card instanceof MonsterCard) {
                    monstersStr.add(card.getCardName() + ": " + card.getCardDescription());
                }
            }
        } else {
            for (Card card : mainDeck) {
                if (card instanceof MonsterCard) {
                    monstersStr.add(card.getCardName() + ": " + card.getCardDescription());
                }
            }
        }
        Collections.sort(monstersStr);
        return monstersStr;
    }

    public ArrayList<String> getSpellAndTrapStr(boolean isSideDeck) {
        ArrayList<String> spellAndTrapStr = new ArrayList<>();
        if (isSideDeck) {
            for (Card card : this.sideDeck) {
                if (card instanceof SpellTrapCard) {
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
                }
            }
        } else {
            for (Card card : this.mainDeck) {
                if (card instanceof SpellTrapCard) {
                    spellAndTrapStr.add(card.getCardName() + ": " + card.getCardDescription());
                }
            }
        }
        Collections.sort(spellAndTrapStr);
        return spellAndTrapStr;
    }

    @Override
    public String toString() {
        String validity;
        if (mainDeck.size() <= 60 && sideDeck.size() <= 15) validity = "valid";
        else validity = "invalid";

        return this.name + ": main deck " + mainDeck.size() +
                ", side deck " + sideDeck.size() + ", " + validity;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof Deck)) {
            return false;
        } else {
            Deck deck = (Deck) object;
            return deck.getName().equals(this.name);
        }
    }

    @Override
    public Deck clone() {
        Deck deck = new Deck(this.getName(), this.username);
        for (Card card : mainDeck) {
            deck.addCard(card.getCardName(), false, this.getUser());
        }
        for (Card card : sideDeck) {
            deck.addCard(card.getCardName(), true, this.getUser());
        }
        return deck;
    }

    public void setDeckForAI() {
        for (int i = 0; i < 50; i++) {
            this.addCard(Card.getAllCards().get(i).cardName, false, getUser());
        }
        for (int i = 0; i < 15; i++) {
            this.addCard(Card.getAllCards().get(i + 50).cardName, true, getUser());
        }
    }
}
