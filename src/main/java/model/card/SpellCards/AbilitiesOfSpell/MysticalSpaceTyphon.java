package model.card.SpellCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.SpellTrapCard;

public class MysticalSpaceTyphon {
    public static void ability(Game game) {
        Card card;
        // input 1 spellTrapCard From Field
        SpellTrapCard spellTrapCard = (SpellTrapCard) card;
        if(game.getPlayerOfThisTurn().equals(card.getOwner())){
            GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
            if(playerGameBoard.getSpellTrapField().doesSpellTrapCardExistInField(spellTrapCard)) {
                playerGameBoard.getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
            }
        } else {
            GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
            if(opponentGameBoard.getSpellTrapField().doesSpellTrapCardExistInField(spellTrapCard)) {
                opponentGameBoard.getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
            }
        }
    }
}
