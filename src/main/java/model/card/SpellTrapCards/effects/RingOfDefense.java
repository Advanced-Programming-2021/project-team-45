package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.SpellTrapCard;
import model.game.Chain;
import model.game.Game;
import model.game.GameBoard;

import java.util.HashMap;

public class RingOfDefense extends Effect {
    private Boolean isActive;
    public static HashMap<Card, RingOfDefense> ringOfDefenseHashMap;


    @Override
    public void activate(Chain chain) {
        Card card = isThereRingOfDefenseInField(chain.getGame());
        if (card != null) {
            if (ringOfDefenseHashMap.get(card) != null) {
                ringOfDefenseHashMap.get(card).isActive = true;
            }
        }
    }


    public Boolean getActive() {
        return isActive;
    }

    public static Card isThereRingOfDefenseInField(Game game) {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        for (int i = 0; i < gameBoard.getSpellTrapField().getSpellTrapsArrayList().size(); i++) {
            if (gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i).getCardName().equals("Ring Of Defense")) {
                ringOfDefenseHashMap.put(gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i)
                        , new RingOfDefense());
                return gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i);
            }
        }
        return null;
    }

}
