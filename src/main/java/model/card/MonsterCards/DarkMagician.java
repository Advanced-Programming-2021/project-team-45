package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class DarkMagician extends MonsterCard {
    public DarkMagician(User owner) {
        this.cardName="Dark Magician";
        this.attack=2500;
        this.level=7;
        this.defense=2100;
        this.cardDescription="The ultimate wizard in terms of attack and defense.";
        this.attribute= MonsterAttribute.Dark;
        this.type="Spellcaster";
        this.cardType="Normal";
        this.price=8300;
        this.owner=owner;
    }
}
