package model.card.SpellTrapCards.effects;

import controller.GameController;
import model.card.Card;
import model.card.MonsterCard;
import model.card.MonsterType;
import model.game.Chain;
import model.game.fields.MonsterField;

import java.util.ArrayList;

public class IncreaseAtkDefOfEquippedEffect extends Effect {

    private final int attack;
    private final int defence;
    private final MonsterType[] monsterTypes;

    public IncreaseAtkDefOfEquippedEffect(int attack, int defence, MonsterType... monsterTypes) {
        this.attack = attack;
        this.defence = defence;
        this.monsterTypes = monsterTypes;
    }


    @Override
    public void activate(Chain chain) {
        GameController gameController = chain.getGame().getGameController();
        MonsterField playerField = chain.getPlayerGameBoard().getMonsterField();

        ArrayList<Card> input = gameController.getCardFromPlayer(1, playerField);
        if (input != null) {

            MonsterCard monster = (MonsterCard) input.get(0);
            for (MonsterType type : monsterTypes) {
                if (monster.getType() == type) {
                    monster.increaseAttack(attack);
                    monster.increaseDefense(defence);
                }
            }
        }
    }
}