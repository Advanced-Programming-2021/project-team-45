package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.game.Game;
import model.card.Card;
import model.card.MonsterCard;

public class MagicCylinder {
    public static void ability(Card invaderCard, Game game){
        MonsterCard monsterCard = (MonsterCard) invaderCard;
        invaderCard.getOwner().getLifepoint().decreaseLifepoint(monsterCard.getAttack());
    }
}