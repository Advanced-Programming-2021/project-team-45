package model.Game;

import model.card.Card;

public class FieldZone {
    private Graveyard graveyard;
    private Card fieldCard;

    public FieldZone(Graveyard graveyard){
        this.graveyard = graveyard;
    }
    public void activateFieldCard(){

    }

    public Card getFieldCard() {
        return fieldCard;
    }

    public void setFieldCard(Card fieldCard) {
        (this.graveyard).addCardToGraveyard(this.fieldCard);
        this.fieldCard = fieldCard;
    }

    public boolean isFull(){
        return this.fieldCard != null;
    }
}
