package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class HeraldOfCreation {
    private static boolean canUse = true;
    public static void abilityOfHeraldOfCreation(Game game) {
        if(canUse) {
            // one input card from hand
            Card card;
            GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
            playerGameBoard.getHand().deleteCard(card);
            // input one monster Card with minimum level of 7 from player graveyard
            MonsterCard monsterCard;
            playerGameBoard.getHand().addCard(monsterCard);
        }
    }
    public static boolean isThereHeraldOfCreation(MonsterField monsterField){
        ArrayList<MonsterCard> cards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(cards.get(i).getCardName().equals("Herald of Creation")){
                return true;
            }
        }
        return false;
    }
}
