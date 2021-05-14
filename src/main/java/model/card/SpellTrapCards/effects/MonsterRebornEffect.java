package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Chain;
import model.game.Game;
import model.game.fields.CardField;

import java.util.ArrayList;

public class MonsterRebornEffect extends Effect {

    private ArrayList<CardField> fields;

    @Override
    public void activate(Chain chain) {
        initializeFields(chain);

        ArrayList<Card> cards = chain.getGame().getGameController().getCardFromPlayer(1, (CardField[]) fields.toArray());
        while (!(cards.get(0) instanceof MonsterCard)) {
            chain.getGame().getGameController().showOutput("please choose a MONSTER!!");
            cards = chain.getGame().getGameController().getCardFromPlayer(1, (CardField[]) fields.toArray());
        }

        MonsterCard monster = (MonsterCard) cards.get(0);
        chain.getGame().specialSummon(monster, chain.getPlayerGameBoard());
    }

    private void initializeFields(Chain chain) {
        fields = new ArrayList<>();
        fields.add(chain.getPlayerGameBoard().getGraveyard());
        fields.add(chain.getOpponentGameBoard().getGraveyard());
    }
}
