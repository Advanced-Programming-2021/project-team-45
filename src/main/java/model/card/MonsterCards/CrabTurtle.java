package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class CrabTurtle extends MonsterCard {
    public CrabTurtle(User owner) {
        this.cardName="Crab Turtle";
        this.attack=2550;
        this.level=8;
        this.defense=2500;
        this.cardDescription="This monster can only be Ritual Summoned with the Ritual Spell Card, \"Turtle Oath\". You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.";
        this.attribute= MonsterAttribute.WATER;
        this.type="Aqua";
        this.cardType="Ritual";
        this.price=10200;
        this.owner=owner;
    }

}
