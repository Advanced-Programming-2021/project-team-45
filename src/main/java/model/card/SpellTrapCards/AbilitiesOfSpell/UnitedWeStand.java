package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class UnitedWeStand {
    public static void ability(Game game){
        // input one monsterCard
        MonsterCard monsterCard;
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getMonsterField().doesExistCardInMonsterField(monsterCard)) {
            if(monsterCard.getPosition() == PositionMonsters.ATTACK ||
            monsterCard.getDefenceMode() == DefensePosition.DO) {
                monsterCard.increaseAttack(800);
                monsterCard.increaseDefense(800);
            }
        }
    }
}
