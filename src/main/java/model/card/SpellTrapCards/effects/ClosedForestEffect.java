package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.card.MonsterType;
import model.game.Chain;

import java.util.ArrayList;

public class ClosedForestEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        ArrayList<Card> graveyard = chain.getPlayerGameBoard().getGraveyard().getGraveyardCards();
        int numberOfMonstersInGraveyard = 0;
        for(Card card : graveyard) {
            if(card instanceof MonsterCard) numberOfMonstersInGraveyard++;
        }
        ArrayList<MonsterCard> monsters = chain.getPlayerGameBoard().getMonsterField().getMonstersOnField();
        for (MonsterCard monster : monsters) {
            if (monster.getType() == MonsterType.Beast || monster.getType() == MonsterType.Beast_Warrior) {
                monster.increaseAttack(numberOfMonstersInGraveyard * 100);
            }
        }
    }
}