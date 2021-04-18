package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class HeroOfTheEast extends MonsterCard {
    public HeroOfTheEast(User owner) {
        this.cardName="Hero of the east";
        this.attack=1100;
        this.level=3;
        this.defense=1000;
        this.cardDescription="Feel da strength ah dis sword-swinging samurai from da Far East.";
        this.attribute= MonsterAttribute.Earth;
        this.type="Warrior";
        this.cardType="Normal";
        this.price=1700;
        this.owner=owner;
    }
}
