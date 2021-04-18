package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class BattleWarrior extends MonsterCard {
    public BattleWarrior(User owner) {
        this.cardName="Battle warrior";
        this.attack=700;
        this.level=3;
        this.defense=1000;
        this.cardDescription="A warrior that fights with his bare hands!!!";
        this.attribute= MonsterAttribute.Earth;
        this.type="Warrior";
        this.cardType="Normal";
        this.price=1300;
        this.owner=owner;
    }
}
