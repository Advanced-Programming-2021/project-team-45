package model.card.SpecialMonsters.AmazingAbility;

import controller.GameController;
import model.Game.Game;
import model.Game.MonsterField;
import model.card.MonsterCard;

public class BeastKingBarbaros {
    public static void reducePower (MonsterCard monster) {

    }



    public static int summonHandler(MonsterCard monster, Game game, GameController gameController) {
        MonsterField monsterField = game.getPlayerGameBoard().getMonsterField();
        if (monsterField.getNumberOfMonstersInField() > 2) {
            boolean tribute3 = gameController.getYesNoAnswer("do you want to tribute 3 monsters?");
            if (tribute3) {
                cardsToTribute = gameController.getCardsForTribute(2);
                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 9;
                }
            } else {
                boolean tribute2 =gameController.getYesNoAnswer("do you want to tribute 2 monsters?");
            }
        } else {
            // here we have to reduce it's ATK/DFN
//                SpecialMonster.specialMonsterController(monster, EffectPlace.SUMMON, game);
            cardsToTribute = playerDuelMenu.getCardsForTribute(0);
            game.tributeSummon(cardsToTribute);
            return 6;
        }
    }

    private void killAllOpponentMonsters(Game game) {

    }

    private void reduceKingPower(MonsterCard monster) {

    }

    
}