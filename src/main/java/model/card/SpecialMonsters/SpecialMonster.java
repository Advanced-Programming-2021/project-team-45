package model.card.SpecialMonsters;

import model.game.Game;
import model.card.Card;
import model.card.MonsterCard;

import model.card.SpecialMonsterEnum;
import model.card.SpecialMonsters.AmazingAbility.*;

import java.util.Objects;

public class SpecialMonster {

    public static void specialMonsterController(Card selectedOrTargetCard, EffectPlace effectPlace, Game game) {
        if (effectPlace.equals(EffectPlace.DESTROY)) {
            destroyEffectPlace(selectedOrTargetCard,game);

        } else if (effectPlace.equals(EffectPlace.SUMMON)) {
            summonEffectPlace(selectedOrTargetCard,game);

        } else if (effectPlace.equals(EffectPlace.CHANGETURN)) {
            changeTurnEffectPlace(game);

        } else if (effectPlace.equals(EffectPlace.CHANGEPOSITION)) {
            changePosition(selectedOrTargetCard,game);
        }
    }

    private static void destroyEffectPlace(Card selectedOrTargetCard,  Game game){
        if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT)) {
            CommandKnight.defenseAbility(game, (MonsterCard) selectedOrTargetCard);

        } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.YOMI_SHIP)) {
            YomiShip.ability(selectedOrTargetCard, game);

        } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.SUIJIN)) {
            Suijin.ability(game.getSelectedCard(), game);

        } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MARSHMALLON)) {
            Marshmallon.ability(selectedOrTargetCard, game);

        } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.TEXCHANGER)) {
            Texchanger.ability((MonsterCard)selectedOrTargetCard,game);

        }
    }

    private static void summonEffectPlace(Card selectedOrTargetCard, Game game){
        if ((((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT))) {
            CommandKnight.summonAbility(game);

        } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.THE_CALCULATOR)) {
            TheCalculator.ability(selectedOrTargetCard, game);

        }else if( ((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.TERRATIGER)){
            Terratiger.ability(game);
        }
    }

    private static void changeTurnEffectPlace(Game game){
        while (Scanner.haveScanner(game.getGameBoardOfPlayerOfThisTurn().getMonsterField()).getCardName().
                equals("Scanner")){
            Scanner.ability(game,Scanner.haveScanner(game.getGameBoardOfPlayerOfThisTurn()
                    .getMonsterField()));
        }

        if(Objects.requireNonNull(HeraldOfCreation.isThereHeraldOfCreation(game.getGameBoardOfPlayerOfThisTurn()
                .getMonsterField())).getCardName().equals("Herald of Creation")){
            HeraldOfCreation.abilityOfHeraldOfCreation(game,HeraldOfCreation
                    .isThereHeraldOfCreation(game.getGameBoardOfPlayerOfThisTurn().getMonsterField()));
        }

        if(Tricky.isThereTricky(game.getGameBoardOfPlayerOfThisTurn().getHand()).getCardName().equals("The Tricky")){
            Tricky.ability(game,(MonsterCard) Tricky.isThereTricky(game.getGameBoardOfPlayerOfThisTurn().getHand()));
        }
    }

    private static void changePosition(Card selectedOrTargetCard, Game game){
        if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MAN_EATER_BUG)) {
            ManEatBug.ability(game);
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
