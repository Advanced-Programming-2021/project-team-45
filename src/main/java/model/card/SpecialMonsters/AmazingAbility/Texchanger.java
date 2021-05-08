package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class Texchanger {


    private static ArrayList<MonsterCard> allTexchanger = new ArrayList<>();

    public static void abilityOfTexchanger(MonsterCard card, Game game) {
        String input1 = "نام کارت مد نظر";
        if (!allTexchanger.contains(card)) {
            allTexchanger.add(card);
            getCard(game.getGameBoardOfPlayerOfThisTurn(),input1);
        }
    }

    private static void getCard(GameBoard gameBoard, String cardName) {
        ArrayList<Card> cards = gameBoard.getHand().getCardsInHand();
        ArrayList<Card> cards1 = gameBoard.getDeckField().getDeck().getMainDeck();
        ArrayList<Card> cards2 = gameBoard.getGraveyard().getGraveyardCards();
        boolean bol = true;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardName().equals(cardName)) {
                if (cards.get(i) instanceof MonsterCard) {
                    if (((MonsterCard) cards.get(i)).getSpecialMonsterEnum() == null) {
                        gameBoard.getMonsterField().addMonsterToField((MonsterCard) cards.get(i));
                        gameBoard.getHand().deleteCard(cards.get(i));
                        bol=false;
                        break;
                    }
                }
            }
        }
        if (bol) {
            for (int i = 0; i < cards1.size(); i++) {
                if (cards1.get(i).getCardName().equals(cardName)) {
                    if (cards1.get(i) instanceof MonsterCard) {
                        if (((MonsterCard) cards1.get(i)).getSpecialMonsterEnum() == null) {
                            gameBoard.getMonsterField().addMonsterToField((MonsterCard) cards1.get(i));
                            gameBoard.getDeckField().getDeck().deleteCard(cardName, false);
                            bol = false;
                            break;
                        }
                    }
                }
            }
        }
        if (bol) {
            for (int i = 0; i < cards2.size(); i++) {
                if (cards2.get(i).getCardName().equals(cardName)) {
                    if (cards2.get(i) instanceof MonsterCard) {
                        if (((MonsterCard) cards2.get(i)).getSpecialMonsterEnum() == null) {
                            gameBoard.getMonsterField().addMonsterToField(((MonsterCard) cards2.get(i)));
                            gameBoard.getGraveyard().deleteCardFromGraveyard((MonsterCard) cards2.get(i));
                            bol = false;
                            break;
                        }
                    }
                }
            }
        }
    }


    public static void setAllTexchanger() {
        allTexchanger.clear();
    }


}
