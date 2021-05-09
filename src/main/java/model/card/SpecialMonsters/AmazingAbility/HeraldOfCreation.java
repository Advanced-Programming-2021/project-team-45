package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.game.fields.CardField;
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
            CardField[] cardFields={game.getGameBoardOfPlayerOfThisTurn().getHand()};
            ArrayList<Card> card=game.getGameController().getCardFromPlayer(1,cardFields);
            if(card.get(0)!=null) {
                GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
                playerGameBoard.getHand().deleteCard(card.get(0));
                // input one monster Card with minimum level of 7 from player graveyard
                MonsterCard monsterCard=getMonster(game,card.get(0));
                if(monsterCard!=null) {
                    playerGameBoard.getHand().addCard(monsterCard);
                }
            }
        }
    }

    private static MonsterCard getMonster(Game game ,Card DeletedCard){
        CardField[] cardFields={game.getGameBoardOfPlayerOfThisTurn().getGraveyard()};
        ArrayList<Card>  card=game.getGameController().getCardFromPlayer(1,cardFields);
        if(card.get(0)!=null){
            if(card.get(0) instanceof MonsterCard){
                if(((MonsterCard) card.get(0)).getLevel()>=7){
                    return (MonsterCard) card.get(0);
                }else{
                    game.getGameController().showOutput("your choice is wrong");
                    game.getGameBoardOfPlayerOfThisTurn().getHand().addCard(DeletedCard);
                    return null;
                }
            }else{
                game.getGameController().showOutput("your choice is wrong");
                game.getGameBoardOfPlayerOfThisTurn().getHand().addCard(DeletedCard);
                return null;
            }
        }else{
            return null;
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
