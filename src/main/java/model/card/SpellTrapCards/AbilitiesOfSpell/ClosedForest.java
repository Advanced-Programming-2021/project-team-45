package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;

public class ClosedForest {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        int numberOfMonstersInGraveyard = 0;
        for(Card card : playerGameBoard.getGraveyard().getGraveyardCards()) {
            if(card instanceof MonsterCard)
                numberOfMonstersInGraveyard++;
        }
        for (MonsterCard monsterCard : playerGameBoard.getMonsterField().getMonstersOnField()) {
            if(monsterCard != null) {
                if(monsterCard.getType().equals("Beast") || monsterCard.getType().equals("Beast-Warrior"))
                    monsterCard.increaseAttack(100 * numberOfMonstersInGraveyard);
            }
        }
    }
}
