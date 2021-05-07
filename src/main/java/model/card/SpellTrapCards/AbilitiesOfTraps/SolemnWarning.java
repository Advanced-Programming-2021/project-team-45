package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;

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
