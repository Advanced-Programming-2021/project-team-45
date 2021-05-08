package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class Texchanger {


    private static ArrayList<MonsterCard> allTexchanger = new ArrayList<>();

    public static void abilityOfTexchanger(MonsterCard card, Game game) {
        int input = 0; //ورودی می گیریم اگر یک باشه منظور از دسته 2 باشه دک سه باشه گریویارد
        String input1 = "نام کارت مد نظر";
        if (!allTexchanger.contains(card)) {
            allTexchanger.add(card);
            if (input == 1) {
                getFromHand(game.getGameBoardOfPlayerOfThisTurn(),input1);
            } else if (input == 2) {
                getFromDeck(game.getGameBoardOfPlayerOfThisTurn(),input1);
            } else if (input == 3) {
                getFromGraveyard(game.getGameBoardOfPlayerOfThisTurn(),input1);
            }
        }
    }

    private static void getFromHand(GameBoard gameBoard, String cardName) {
        ArrayList<Card> cards = gameBoard.getHand().getCardsInHand();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardName().equals(cardName)) {
                if (cards.get(i) instanceof MonsterCard) {
                    if (((MonsterCard) cards.get(i)).getSpecialMonsterEnum() == null) {
                        gameBoard.getMonsterField().addMonsterToField((MonsterCard) cards.get(i));
                        gameBoard.getHand().deleteCard(cards.get(i));
                        break;
                    }
                }
            }
        }
    }

    private static void getFromDeck(GameBoard gameBoard, String cardName) {
        ArrayList<Card> cards = gameBoard.getDeckField().getDeck().getMainDeck();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardName().equals(cardName)) {
                if (cards.get(i) instanceof MonsterCard) {
                    if (((MonsterCard) cards.get(i)).getSpecialMonsterEnum() == null) {
                        gameBoard.getMonsterField().addMonsterToField((MonsterCard) cards.get(i));
                        gameBoard.getDeckField().getDeck().deleteCard(cardName, false);
                        break;
                    }
                }
            }
        }
    }

    private static void getFromGraveyard(GameBoard gameBoard, String cardName) {
        ArrayList<Card> cards = gameBoard.getGraveyard().getGraveyardCards();
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getCardName().equals(cardName)){
                if(cards.get(i) instanceof MonsterCard){
                    if(((MonsterCard) cards.get(i)).getSpecialMonsterEnum()==null){
                       gameBoard.getMonsterField().addMonsterToField(((MonsterCard) cards.get(i)));
                       gameBoard.getGraveyard().deleteCardFromGraveyard((MonsterCard) cards.get(i));
                       break;
                    }
                }
            }
        }
    }


    public static void setAllTexchanger() {
        allTexchanger.clear();
    }


}
