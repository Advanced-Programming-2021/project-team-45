package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class BlueEyesWhiteDragon extends MonsterCard {
    public BlueEyesWhiteDragon(User owner) {
        this.cardName="Blue-Eyes white dragon";
        this.attack=3000;
        this.level=8;
        this.defense=2500;
        this.cardDescription="This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.";
        this.attribute= MonsterAttribute.Light;
        this.type="Dragon";
        this.cardType="Normal";
        this.price=11300;
        this.owner=owner;
    }
}
