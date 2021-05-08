package model.card.SpecialMonsters;

import model.game.Game;
import model.card.Card;
import model.card.MonsterCard;

import model.card.SpecialMonsterEnum;
import model.card.SpecialMonsters.AmazingAbility.*;

public class SpecialMonster {
    private EffectPlace effectPlace;;

    public static void specialMonsterController(Card selectedOrTargetCard, EffectPlace effectPlace, Game game) {
        if (effectPlace.equals(EffectPlace.DESTROY)) {
            if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT)) {
                CommandKnight.abilityOfCommandKnightAtDefense(game, (MonsterCard) selectedOrTargetCard);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.YOMI_SHIP)) {
                YomiShip.abilityOfYomiShip(selectedOrTargetCard, game);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.SUIJIN)) {
                Suijin.suijinAbility(game.getSelectedCard(), game);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MARSHMALLON)) {
                Marshmallon.abilityOfMarshmallon(selectedOrTargetCard, game);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.TEXCHANGER)) {
                Texchanger.abilityOfTexchanger((MonsterCard)selectedOrTargetCard,game);
            }
        } else if (effectPlace.equals(EffectPlace.SUMMON)) {
            if ((((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT))) {
                CommandKnight.abilityOfCommandKnightAtSummon(game);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.THE_CALCULATOR)) {
                TheCalculator.abilityOfTheCalculator(selectedOrTargetCard, game);
            }
            //terrigiater
        } else if (effectPlace.equals(EffectPlace.CHANGETURN)) {
            while (Scanner.haveScanner(game.getGameBoardOfPlayerOfThisTurn().getMonsterField()).getCardName().
                    equals("Scanner")){
                Scanner.activeAbilityOfScanner(game,Scanner.haveScanner(game.getGameBoardOfPlayerOfThisTurn()
                        .getMonsterField()));
            }
            if(HeraldOfCreation.isThereHeraldOfCreation(game.getGameBoardOfPlayerOfThisTurn().getMonsterField())){
                HeraldOfCreation.abilityOfHeraldOfCreation(game);
            }
            //tricky
            //
        } else if (effectPlace.equals(EffectPlace.CHANGEPOSITION)) {
            if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MAN_EATER_BUG)) {
                ManEatBug.abilityOfmanEatBug(game);
            }
        }
    }


    public static boolean isSelectedCardASpecialMonsterOnDestroyMode(Card selectedCard) {
        if (selectedCard instanceof MonsterCard) {
            SpecialMonsterEnum test = ((MonsterCard) selectedCard).getSpecialMonsterEnum();
            return test.equals(SpecialMonsterEnum.COMMAND_KNIGHT) || test.equals(SpecialMonsterEnum.YOMI_SHIP) ||
                    test.equals(SpecialMonsterEnum.SUIJIN) || test.equals(SpecialMonsterEnum.MARSHMALLON) ||
                    test.equals(SpecialMonsterEnum.TEXCHANGER);

        } else {
            return false;
        }
    }

    public static boolean isSelectedCardASpecialMonster(Card selectedCard) {
        if (selectedCard instanceof MonsterCard) {
            return ((MonsterCard) selectedCard).getSpecialMonsterEnum() != null;
        } else {
            return false;
        }
    }


}
