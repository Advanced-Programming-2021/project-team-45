package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Chain;
import model.game.Game;
import model.game.fields.CardField;


import java.util.ArrayList;

public class AdvancedRitualArtEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        if (isThereAnyRitualCardInHand(chain)) {
            CardField[] cardFields = {chain.getPlayerGameBoard().getHand()};
            CardField[] cardFieldsMonsterField = {chain.getPlayerGameBoard().getMonsterField()};
            ArrayList<Card> ritualCard = chain.getGame().getGameController().getCardFromPlayer(1, cardFields);
            if (ritualCard.get(0).getCardType().equals("Ritual")) {
                int numberOfCards = getNumberOfCardsHaveToRitual(chain.getGame());
                ArrayList<Card> cardsForRitual = chain.getGame().getGameController().
                        getCardFromPlayer(numberOfCards, cardFieldsMonsterField);
                if (canInputThisMonsterOnField(cardsForRitual, (MonsterCard) ritualCard.get(0))) {
                    specialSummon(cardsForRitual, ritualCard.get(0), chain);
                } else {
                    chain.getGame().getGameController().showOutput("this selected card haven't required level for ritual");
                }
            } else {
                chain.getGame().getGameController().showOutput("your selected Card isn't a ritual monster");
            }
        } else {
            chain.getGame().getGameController().showOutput("you dont have ritual monster on your hand");
        }
    }

    private boolean isThereAnyRitualCardInHand(Chain chain) {
        ArrayList<Card> hand = chain.getPlayerGameBoard().getHand().getCardsInHand();
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

    private void specialSummon(ArrayList<Card> cards, Card card, Chain chain) {
        ArrayList<MonsterCard> cardsOnField = chain.getPlayerGameBoard().getMonsterField().getMonstersOnField();
        for (int j = 0; j < cards.size(); j++) {
            for (int i = 0; i < cardsOnField.size(); i++) {
                if (cardsOnField.get(i).equals(cards.get(j))) {
                    chain.getPlayerGameBoard().getMonsterField().deleteAndDestroyMonster(cardsOnField.get(i));
                }
            }
        }
        chain.getPlayerGameBoard().getMonsterField().addMonsterToField((MonsterCard) card);
    }

}
