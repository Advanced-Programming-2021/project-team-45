package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.MonsterCard;

public class ManEatBug {

    public static void abilityOfmanEatBug(Game game) {
        // bugs -haji
        String input = game.getGameController().NumberOfField("man eater bug get actived" +
                ", which number of his monsterField do you want get destroyed");
        if (input != null) {
            int targetMonsterFieldPosition = Integer.parseInt(input);
            GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
            if (!opponentGameBoard.getMonsterField().
                    isThisCellOfMonsterFieldEmptyInOpponentMode(targetMonsterFieldPosition)) {
                MonsterCard monsterCard = opponentGameBoard.
                        getMonsterField().getMonsterCardFromMonsterFieldInOpponentMode(targetMonsterFieldPosition);
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
            }
        }
    }
}
