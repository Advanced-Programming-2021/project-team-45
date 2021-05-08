package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class BlackPendant {
    public static void ability(Game game){
        // input one monsterCard
        MonsterCard monsterCard;
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getMonsterField().doesExistCardInMonsterField(monsterCard)) {
            monsterCard.increaseAttack(500);
        }
    }
}
