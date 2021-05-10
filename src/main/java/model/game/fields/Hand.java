package model.game.fields;

import model.card.Card;
import model.user.User;

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

    public ArrayList<Card> getCardsInHand() {
        return hand;
    }

    public Card getCardFromHand(int handIndex) {
        return hand.get(handIndex - 1);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void deleteCard(Card card) {
        hand.remove(card);
    }

    public boolean doesCardExistInThesePlace(int handIndex) {
        return hand.get(handIndex - 1) != null;
    }

    public void deleteCardWithNumberOfIt(int num) {
        this.hand.remove(num);
    }
}
