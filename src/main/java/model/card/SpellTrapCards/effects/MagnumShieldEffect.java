package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.card.MonsterType;
import model.card.PositionMonsters;
import model.game.Chain;
import model.game.fields.MonsterField;

import java.util.ArrayList;

public class MagnumShieldEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        MonsterField playerMonsterField = chain.getPlayerGameBoard().getMonsterField();
        ArrayList<Card> input = chain.getGame().getGameController().getCardFromPlayer(1, playerMonsterField);
        MonsterCard monster = (MonsterCard) input.get(0);

        if (monster.getType() == MonsterType.Warrior) {
            if (monster.getPosition() == PositionMonsters.ATTACK) {
                monster.increaseAttack(monster.getDefense());
            } else {
                monster.increaseDefense(monster.getAttack());
            }
        }
    }
}