package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.game.Game;
import model.game.GameBoard;
import model.game.fields.Hand;
import model.card.Card;
import model.card.SpellTrapCard;

import java.util.ArrayList;
import java.util.Random;

public class MindCrush {
    public void ability(Game game){
        String cardName=""; // in dastoor hatman bayad az voroodi grefte shavad
        if(checkHand(game.getGameBoardOfOpponentPlayerOfThisTurn().getHand(),cardName)){
            Hand hand=game.getGameBoardOfOpponentPlayerOfThisTurn().getHand();
            ArrayList<Card> cards= hand.getCardsInHand();
            for(int i=0;i<cards.size();i++){
                if(cards.get(i).getCardName().equals(cardName)){
                    hand.deleteCard(cards.get(i));
                }
            }
        }else{
            Random random=new Random();
            int num=random.nextInt(game.getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand().size());
            game.getGameBoardOfPlayerOfThisTurn().getHand().deleteCardWithNumberOfIt(num);
        }
        deleteMindCrush(game.getPlayerGameBoard(), game);
    }
    private void deleteMindCrush(GameBoard gameBoard,Game game){
        gameBoard.getSpellTrapField().deleteAndDestroySpellTrap((SpellTrapCard) game.getSelectedCard());
    }
    private boolean checkHand(Hand hand,String cardName){
        ArrayList<Card> cards= hand.getCardsInHand();
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getCardName().equals(cardName)){
                return true;
            }
        }
        return false;
    }

}
