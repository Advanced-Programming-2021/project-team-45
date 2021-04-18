package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class babyDragon extends MonsterCard {
    public babyDragon(User owner) {
        this.cardName="Baby dragon";
        this.attack=1200;
        this.level=3;
        this.defense=700;
        this.cardDescription="Much more than just a child, this dragon is gifted with untapped power.";
        this.attribute= MonsterAttribute.WIND;
        this.type="Dragon";
        this.cardType="Normal";
        this.price=1600;
        this.owner=owner;
    }
}
