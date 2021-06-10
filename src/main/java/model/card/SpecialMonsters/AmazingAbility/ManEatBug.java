package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class ManEatBug {

    public static void ability(Game game) {
        // bugs -haji
        String input = game.getGameController().NumberOfField("man eater bug get actived" +
                ", which number of his monsterField do you want get destroyed");
        if (input != null) {
            int targetMonsterFieldPosition = Integer.parseInt(input);
            GameBoard opponentGameBoard = game.getOpponentGameBoard();
            if (!opponentGameBoard.getMonsterField().
                    isFieldEmpty(targetMonsterFieldPosition)) {
                MonsterCard monsterCard = opponentGameBoard.
                        getMonsterField().getMonsterCardOpponentFromMonsterField(targetMonsterFieldPosition);
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
            }
        }
    }
}
