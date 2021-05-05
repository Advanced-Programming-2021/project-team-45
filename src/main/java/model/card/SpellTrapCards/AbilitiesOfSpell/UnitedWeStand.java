package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.DOorDH;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class UnitedWeStand {
    public static void ability(Game game){
        // input one monsterCard
        MonsterCard monsterCard;
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getMonsterField().doesExistCardInMonsterField(monsterCard)) {
            if(monsterCard.getPosition() == PositionMonsters.ATTACK ||
            monsterCard.getDefenceMode() == DOorDH.DO) {
                monsterCard.increaseAttack(800);
                monsterCard.increaseDefense(800);
            }
        }
    }
}
