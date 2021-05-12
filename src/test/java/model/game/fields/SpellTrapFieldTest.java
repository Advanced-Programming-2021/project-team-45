package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCard;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SpellTrapFieldTest {

    private static SpellTrapField spellTrapField;
    private static Graveyard graveyard;

    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        graveyard = new Graveyard();
        spellTrapField = new SpellTrapField(user, graveyard);
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Time Seal"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Negate Attack"));
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
    void getPlayerSpellTrapCard() {
        assertSame((SpellTrapCard) Card.getCardByName("Negate Attack"), spellTrapField.getPlayerSpellTrapCard(2));
        spellTrapField.deleteAndDestroySpellTrap((SpellTrapCard) Card.getCardByName("Closed Forest"));
        assertNull(spellTrapField.getPlayerSpellTrapCard(5));
    }

    @Test
    void isThisCellOfPlayerSpellTrapFieldEmpty() {
        assertTrue(spellTrapField.isThisCellOfPlayerSpellTrapFieldEmpty(5));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Closed Forest"));
        assertFalse(spellTrapField.isThisCellOfPlayerSpellTrapFieldEmpty(5));
    }

    @Test
    void isFullPlace() {
        assertTrue(spellTrapField.isFull(2));
        spellTrapField.deleteAndDestroySpellTrap((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        assertFalse(spellTrapField.isFull(2));
    }

    @Test
    void getSpellTrapCardsPositionsArray() {
        SpellTrapCard[] expectedSpellTrapCards = new SpellTrapCard[5];
        expectedSpellTrapCards[0] = (SpellTrapCard) Card.getCardByName("Time Seal");
        expectedSpellTrapCards[1] = (SpellTrapCard) Card.getCardByName("Negate Attack");
        expectedSpellTrapCards[2] = null;
        expectedSpellTrapCards[3] = (SpellTrapCard) Card.getCardByName("Forest");
        expectedSpellTrapCards[4] = (SpellTrapCard) Card.getCardByName("Closed Forest");
        assertArrayEquals(expectedSpellTrapCards, spellTrapField.getSpellTrapCardsPositionsArray());
    }

    @Test
    void deleteAndDestroyAllSpellTrapCards() {
        spellTrapField.deleteAndDestroyAllSpellTrapCards();
        assertTrue(graveyard.doesCardExist("Time Seal"));
        assertTrue(graveyard.doesCardExist("Negate Attack"));
        assertTrue(graveyard.doesCardExist("Forest"));
        assertTrue(graveyard.doesCardExist("Closed Forest"));
    }

    @Test
    void getOpponentSpellTrapCard() {
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Negate Attack"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        assertNotSame((SpellTrapCard) Card.getCardByName("Solemn Warning"), spellTrapField.getOpponentSpellTrapCard(2));
        assertSame((SpellTrapCard) Card.getCardByName("Solemn Warning"), spellTrapField.getOpponentSpellTrapCard(3));
        assertNull(spellTrapField.getOpponentSpellTrapCard(5));
    }

    @Test
    void addSpellTrapCard() {
        assertFalse(spellTrapField.doesCardExist("Forest"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Forest"));
        assertTrue(spellTrapField.doesCardExist("Forest"));
    }


    @Test
    void deleteAndDestroySpellTrap() {
        assertTrue(spellTrapField.doesCardExist("Forest"));
        spellTrapField.deleteAndDestroySpellTrap((SpellTrapCard) Card.getCardByName("Forest"));
        assertFalse(spellTrapField.doesCardExist("Forest"));
        assertTrue(graveyard.doesCardExist("Forest"));
    }

    @Test
    void doesSpellTrapCardExistInField() {
        assertTrue(spellTrapField.doesSpellTrapCardExistInField((SpellTrapCard) (Card.getCardByName("Negate Attack")).clone()));
        assertFalse(spellTrapField.doesSpellTrapCardExistInField((SpellTrapCard) (Card.getCardByName("Trap Hole")).clone()));
    }

    @Test
    void isThisCellOfOpponentSpellTrapFieldEmpty() {
        assertFalse(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(1));
        assertTrue(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(4));
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
        spellTrapField = null;
        graveyard = null;
    }

}