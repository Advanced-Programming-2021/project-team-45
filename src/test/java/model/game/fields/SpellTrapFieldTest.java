package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCard;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellTrapFieldTest {

    private SpellTrapField spellTrapField;
    private Graveyard graveyard;

    @BeforeAll
    public void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        graveyard = new Graveyard();
        spellTrapField = new SpellTrapField(user, graveyard);
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Time Seal"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Negate Attack"));
    }

    @Test
    void addSpellTrapCard() {
        assertFalse(spellTrapField.doesCardExist("Solemn Warning"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        assertTrue(spellTrapField.doesCardExist("Solemn Warning"));
    }

    @Test
    void getSpellTrapCardsPositionsArray() {
        SpellTrapCard[] expectedSpellTrapCards = new SpellTrapCard[5];
        expectedSpellTrapCards[0] = (SpellTrapCard) Card.getCardByName("Time Seal");
        expectedSpellTrapCards[1] = (SpellTrapCard) Card.getCardByName("Negate Attack");
        expectedSpellTrapCards[2] = (SpellTrapCard) Card.getCardByName("Solemn Warning");

        assertArrayEquals(expectedSpellTrapCards, spellTrapField.getSpellTrapCardsPositionsArray());
    }

    @Test
    void getPlayerSpellTrapCard() {
        assertSame((SpellTrapCard) Card.getCardByName("Negate Attack"), spellTrapField.getPlayerSpellTrapCard(2));
        assertNull(spellTrapField.getPlayerSpellTrapCard(4));
    }

    @Test
    void getOpponentSpellTrapCard() {
        assertNotSame((SpellTrapCard) Card.getCardByName("Negate Attack"), spellTrapField.getOpponentSpellTrapCard(2));
        assertSame((SpellTrapCard) Card.getCardByName("Solemn Warning"), spellTrapField.getOpponentSpellTrapCard(2));
        assertNull(spellTrapField.getOpponentSpellTrapCard(5));
    }

    @Test
    void deleteAndDestroySpellTrap() {
        assertTrue(spellTrapField.doesCardExist("Solemn Warning"));
        spellTrapField.deleteAndDestroySpellTrap((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        assertFalse(spellTrapField.doesCardExist("Solemn Warning"));
        assertTrue(graveyard.doesCardExist("Solemn Warning"));
    }

    @Test
    void isFull() {
        assertFalse(spellTrapField.isFull());
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Forest"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Closed Forest"));
        assertTrue(spellTrapField.isFull());
    }

    @Test
    void isThisCellOfPlayerSpellTrapFieldEmpty() {
        assertFalse(spellTrapField.isThisCellOfPlayerSpellTrapFieldEmpty(5));
        spellTrapField.deleteAndDestroySpellTrap((SpellTrapCard) Card.getCardByName("Closed Forest"));
        assertTrue(spellTrapField.isThisCellOfPlayerSpellTrapFieldEmpty(5));
    }

    @Test
    void isThisCellOfOpponentSpellTrapFieldEmpty() {
        assertFalse(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(1));
        assertTrue(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(4));
    }

    @Test
    void isFullPlace() {
        assertFalse(spellTrapField.isFull(3));
        assertFalse(spellTrapField.isFull(4));
    }

    @Test
    void doesSpellTrapCardExistInField() {
        assertTrue(spellTrapField.doesSpellTrapCardExistInField((SpellTrapCard) Card.getCardByName("Closed Forest")));
        assertFalse(spellTrapField.doesSpellTrapCardExistInField((SpellTrapCard) Card.getCardByName("Magic Jammer")));
    }

    @Test
    void deleteAndDestroyAllSpellTrapCards() {

    }

    @AfterAll
    public void setAfterTest() {
        User.deleteUserByUsername("hajji");
        spellTrapField = null;
        graveyard = null;
    }

}