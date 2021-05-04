package model.card.SpellCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.MonsterCard;

public class Umiiruka {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        for(MonsterCard monsterCard : playerGameBoard.getMonsterField().getMonstersOnField()) {
            if(monsterCard != null) {
                if(monsterCard.getType().equals("Aqua")) {
                    monsterCard.increaseAttack(400);
                    monsterCard.decreaseDefense(200);
                }
            }
        }
        for(MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
            if(monsterCard != null) {
                if(monsterCard.getType().equals("Aqua")) {
                    monsterCard.increaseAttack(400);
                    monsterCard.decreaseDefense(200);
                }
            }
        }
    }
}
