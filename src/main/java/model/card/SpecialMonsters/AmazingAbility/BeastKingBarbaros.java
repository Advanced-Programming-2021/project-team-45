package model.card.SpecialMonsters.AmazingAbility;

import controller.GameController;
import model.game.Game;
import model.game.GameErrorHandler;
import model.game.fields.MonsterField;
import model.card.MonsterCard;

import java.util.ArrayList;

public class BeastKingBarbaros {

    private final Game game;
    private final GameErrorHandler gameErrorHandler;
    private final GameController gameController;

    public BeastKingBarbaros(Game game, GameErrorHandler gameErrorHandler, GameController gameController) {
        this.game = game;
        this.gameErrorHandler = gameErrorHandler;
        this.gameController = gameController;
    }


    public int summonHandler(MonsterCard monster) {
        MonsterField monsterField = game.getPlayerGameBoard().getMonsterField();
        if (monsterField.getNumberOfMonstersInField() > 2) {
            Boolean tribute3 = gameController.getYesNoAnswer("do you want to tribute 3 monsters?");
            if (tribute3 == null) return -1;

            if (tribute3) {
                return tributeHandler(3, monster);
            } else {
                Boolean tribute2 = gameController.getYesNoAnswer("do you want to tribute 2 monsters?");
                if (tribute2 == null) return -1;

                if (tribute2) {
                    return tributeHandler(2, monster);
                } else {
                    return tributeHandler(0, monster);
                }
            }
        } else if (monsterField.getNumberOfMonstersInField() > 1) {
            Boolean tribute2 = gameController.getYesNoAnswer("do you want to tribute 2 monsters?");
            if (tribute2 == null) return -1;

            if (tribute2) {
                return tributeHandler(2, monster);
            } else {
                return tributeHandler(0, monster);
            }
        } else {
            return tributeHandler(0, monster);
        }
    }

    private int tributeHandler(int n, MonsterCard monster) {
        ArrayList<Integer> cardsToTribute = gameController.getCardsForTribute(n);
        if (cardsToTribute == null) return -1;

        if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
            if (n == 3) {
                killAllOpponentMonsters();
            } else if (n == 0) {
                reduceKingPower(monster);
            }
            game.tributeSummon(cardsToTribute);
            return 6;
        } else {
            return 9;
        }
    }

    private void killAllOpponentMonsters() {
        MonsterField opponentMonsterField = game.getOpponentGameBoard().getMonsterField();
        opponentMonsterField.deleteAndDestroyAllMonsters();
    }

    private void reduceKingPower(MonsterCard monster) {
        monster.decreaseAttack(1100);
    }
}