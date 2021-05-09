package model.game;

import model.card.Card;
import model.game.fields.Graveyard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraveyardTest {

    Graveyard graveyard = new Graveyard();

    @BeforeEach
    public void set() {
        this.graveyard.addCardToGraveyard(Card.getCardByName("Suijin"));
        this.graveyard.addCardToGraveyard(Card.getCardByName("Crab Turtle"));
        this.graveyard.addCardToGraveyard(Card.getCardByName("Trap Hole"));
    }

    @Test
    void getGraveyardCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Card.getCardByName("Suijin"));
        cards.add(Card.getCardByName("Crab Turtle"));
        cards.add(Card.getCardByName("Trap Hole"));
        assertEquals(cards, this.graveyard.getGraveyardCards());
    }

    @Test
    void doesCardExist() {
        assertFalse(this.graveyard.doesCardExist("Axe Raider"));
        assertTrue(this.graveyard.doesCardExist("Trap Hole"));
    }

    @Test
    void getCardByName() {
        assertNull(this.graveyard.getCardByName("Axe Raider"));
        assertSame(Card.getCardByName("Crab Turtle"), this.graveyard.getCardByName("Crab Turtle"));
    }

    @Test
    void addCardToGraveyard() {
        assertFalse(this.graveyard.doesCardExist("Axe Raider"));
        this.graveyard.addCardToGraveyard(Card.getCardByName("Axe Raider"));
        assertTrue(this.graveyard.doesCardExist("Axe Raider"));
    }

    @Test
    void deleteCardFromGraveyard() {

    }

    @Test
    void getGraveyardStr() {

    }

    @Test
    void toStringTest() {

    }

    @AfterEach
    void setAfterTest() {

    }
}