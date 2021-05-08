package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class Forest {
    public static void ability(Game game) {
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        for (MonsterCard monsterCard : playerGameBoard.getMonsterField().getMonstersOnField()) {
            if (monsterCard.getType().equals("Insect") || monsterCard.getType().equals("Beast")
                    || monsterCard.getType().equals("Beast-Warrior")) {
                monsterCard.increaseAttack(200);
                monsterCard.increaseDefense(200);
            }
        }
        for (MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
            if (monsterCard.getType().equals("Insect") || monsterCard.getType().equals("Beast")
                    || monsterCard.getType().equals("Beast-Warrior")) {
                monsterCard.increaseAttack(200);
                monsterCard.increaseDefense(200);
            }
        }
    }
}
