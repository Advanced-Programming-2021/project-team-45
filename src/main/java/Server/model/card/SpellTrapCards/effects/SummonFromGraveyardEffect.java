package Server.model.card.SpellTrapCards.effects;

import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.game.Chain;
import Server.model.game.fields.CardField;
import Server.model.game.fields.CardFieldType;

import java.util.ArrayList;

public class SummonFromGraveyardEffect extends Effect {

    private final CardFieldType[] fieldTypes;
    private ArrayList<CardField> fields;

    public SummonFromGraveyardEffect(CardFieldType... fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

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
        for (CardFieldType fieldType : fieldTypes) {
            if (fieldType == CardFieldType.OPPONENT_GRAVEYARD) {
                fields.add(chain.getOpponentGameBoard().getGraveyard());
            } else if (fieldType == CardFieldType.PLAYER_GRAVEYARD) {
                fields.add(chain.getPlayerGameBoard().getGraveyard());
            }
        }
    }
}
