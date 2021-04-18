package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class YomiShip  extends MonsterCard {
    public YomiShip(User owner) {
        this.cardName = "Yomi Ship";
        this.attack = 800;
        this.level = 3;
        this.defense = 1400;
        this.cardDescription = "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
        this.attribute = MonsterAttribute.WATER;
        this.type = "Aqua";
        this.cardType = "Effect";
        this.price = 1700;
        this.owner = owner;
    }
    public void initializePower(MonsterCard card){
        card.setAttack(-1);
        card.setDefense(-1);
    }
}
