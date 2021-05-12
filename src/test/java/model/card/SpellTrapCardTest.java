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
        SpellTrapCard spellTrapCard = (SpellTrapCard) (Card.getCardByName("Magic Jammer")).clone();
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.position);
    }

    @Test
    void set() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) (Card.getCardByName("Terraforming").clone());
        spellTrapCard.set();
        assertEquals(SpellsAndTrapPosition.SET ,spellTrapCard.position);
    }

    @Test
    void getPosition() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) (Card.getCardByName("Mirror Force")).clone();
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.getPosition());
    }

    @Test
    void setPosition() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) (Card.getCardByName("Trap Hole")).clone();
        spellTrapCard.setPosition(SpellsAndTrapPosition.SET);
        assertEquals(SpellsAndTrapPosition.SET, spellTrapCard.getPosition());
    }


    @Test
    void checkClone() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) (Card.getCardByName("Solemn Warning").clone());
        assertSame(Card.getCardByName("Solemn Warning"), spellTrapCard);
    }

}