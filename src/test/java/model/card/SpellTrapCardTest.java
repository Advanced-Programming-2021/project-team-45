package model.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SpellTrapCardTest {


    @Test
    void isSpell() {
        assertFalse(((SpellTrapCard) Objects.requireNonNull(Card.getCardByName("Time Seal"))).isSpell);
        assertTrue(((SpellTrapCard) Card.getCardByName("Raigeki")).isSpell);

    }

    @Test
    void isActivated() {

    }

    @Test
    void getIcon() {
        assertEquals(SpellAndTrapIcon.NORMAL, Card.getCardByName("Trap Hole"));
        assertEquals(SpellAndTrapIcon.CONTINUOUS, Card.getCardByName("Supply Squad"));
        assertEquals(SpellAndTrapIcon.COUNTER, Card.getCardByName("Negate Attack"));
    }

    @Test
    void summon() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Magic Jammer"))).copy();
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.position);
        spellTrapCard.position = null;
    }

    @Test
    void set() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Terraforming"))).copy();
        spellTrapCard.set();
        assertEquals(SpellsAndTrapPosition.SET ,spellTrapCard.position);
        spellTrapCard.position = null;
    }

    @Test
    void getPosition() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Mirror Force"))).copy();
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.getPosition());
        spellTrapCard.position = null;
    }

    @Test
    void setPosition() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Trap Hole"))).copy();
        spellTrapCard.setPosition(SpellsAndTrapPosition.SET);
        assertEquals(SpellsAndTrapPosition.SET, spellTrapCard.getPosition());
        spellTrapCard.position = null;
    }


    @Test
    void checkClone() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Solemn Warning"))).copy();
        assertSame(Card.getCardByName("Solemn Warning"), spellTrapCard);
    }

}