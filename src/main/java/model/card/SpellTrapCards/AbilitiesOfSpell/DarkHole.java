package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;

public class DarkHole {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        playerGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
        opponentGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
    }
}
