package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class SilverFang extends MonsterCard {
    public SilverFang(User owner) {
        this.cardName = "Silver Fang";
        this.attack = 1200;
        this.level = 3;
        this.defense = 800;
        this.cardDescription = "A snow wolf that's beautiful to the eye, but absolutely vicious in battle.";
        this.attribute = MonsterAttribute.Earth;
        this.type = "Beast";
        this.cardType = "Normal";
        this.price = 1700;
        this.owner = owner;
    }
}
