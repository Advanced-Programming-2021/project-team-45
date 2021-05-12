package model.game;

import model.card.Card;
import model.game.fields.FieldZone;
import model.game.fields.Graveyard;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class FieldZoneTest {
    private static Graveyard graveyard;
    private static FieldZone fieldZone;
    @BeforeAll
    public static void set() {
        graveyard = new Graveyard();
        fieldZone = new FieldZone(graveyard);
    }

    @Test
    void setFieldCard() {
        fieldZone.setFieldCard(Card.getCardByName("Closed Forest"));
        assertTrue(fieldZone.doesCardExist("Closed Forest"));
        assertFalse(graveyard.doesCardExist("Forest"));

    }

    @Test
    void isFull() {
        assertTrue(fieldZone.isFull());
        fieldZone.setFieldCard(Card.getCardByName("Forest"));
    }

    @Test
    void getFieldCard() {
        assertSame(Card.getCardByName("Forest"), fieldZone.getFieldCard());
    }

    @Test
    void doesCardExist() {
        assertTrue(fieldZone.doesCardExist("Forest"));
        assertFalse(fieldZone.doesCardExist("Closed Forest"));
    }

    @AfterAll
    public static void setAfterTest() {
        fieldZone = null;
        graveyard = null;
    }

}