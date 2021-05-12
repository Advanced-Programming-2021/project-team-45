package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Game;
import model.game.fields.CardField;


import java.util.ArrayList;

public class AdvancedRitualArtEffect extends Effect {
    @Override
    public void activate(Game game) {
        if (isThereAnyRitualCardInHand(game)) {
            CardField[] cardFields = {game.getGameBoardOfPlayerOfThisTurn().getHand()};
            CardField[] cardFieldsMonsterField = {game.getGameBoardOfPlayerOfThisTurn().getMonsterField()};
            ArrayList<Card> ritualCard = game.getGameController().getCardFromPlayer(1, cardFields);
            if (ritualCard.get(0).getCardType().equals("Ritual")) {
                int numberOfCards = getNumberOfCardsHaveToRitual(game);
                ArrayList<Card> cardsForRitual = game.getGameController().
                        getCardFromPlayer(numberOfCards, cardFieldsMonsterField);
                if (canInputThisMonsterOnField(cardsForRitual, (MonsterCard) ritualCard.get(0))) {
                    specialSummon(cardsForRitual, ritualCard.get(0), game);
                } else {
                    game.getGameController().showOutput("this selected card haven't required level for ritual");
                }
            } else {
                game.getGameController().showOutput("your selected Card isn't a ritual monster");
            }
        } else {
            game.getGameController().showOutput("you dont have ritual monster on your hand");
        }
    }

    private boolean isThereAnyRitualCardInHand(Game game) {
        ArrayList<Card> hand = game.getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof MonsterCard) {
                if (hand.get(i).getCardType().equals("Ritual")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canInputThisMonsterOnField(ArrayList<Card> carsForRitual, MonsterCard card) {
        int sumLevels = 0;
        for (int i = 0; i < carsForRitual.size(); i++) {
            MonsterCard monsterCard = (MonsterCard) carsForRitual.get(i);
            sumLevels += monsterCard.getLevel();
        }
        return sumLevels == card.getLevel();
    }

    private int getNumberOfCardsHaveToRitual(Game game) {
        return game.getGameController().
                getNumberFromPlayer("enter number of cards do you want ritual from your field");
    }

    private void specialSummon(ArrayList<Card> cards, Card card, Game game) {
        ArrayList<MonsterCard> cardsOnField = game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField();
        for (int j = 0; j < cards.size(); j++) {
            for (int i = 0; i < cardsOnField.size(); i++) {
                if (cardsOnField.get(i).equals(cards.get(j))) {
                    game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(cardsOnField.get(i));
                }
            }
        }
        game.getGameBoardOfPlayerOfThisTurn().getMonsterField().addMonsterToField((MonsterCard) card);
    }

}
