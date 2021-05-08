package model.card.SpecialMonsters.AmazingAbility;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Game;
import model.game.fields.Hand;

import java.io.IOException;
import java.util.ArrayList;

public class Tricky {

    public static void ability(Game game,MonsterCard tricky){
        Card deletedCard;
        game.getGameBoardOfPlayerOfThisTurn().getHand().deleteCard(deletedCard);
        game.getGameBoardOfPlayerOfThisTurn().getMonsterField().addMonsterToField(tricky);

    }

    public static Card isThereTricky(Hand hand){
        ArrayList<Card> cards=hand.getCardsInHand();
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getCardName().equals("The Tricky")){
                return cards.get(i);
            }
        }
        try {
            return new MonsterCard("-1");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
