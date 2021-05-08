package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.game.Game;
import model.game.GameBoard;

public class TorrentialTribute {
    public static boolean isAMonsterSummoned = true;
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        if(isAMonsterSummoned) {
            playerGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
            opponentGameBoard.getMonsterField().deleteAndDestroyAllMonsters();
            isAMonsterSummoned = false;
        }
    }
}
