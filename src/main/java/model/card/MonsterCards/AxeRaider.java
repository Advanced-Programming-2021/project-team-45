package model.card.MonsterCards;

import model.card.*;
import model.user.User;

public class AxeRaider extends MonsterCard {
    public AxeRaider(User owner) {
        this.cardName = "Axe Raider";
        this.attack = 1700;
        this.level = 4;
        this.defense = 1150;
        this.cardDescription = "An axe-wielding monster of tremendous strength and agility.";
        this.attribute = MonsterAttribute.Earth;
        this.type = "Warrior";
        this.cardType = "Normal";
        this.price = 3100;
        this.owner=owner;
    }
}
