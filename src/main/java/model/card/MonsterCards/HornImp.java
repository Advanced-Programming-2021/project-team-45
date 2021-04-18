package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class HornImp extends MonsterCard {
    public HornImp(User owner) {
        this.cardName="Horn Imp";
        this.attack=1300;
        this.level=4;
        this.defense=1000;
        this.cardDescription="A small fiend that dwells in the dark, its single horn makes it a formidable opponent.";
        this.attribute= MonsterAttribute.Dark;
        this.type="Fiend";
        this.cardType="Normal";
        this.price=2500;
        this.owner=owner;
    }
}
