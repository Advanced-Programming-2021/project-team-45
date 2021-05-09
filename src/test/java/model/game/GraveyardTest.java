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
        assertTrue(this.graveyard.doesCardExist("Axe Raider"));
        this.graveyard.deleteCardFromGraveyard(Card.getCardByName("Axe Raider"));
        assertFalse(this.graveyard.doesCardExist("Axe Raider"));
    }

    @Test
    void getGraveyardStr() {
        ArrayList<String> expectedGraveyardStr = new ArrayList<>();
        expectedGraveyardStr.add("1." + "Suijin" + ":" + Card.getCardByName("Suijin").getCardDescription());
        expectedGraveyardStr.add("2." + "Crab Turtle" + ":" + Card.getCardByName("Crab Turtle").getCardDescription());
        expectedGraveyardStr.add("3." + "Trap Hole" + ":" + Card.getCardByName("Trap Hole").getCardDescription());

        assertEquals(expectedGraveyardStr, this.graveyard.getGraveyardStr());
        this.graveyard.deleteCardFromGraveyard(Card.getCardByName("Suijin"));
        this.graveyard.deleteCardFromGraveyard(Card.getCardByName("Crab Turtle"));
        this.graveyard.deleteCardFromGraveyard(Card.getCardByName("Trap Hole"));

        ArrayList<String> secondExpectedGraveyardStr = new ArrayList<>();
        secondExpectedGraveyardStr.add("graveyard empty");

        assertEquals(secondExpectedGraveyardStr, this.graveyard.getGraveyardStr());
    }


    @AfterEach
    void setAfterTest() {
        this.graveyard = null;
    }
}