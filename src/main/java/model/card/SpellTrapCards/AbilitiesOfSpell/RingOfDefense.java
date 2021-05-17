package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.card.Card;
import model.card.SpellTrapCard;
import model.card.SpellsAndTrapPosition;
import model.game.Game;
import model.game.GameBoard;

import java.util.HashMap;

public class RingOfDefense {
    private Boolean isActive;
    public static HashMap<Card,RingOfDefense> ringOfDefenseHashMap;

    RingOfDefense(SpellTrapCard card){
        if(ringOfDefenseHashMap.get(card)==null){
            ringOfDefenseHashMap.put(card,this);
        }
    }

    public static void ability(SpellTrapCard card){
        if(ringOfDefenseHashMap.get(card)!=null){
            ringOfDefenseHashMap.get(card).isActive=true;
        }
    }


    public Boolean getActive() {
        return isActive;
    }

    public static Card isThereRingOfDefenseInField(Game game){
        GameBoard gameBoard=game.getGameBoardOfPlayerOfThisTurn();
        for(int i=0;i<gameBoard.getSpellTrapField().getSpellTrapsArrayList().size();i++){
            if(gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i).getCardName().equals("Ring Of Defense")){
                return gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i);
            }
        }
        return null;
    }




}
