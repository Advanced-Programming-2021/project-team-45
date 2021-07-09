package Server.model.card.SpellTrapCards.effects;


import Server.model.game.Chain;
import Server.model.game.ChainStartState;
import Server.model.game.GameBoard;
import Server.model.card.MonsterCard;
import Server.model.card.PositionMonsters;

import java.util.ArrayList;


public class MirrorForceEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        //destroy opponent monster on attack position
        GameBoard opponentGameBoard = chain.getOpponentGameBoard();
        ArrayList<MonsterCard> monstersOnField = opponentGameBoard.getMonsterField().getMonstersOnField();
        for (MonsterCard monsterCard : monstersOnField) {
            if (monsterCard.getPosition() == PositionMonsters.ATTACK) {
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
            }
        }
        chain.deActiveMonster();

        // deselect card
        chain.getGame().deselectCard();
    }

    @Override
    public boolean canActivate(Chain chain) {
        // check if opponent has attacked:
        return chain.getOpponent() == chain.getGame().getPlayerOfThisTurn() &&
                chain.getChainStartState() == ChainStartState.MONSTER_ATTACK;
    }
}