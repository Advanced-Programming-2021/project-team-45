package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class FeralImp extends MonsterCard {
    public FeralImp(User owner) {
        this.cardName="Feral Imp";
        this.attack=1300;
        this.level=4;
        this.defense=1400;
        this.cardDescription="A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.";
        this.attribute= MonsterAttribute.Dark;
        this.type="Fiend";
        this.cardType="Normal";
        this.price=2800;
        this.owner=owner;
    }
}
