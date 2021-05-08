package model.game.fields;

import model.card.Card;
import model.card.Deck;
import model.card.SpellTrapCard;
import model.user.User;

import java.util.ArrayList;
import java.util.Random;

public class DeckField {

    private final User owner;
    private final Deck deck;
    private final ArrayList<Card> mainDeck;

    public DeckField(User user){
        owner = user;
        deck = user.getUserDeck().getActiveDeck().clone();
        mainDeck = deck.getMainDeck();
    }

    public Card drawCard(){
        // generate random card
        Random random = new Random();
        int i = random.nextInt(mainDeck.size());
        Card card = mainDeck.get(i);
        mainDeck.remove(i);
        return card;
    }

    public Card getFieldCard() {
        for (int i = 0; i < mainDeck.size(); i++) {
            Card card = mainDeck.get(i);
            if (card instanceof SpellTrapCard) {
                if (((SpellTrapCard) card).getIcon().equals("Field")) {
                    mainDeck.remove(i);
                    return card;
                }
            }
        }
        return null;
    }

    public Deck getDeck() {
        return deck;
    }
}