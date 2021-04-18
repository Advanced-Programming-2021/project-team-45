package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class FlameManipulator extends MonsterCard {
    public FlameManipulator(User owner) {
        this.cardName="Flame manipulator";
        this.attack=900;
        this.level=3;
        this.defense=1000;
        this.cardDescription="This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".";
        this.attribute= MonsterAttribute.Fire;
        this.type="Spellcaster";
        this.cardType="Normal";
        this.price=1500;
        this.owner=owner;
    }
}
