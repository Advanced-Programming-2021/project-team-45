package model.card.MonsterCards;

import model.card.*;
import model.user.User;

public class BattleOx extends MonsterCard {
    public BattleOx(User owner) {
        this.cardName="Battle OX";
        this.attack=1700;
        this.level=4;
        this.defense=1000;
        this.cardDescription="A monster with tremendous power, it destroys enemies with a swing of its axe.";
        this.attribute=MonsterAttribute.Earth;
        this.type="Beast-Warrior";
        this.cardType="Normal";
        this.price=2200;
        this.owner=owner;
    }
}
