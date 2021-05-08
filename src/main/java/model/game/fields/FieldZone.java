package model.game.fields;

import model.card.Card;

public class FieldZone extends CardField {

    private final Graveyard graveyard;
    private Card fieldCard;

    public FieldZone(Graveyard graveyard) {
        this.graveyard = graveyard;
    }


    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        if (fieldCard.getCardName().equals(cardName)) {
            return fieldCard;
        }
        return null;
    }

    public void activateFieldCard() {

    }

    public Card getFieldCard() {
        return fieldCard;
    }

    public void setFieldCard(Card fieldCard) {
        graveyard.addCardToGraveyard(this.fieldCard);
        this.fieldCard = fieldCard;
    }

    public boolean isFull() {
        return this.fieldCard != null;
    }
}
