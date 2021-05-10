package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Game;
import model.game.fields.CardField;

import java.util.ArrayList;

public class MonsterRebornEffect extends Effect {

    private ArrayList<CardField> fields;

    @Override
    public void activate(Game game) {
        initializeFields(game);

        ArrayList<Card> cards = game.getGameController().getCardFromPlayer(1, (CardField[]) fields.toArray());
        while (!(cards.get(0) instanceof MonsterCard)) {
            game.getGameController().showOutput("please choose a MONSTER!!");
            cards = game.getGameController().getCardFromPlayer(1, (CardField[]) fields.toArray());
        }

        MonsterCard monster = (MonsterCard) cards.get(0);

    }

    private void initializeFields(Game game) {
        fields = new ArrayList<>();
        fields.add(game.getPlayerGameBoard().getGraveyard());
        fields.add(game.getOpponentGameBoard().getGraveyard());
    }
}
