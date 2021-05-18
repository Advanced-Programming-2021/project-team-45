package model.card.SpellTrapCards.effects;

import model.game.Chain;
import model.game.ChainStartState;
import model.game.GameBoard;
import model.game.fields.Graveyard;

public class TorrentialTributeEffect extends Effect {

    @Override
    public void activate(Chain chain) {
        // destroy all monsters
        GameBoard playerGameBoard = chain.getPlayerGameBoard();
        GameBoard opponentGameBoard = chain.getOpponentGameBoard();
        playerGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
        opponentGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
        chain.deActiveMonster();

        // move selected card to graveyard
        Graveyard playerGraveyard = chain.getGame().getPlayerGameBoard().getGraveyard();
        playerGraveyard.addCardToGraveyard(chain.getGame().getSelectedCard());
        chain.getGame().deselectCard();
    }

    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_SUMMON;
    }
}
