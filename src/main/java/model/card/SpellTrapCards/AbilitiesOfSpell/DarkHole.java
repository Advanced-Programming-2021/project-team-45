package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;

public class DarkHole {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        playerGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
        opponentGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
    }
}
