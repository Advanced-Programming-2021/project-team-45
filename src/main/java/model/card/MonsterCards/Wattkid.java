package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class Wattkid extends MonsterCard {
    public Wattkid(User owner) {
        this.cardName="Wattkid";
        this.attack=1000;
        this.level=3;
        this.defense=500;
        this.cardDescription="A creature that electrocutes opponents with bolts of lightning.";
        this.attribute= MonsterAttribute.Light;
        this.type="Thunder";
        this.cardType="Normal";
        this.price=1300;
        this.owner=owner;
    }
}
