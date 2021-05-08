package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.game.Game;
import model.game.GameBoard;

public class SolemnWarning {
    public static boolean isAMonsterSummoned = true;
    public static boolean isSpecialSummonedBecauseOASpellOrTrap = true;
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(isAMonsterSummoned ||
                isSpecialSummonedBecauseOASpellOrTrap) {
            game.getPlayerOfThisTurn().getLifepoint().decreaseLifepoint(2000);
            if(isAMonsterSummoned) {
                /*
                deActive summon and destroy card
                 */
            } else if(isSpecialSummonedBecauseOASpellOrTrap) {
                /*
                deActive summon MonsterCard and destroy it
                 */
            }
        }
    }
}
