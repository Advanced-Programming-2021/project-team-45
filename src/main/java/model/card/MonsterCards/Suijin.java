package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class Suijin extends MonsterCard {
    public Suijin(User owner) {
        this.cardName = "Suijin";
        this.attack = 2500;
        this.level = 7;
        this.defense = 2400;
        this.cardDescription = "During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.";
        this.attribute = MonsterAttribute.WATER;
        this.type = "Aqua";
        this.cardType = "Effect";
        this.price = 8700;
        this.owner = owner;
    }


    public void initializePower(MonsterCard card){
        /*
            it depend to game class methods
         */
    }
}
