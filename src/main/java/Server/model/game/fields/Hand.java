package Server.model.game.fields;

import Server.model.card.Card;
import Server.model.user.User;

import java.util.ArrayList;

public class Hand extends CardField {

    private final ArrayList<Card> hand;
    private User handOwner;

    public Hand() {
        super("Hand");
        hand = new ArrayList<>();
    }


    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : hand) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public void setHandAtFirst(DeckField deckField) {
        for (int i = 0; i < 4; i++) {
            this.addCard(deckField.drawCard());
        }
    }

    public ArrayList<Card> getCardsInHand() {
        return hand;
    }

    public Card getCardFromHand(int handIndex) {
        return hand.get(handIndex);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void deleteCard(Card card) {
        hand.remove(card);
    }

    public boolean doesCardExistInThesePlace(int handIndex) {
        if (handIndex < hand.size()) {
            return hand.get(handIndex) != null;
        } else
            return false;
    }

    public void deleteCardWithNumberOfIt(int num) {
        this.hand.remove(num);
    }
}
