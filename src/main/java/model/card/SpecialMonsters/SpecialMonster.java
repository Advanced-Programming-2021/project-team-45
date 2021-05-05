package model.card.SpecialMonsters;

import model.Game.Game;
import model.card.Card;
import model.card.MonsterCard;

import model.card.SpecialMonsterEnum;
import model.card.SpecialMonsters.AmazingAbility.*;

import java.util.ArrayList;

public class SpecialMonster {
    private EffectPlace effectPlace;
    public static boolean suijinActive;


    public static ArrayList<MonsterCard> allSpecialMonsterCard = new ArrayList<>();


    public void execute() {
        // all special card
    }


    public static void specialMonsterController(Card selectedOrTargetCard, EffectPlace effectPlace, Game game) {
        if (effectPlace.equals(EffectPlace.DESTROY)) {
            if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT)) {
                CommandKnight.abilityOfCommandKnightAtDefense();
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.YOMI_SHIP)) {
                YomiShip.abilityOfYomiShip(selectedOrTargetCard ,game);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.SUIJIN)) {
                SuijinAbility.suijinAbility(selectedOrTargetCard);
            } else if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MARSHMALLON)){
                Marshmallon.abilityOfMarshmallon(selectedOrTargetCard ,game);
            }else if(((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.TEXCHANGER)){
                Texchanger.abilityOfTexchanger();
            }

        } else if (effectPlace.equals(EffectPlace.SUMMON)) {
            if ((((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.COMMAND_KNIGHT))) {
                CommandKnight.abilityOfCommandKnightAtSummon();
            }else if(((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.THE_CALCULATOR)){
                TheCalculator.abilityOfTheCalculator(selectedOrTargetCard, game);
            }
        } else if (effectPlace.equals(EffectPlace.SELECT)) {
            if((((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.SCANNER))){
                Scanner.activeAbilityOfScanner((MonsterCard) selectedOrTargetCard);
            }
        } else if (effectPlace.equals(EffectPlace.CHANGEPOSITION)) {
            if (((MonsterCard) selectedOrTargetCard).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MAN_EATER_BUG)) {
                ManEatBug.abilityOfmanEatBug(game);
            }
        }else if(effectPlace.equals(EffectPlace.SPELLACTIVE)){
            if(MirageDragon.checkField(game.getGameBoardOfOpponentPlayerOfThisTurn())){
                MirageDragon.abilityOfMirageDragon();
            }
        }
    }


    public static boolean isSelectedCardASpecialMonsterOnDestroyMode(Card selectedCard) {
        if(selectedCard instanceof MonsterCard){
            SpecialMonsterEnum test=((MonsterCard) selectedCard).getSpecialMonsterEnum();
            boolean bol=test.equals(SpecialMonsterEnum.COMMAND_KNIGHT)||test.equals(SpecialMonsterEnum.YOMI_SHIP)||
                    test.equals(SpecialMonsterEnum.SUIJIN)||test.equals(SpecialMonsterEnum.MARSHMALLON)||
                    test.equals(SpecialMonsterEnum.TEXCHANGER);
            return bol;
        }else {
            return false;
        }
    }

    public static boolean isSelectedCardASpecialMonster(Card selectedCard) {
        if(selectedCard instanceof MonsterCard){
            if(((MonsterCard) selectedCard).getSpecialMonsterEnum()!=null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }




}
