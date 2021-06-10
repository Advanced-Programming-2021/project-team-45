package model.card.SpecialMonsters.AmazingAbility;

import controller.GameController;
import model.card.MonsterType;
import model.card.PositionMonsters;
import model.game.Game;
import model.game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;
import model.game.fields.CardField;

import java.util.ArrayList;

public class Texchanger {

    private static ArrayList<MonsterCard> allTexchanger = new ArrayList<>();

    public static void ability(MonsterCard card, Game game) {
        if (!allTexchanger.contains(card)) {
            allTexchanger.add(card);
            Card input1 = getinput(game);
            if (input1 != null) {
                getCard(game.getPlayerGameBoard(), input1, game.getGameController());
            }
        }
    }

    private static void getCard(GameBoard gameBoard, Card card, GameController gameController) {
        if(!gameBoard.getMonsterField().isFull()){
            gameBoard.getMonsterField().addMonsterToField((MonsterCard) card);
            ((MonsterCard) card).setPosition(PositionMonsters.ATTACK);
        }else{
            gameController.showOutput("your monster field is full you can't add a new card");
        }
    }

    private static Card getinput(Game game) {
        CardField[] cardFields = {game.getPlayerGameBoard().getDeckField(), game.getPlayerGameBoard().getHand(),
                game.getPlayerGameBoard().getGraveyard()};
        ArrayList<Card> input = game.getGameController().getCardFromPlayer(1, cardFields);
        while (true) {
            if (input != null) {
                if (input.get(0) instanceof MonsterCard) {
                    if (((MonsterCard) input.get(0)).getType().equals(MonsterType.Cyberse)) {
                        return input.get(0);
                    }
                }
            } else {
                return null;
            }
            game.getGameController().showOutput("your input is not correct please try again");
            input.clear();
            input = game.getGameController().getCardFromPlayer(1, cardFields);
        }
    }

    public static void setAllTexchanger() {
        allTexchanger.clear();
    }


}
