package model.card.SpellTrapCards.effects;


import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.RingOfDefense;
import model.game.Chain;
import model.game.ChainStartState;
import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.game.fields.Graveyard;
import model.game.fields.MonsterField;


public class MirrorForce extends Effect {


    @Override
    public void activate(Chain chain) {
        //destroy opponent monster on attack position
        GameBoard opponentGameBoard = chain.getOpponentGameBoard();
        for (int i = 0; i < opponentGameBoard.getMonsterField().getMonstersOnField().size(); i++) {
            if (opponentGameBoard.getMonsterField().getMonstersOnField().get(i).getPosition()
                    .equals(PositionMonsters.ATTACK)) {
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(opponentGameBoard.
                        getMonsterField().getMonstersOnField().get(i));
            }
        }
        chain.deActiveMonster();

        //move selected card to graveyard
        Graveyard playerGraveyard = chain.getGame().getPlayerGameBoard().getGraveyard();
        playerGraveyard.addCardToGraveyard(chain.getGame().getSelectedCard());
        chain.getGame().deselectCard();
    }


    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_ATTACK;
    }

}