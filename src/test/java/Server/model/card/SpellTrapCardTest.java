package Server.model.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellTrapCardTest {


    @Test
    void setPosition() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Trap Hole")));
        spellTrapCard.setPosition(SpellsAndTrapPosition.SET);
        assertEquals(SpellsAndTrapPosition.SET, spellTrapCard.getPosition());
        spellTrapCard.position = null;
    }

    @Test
    void getPosition() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Mirror Force")));
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.getPosition());
        spellTrapCard.position = null;
    }

    @Test
    void summon() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Negate Attack")));
        spellTrapCard.summon();
        assertEquals(SpellsAndTrapPosition.SUMMON ,spellTrapCard.position);
        spellTrapCard.position = null;
    }

    @Test
    void checkClone() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Solemn Warning"))).copy();
        assertEquals("Solemn Warning", spellTrapCard.getCardName());
    }

    @Test
    void getIcon() {
        assertEquals(SpellAndTrapIcon.NORMAL, ((SpellTrapCard)Card.getCardByName("Trap Hole")).getIcon());
        assertEquals(SpellAndTrapIcon.CONTINUOUS, ((SpellTrapCard)Card.getCardByName("Supply Squad")).getIcon());
        assertEquals(SpellAndTrapIcon.COUNTER, ((SpellTrapCard)Card.getCardByName("Negate Attack")).getIcon());
    }

    @Test
    void set() {
        SpellTrapCard spellTrapCard = ((SpellTrapCard) (Card.getCardByName("Terraforming"))).copy();
        spellTrapCard.set();
        assertEquals(SpellsAndTrapPosition.SET ,spellTrapCard.position);
        spellTrapCard.position = null;
    }

    @Test
    void isActivated() {

    }


    @Test
    void isSpell() {
        assertTrue(((SpellTrapCard) (Card.getCardByName("Raigeki"))).isSpell);
        assertFalse(((SpellTrapCard) Card.getCardByName("Trap Hole")).isSpell);

    }









}