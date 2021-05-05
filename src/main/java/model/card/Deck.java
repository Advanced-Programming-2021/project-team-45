package model.card;

import model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private final String name;
    private final User user;
    private final ArrayList<Card> mainDeck;
    private final ArrayList<Card> sideDeck;

    public Deck(String name, User user) {
        this.name = name;
        this.user = user;
        this.mainDeck = new ArrayList<>();
        this.sideDeck = new ArrayList<>();
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
            for (Card card : sideDeck) {
                if (card.getCardName().equals(cardName)) {
                    return true;
                }
            }
        } else {
            for (Card card : mainDeck) {
                if (card.getCardName().equals(cardName)) {
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


    //////////////////////////////////////// DA FUK? //////////////////////////

    public Card getCard() {
        Card card = mainDeck.get(0);
        mainDeck.remove(card);
        return card;
    }

    public Card getAFieldCard() {
        ArrayList<Card> targetCard = new ArrayList<>();
        for (Card card : mainDeck) {
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

    //////////////////////////////////////////////////////////////////////////

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
}
