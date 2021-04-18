package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class CurtainOfTheDarkOnes extends MonsterCard {
    public CurtainOfTheDarkOnes(User owner) {
        this.cardName="Curtain of the dark ones";
        this.attack=600;
        this.level=2;
        this.defense=500;
        this.cardDescription="A curtain that a spellcaster made, it is said to raise a dark power.";
        this.attribute= MonsterAttribute.Dark;
        this.type="Spellcaster";
        this.cardType="Normal";
        this.price=700;
        this.owner=owner;
    }
}
