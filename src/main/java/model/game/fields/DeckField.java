package model.game.fields;

import model.card.*;
import model.user.User;

import java.util.ArrayList;
import java.util.Random;

public class DeckField extends CardField {

    private final User owner;
    private final Deck deck;
    private final ArrayList<Card> mainDeck;

    public DeckField(User user) {
        super("Deck");
        owner = user;
        deck = user.getUserDeck().getActiveDeck();
        mainDeck = deck.getMainDeck();
    }


    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : mainDeck) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public Card drawCard() {
        // generate random card
        Random random = new Random();
        int i = random.nextInt(mainDeck.size());
        Card card = mainDeck.get(i);
        if (card instanceof MonsterCard) {
            MonsterCard monsterCard = (MonsterCard) card;
            monsterCard.setPosition(PositionMonsters.ATTACK);
            card = monsterCard;
        } else {
            SpellTrapCard spellTrapCard = (SpellTrapCard) card;
            spellTrapCard.setPosition(SpellsAndTrapPosition.SET);
            card = spellTrapCard;
        }
        mainDeck.remove(i);
        return card;
    }

    public Card getFieldSpell() {
        for (int i = 0; i < mainDeck.size(); i++) {
            Card card = mainDeck.get(i);
            if (card instanceof SpellTrapCard) {
                if (((SpellTrapCard) card).getIcon() == SpellAndTrapIcon.FIELD) {
                    mainDeck.remove(i);
                    return card;
                }
            }
        }
        return null;
    }

    public boolean doesFieldSpellExist() {
        return getFieldSpell() != null;
    }

    public Deck getDeck() {
        return deck;
    }
}