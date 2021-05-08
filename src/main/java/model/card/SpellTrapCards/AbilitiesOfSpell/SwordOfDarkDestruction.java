package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class SwordOfDarkDestruction {
    public static void ability(Game game){
        // input one monsterCard
        MonsterCard monsterCard;
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getMonsterField().doesExistCardInMonsterField(monsterCard)) {
            if (monsterCard.getType().equals("Fiend") ||
                    monsterCard.getType().equals("Spellcaster")) {
                monsterCard.increaseAttack(400);
                monsterCard.decreaseDefense(200);
            }
        }
    }
}
