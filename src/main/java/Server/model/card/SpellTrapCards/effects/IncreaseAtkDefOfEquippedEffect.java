package Server.model.card.SpellTrapCards.effects;

import Server.controller.GameController;
import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.card.MonsterType;
import Server.model.game.Chain;
import Server.model.game.fields.MonsterField;

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