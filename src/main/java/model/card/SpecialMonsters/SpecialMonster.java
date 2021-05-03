package model.card.SpecialMonsters;

import model.Game.Game;
import model.card.Card;
import model.card.MonsterCard;

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
            if (selectedOrTargetCard.getCardName().equals("Command knight")) {
                CommandKnight.abilityOfCommandKnightAtDefense();
            } else if (selectedOrTargetCard.getCardName().equals("Yomi Ship")) {
                YomiShip.abilityOfYomiShip(selectedOrTargetCard ,game);
            } else if (selectedOrTargetCard.getCardName().equals("Suijin")) {
                SuijinAbility.suijinAbility(selectedOrTargetCard);
            } else if (selectedOrTargetCard.getCardName().equals("Marshmallon")){
                Marshmallon.abilityOfMarshmallon(selectedOrTargetCard ,game);
            }else if(selectedOrTargetCard.getCardName().equals("Texchanger")){

            }

        } else if (effectPlace.equals(EffectPlace.SUMMON)) {
            if (selectedOrTargetCard.getCardName().equals("Command knight")) {
                CommandKnight.abilityOfCommandKnightAtSummon();
            }else if(selectedOrTargetCard.getCardName().equals("The Calculator")){
                TheCalculator.abilityOfTheCalculator(selectedOrTargetCard, game);
            }
        } else if (effectPlace.equals(EffectPlace.SELECT)) {
            if(selectedOrTargetCard.getCardName().equals("Scanner")){
                Scanner.activeAbilityOfScanner((MonsterCard) selectedOrTargetCard);
            }
        } else if (effectPlace.equals(EffectPlace.CHANGEPOSITION)) {
            if (selectedOrTargetCard.getCardName().equals("Man-Eater Bug")) {
                ManEatBug.abilityOfmanEatBug(game);
            }
        }else if(effectPlace.equals(EffectPlace.SPELLACTIVE)){
            if(MirageDragon.checkField(game.getGameBoardOfOpponentPlayerOfThisTurn())){
                MirageDragon.abilityOfMirageDragon();
            }
        }
    }

    public static boolean isSelectedCardASpecialMonsterOnSummonMode(Card selectedCard) {

    }

    public static boolean isSelectedCardASpecialMonsterOnDefenseMode(Card selectedCard) {

    }

    public static boolean isSelectedCardASpecialMonsterOnSelectMode(Card selectedCard) {

    }

    public static boolean isSelectedCardASpecialMonsterOnChangePositionMode(Card selectedCard) {

    }




}
