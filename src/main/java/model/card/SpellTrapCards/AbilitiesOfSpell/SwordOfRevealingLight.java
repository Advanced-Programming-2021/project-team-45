package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.SpellTrapCard;

public class SwordOfRevealingLight {
    public static int currentTurn = 0;

    public static void ability(Card selectedOrTargetCard, Game game){
        SpellTrapCard spellTrapCard = (SpellTrapCard) selectedOrTargetCard;
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        for(MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
            if(monsterCard != null) {
                if(monsterCard.getDefenceMode() == DefensePosition.DH) {
                    monsterCard.setDefenceMode(DefensePosition.DO);
                }
            }
        }

        while(currentTurn != 3) {
            game.getOpponentOfThisTurn().disableToAttack();
        }

        currentTurn = 0;
        playerGameBoard.getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
    }
}
