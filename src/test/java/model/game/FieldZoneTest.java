package model.game;

import model.card.Card;
import model.game.fields.FieldZone;
import model.game.fields.Graveyard;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class FieldZoneTest {
    private Graveyard graveyard;
    private FieldZone fieldZone;
    @BeforeAll
    public void set() {
        this.graveyard = new Graveyard();
        this.fieldZone = new FieldZone(this.graveyard);
    }

    @Test
    void isFull() {
        assertFalse(this.fieldZone.isFull());
        this.fieldZone.setFieldCard(Card.getCardByName("Forest"));
        assertTrue(this.fieldZone.isFull());
    }

    @Test
    void doesCardExist() {
        assertTrue(this.fieldZone.doesCardExist("Forest"));
        assertFalse(this.fieldZone.doesCardExist("Closed Forest"));
    }

    @Test
    void getFieldCard() {
        assertSame(Card.getCardByName("Forest"), this.fieldZone.getFieldCard());
    }

    @Test
    void setFieldCard() {
        this.fieldZone.setFieldCard(Card.getCardByName("Closed Forest"));
        assertTrue(this.fieldZone.doesCardExist("Closed Forest"));
        assertTrue(this.graveyard.doesCardExist("Forest"));

    }


    @AfterAll
    public void setAfterTest() {
        this.fieldZone = null;
        this.graveyard = null;
    }

}