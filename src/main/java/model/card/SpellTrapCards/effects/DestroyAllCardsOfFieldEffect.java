package model.card.SpellTrapCards.effects;

import model.game.Chain;
import model.game.fields.CardField;
import model.game.fields.CardFieldType;
import model.game.fields.MonsterField;
import model.game.fields.SpellTrapField;

import java.util.ArrayList;

public class DestroyAllCardsOfFieldEffect extends Effect {

    private final CardFieldType[] fieldTypes;
    private ArrayList<CardField> fields;

    public DestroyAllCardsOfFieldEffect(CardFieldType... fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    @Override
    public void activate(Chain chain) {
        initializeFields(chain);

        for (CardField field : fields) {
            if (field instanceof MonsterField) {
                MonsterField monsterField = (MonsterField) field;
                monsterField.deleteAndDestroyAllMonsters();

            } else if (field instanceof SpellTrapField) {
                SpellTrapField spellTrapField = (SpellTrapField) field;
                spellTrapField.deleteAndDestroyAllSpellTrapCards();

            }
        }
    }

    private void initializeFields(Chain chain) {
        fields = new ArrayList<>();
        for (CardFieldType type : fieldTypes) {
            if (type == CardFieldType.PLAYER_MONSTER) {
                fields.add(chain.getPlayerGameBoard().getMonsterField());

            } else if (type == CardFieldType.PLAYER_SPELL_TRAP) {
                fields.add(chain.getPlayerGameBoard().getSpellTrapField());

            } else if (type == CardFieldType.OPPONENT_MONSTER) {
                fields.add(chain.getPlayerGameBoard().getMonsterField());

            } else if (type == CardFieldType.OPPONENT_SPELL_TRAP) {
                fields.add(chain.getPlayerGameBoard().getSpellTrapField());

            }
        }
    }
}
