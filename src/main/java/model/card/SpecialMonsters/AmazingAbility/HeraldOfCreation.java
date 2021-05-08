package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.game.fields.MonsterField;
import model.card.Card;
import model.card.MonsterCard;

import java.io.IOException;
import java.util.ArrayList;

public class HeraldOfCreation {
    public static ArrayList<Card> heraldCards=new ArrayList<>();

    public static void abilityOfHeraldOfCreation(Game game,Card thisHerald) {
        if(!heraldCards.contains(thisHerald)) {
            heraldCards.add(thisHerald);
            // one input card from hand
            Card card;
            GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
            playerGameBoard.getHand().deleteCard(card);
            // input one monster Card with minimum level of 7 from player graveyard
            MonsterCard monsterCard;
            playerGameBoard.getHand().addCard(monsterCard);
        }
    }

    public static Card isThereHeraldOfCreation(MonsterField monsterField){
        ArrayList<MonsterCard> cards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(cards.get(i).getCardName().equals("Herald of Creation")){
                return cards.get(i);
            }
        }
        try {
            return new MonsterCard("-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards.get(0);
    }

}
