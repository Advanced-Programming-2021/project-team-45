package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCard;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellTrapField extends CardField {

    private final User owner;
    private final Graveyard graveyard;
    private final SpellTrapCard[] spellTraps;

    public SpellTrapField(User owner, Graveyard graveyard) {
        super("Spell And Trap Field");
        this.owner = owner;
        this.graveyard = graveyard;
        spellTraps = new SpellTrapCard[5];
    }

    public SpellTrapCard[] getSpellTrapCardsPositionsArray() {
        return spellTraps;
    }

    public ArrayList<SpellTrapCard> getSpellTrapsArrayList() {
        return new ArrayList<>(Arrays.asList(spellTraps));
    }

    public int getCount() {
        return getSpellTrapsArrayList().size();
    }

    public SpellTrapCard getPlayerSpellTrapCard(int cardPosition) {
        return spellTraps[cardPosition - 1];
    }

    public SpellTrapCard getOpponentSpellTrapCard(int cardPosition) {
        int position;
        if (cardPosition == 1) {
            position = cardPosition;
        } else if (cardPosition % 2 == 0) {
            position = cardPosition + 1;
        } else {
            position = cardPosition - 1;
        }
        return spellTraps[position - 1];
    }

    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : spellTraps) {
            if (card != null) {
                if (card.getCardName().equals(cardName)) {
                    return card;
                }
            }
        }
        return null;
    }

    public void addSpellTrapCard(SpellTrapCard spellTrapCard) {
        int index = 0;
        while (index < 5) {
            if (spellTraps[index] == null) {
                spellTraps[index] = spellTrapCard;
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroySpellTrap(SpellTrapCard spellTrapCard) {
        for (int i = 0; i < 5; i++) {
            if (spellTraps[i] != null) {
                if (spellTraps[i] == spellTrapCard) {
                    graveyard.addCardToGraveyard(spellTrapCard);
                    spellTraps[i] = null;
                }
            }
        }
    }

    public void deleteAndDestroyAllSpellTrapCards() {
        for (int i = 0; i < 5; i++) {
            if (spellTraps[i] != null) {
                graveyard.addCardToGraveyard(this.spellTraps[i]);
                spellTraps[i] = null;
            }
        }
    }

    public boolean isFull() {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (spellTraps[i] != null)
                count++;
        }
        return count >= 5;
    }

    public boolean isThisCellOfPlayerSpellTrapFieldEmpty(int cardPosition) {
        return getPlayerSpellTrapCard(cardPosition) == null;
    }

    public boolean isThisCellOfOpponentSpellTrapFieldEmpty(int cardPosition) {
        return getOpponentSpellTrapCard(cardPosition) == null;
    }

    public boolean isFull(int index) {
        return spellTraps[index] != null;
    }

    public boolean doesSpellTrapCardExistInField(SpellTrapCard spellTrapCard) {
        return getCardByName(spellTrapCard.getCardName()) != null;
    }
}