package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;

public class HarpieFeatherDuster {
    public static void ability(Game game){
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        opponentGameBoard.getSpellTrapField().deleteAndDestroyAllSpellTrapCards();
    }
}
