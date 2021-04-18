package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class Fireyarou extends MonsterCard {
    public Fireyarou(User owner) {
        this.cardName="Fireyarou";
        this.attack=1300;
        this.level=4;
        this.defense=1000;
        this.cardDescription="A malevolent creature wrapped in flames that attacks enemies with intense fire.";
        this.attribute= MonsterAttribute.Fire;
        this.type="Pyro";
        this.cardType="Normal";
        this.price=2500;
        this.owner=owner;
    }
}
