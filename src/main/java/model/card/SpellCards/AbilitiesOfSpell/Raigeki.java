package model.card.SpellCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;

public class Raigeki {
    public static void ability(Game game) {
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        opponentGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
    }
}
