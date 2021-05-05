package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;

public class HarpieFeatherDuster {
    public static void ability(Game game){
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        opponentGameBoard.getSpellTrapField().deleteAndDestroyAllSpellTrapCards();
    }
}
