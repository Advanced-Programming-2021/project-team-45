package model.card.MonsterCards;

import model.card.MonsterAttribute;
import model.card.MonsterCard;
import model.user.User;

public class CrawlingDragon extends MonsterCard {
    public CrawlingDragon(User owner) {
        this.cardName="Crawling dragon";
        this.attack=1600;
        this.level=5;
        this.defense=1400;
        this.cardDescription="This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.";
        this.attribute= MonsterAttribute.Earth;
        this.type="Dragon";
        this.cardType="Normal";
        this.price=3900;
        this.owner=owner;
    }
}
