package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.MonsterCard;

public class Yami {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        for(MonsterCard monsterCard : playerGameBoard.getMonsterField().getMonstersOnField()) {
            if (monsterCard != null) {
                if (monsterCard.getType().equals("Fairy")) {
                    monsterCard.decreaseAttack(200);
                    monsterCard.decreaseDefense(200);
                } else if (monsterCard.getType().equals("Fiend") || monsterCard.equals("Spellcaster")) {
                    monsterCard.increaseAttack(200);
                    monsterCard.increaseDefense(200);
                }
            }
        }
    }
}
