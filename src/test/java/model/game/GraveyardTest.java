package model.game;

import model.card.Card;
import model.game.fields.Graveyard;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraveyardTest {

    private static Graveyard graveyard;

    @BeforeAll
    public static void set() {
        graveyard = new Graveyard();
        graveyard.addCardToGraveyard(Card.getCardByName("Suijin"));
        graveyard.addCardToGraveyard(Card.getCardByName("Crab Turtle"));
        graveyard.addCardToGraveyard(Card.getCardByName("Trap Hole"));
    }

    @Test
    void getGraveyardCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Card.getCardByName("Suijin"));
        cards.add(Card.getCardByName("Crab Turtle"));
        cards.add(Card.getCardByName("Trap Hole"));
        assertEquals(cards, graveyard.getGraveyardCards());
    }

    @Test
    void deleteCardFromGraveyard() {
        assertTrue(graveyard.doesCardExist("Crab Turtle"));
        graveyard.deleteCardFromGraveyard(Card.getCardByName("Crab Turtle"));
        assertFalse(graveyard.doesCardExist("Crab Turtle"));
    }

    @Test
    void getCardByName() {
        assertNull(this.graveyard.getCardByName("Axe Raider"));
        assertSame(Card.getCardByName("Trap Hole"), graveyard.getCardByName("Trap Hole"));
    }

    @Test
    void getGraveyardStr() {
        ArrayList<String> expectedGraveyardStr = new ArrayList<>();
        expectedGraveyardStr.add("1. " + "Suijin" + ":" + Card.getCardByName("Suijin").getCardDescription());
        expectedGraveyardStr.add("2. " + "Trap Hole" + ":" + Card.getCardByName("Trap Hole").getCardDescription());

        assertEquals(expectedGraveyardStr, graveyard.getGraveyardStr());
        graveyard.deleteCardFromGraveyard(Card.getCardByName("Suijin"));
        graveyard.deleteCardFromGraveyard(Card.getCardByName("Trap Hole"));

        ArrayList<String> secondExpectedGraveyardStr = new ArrayList<>();
        secondExpectedGraveyardStr.add("graveyard empty");

        assertEquals(secondExpectedGraveyardStr, graveyard.getGraveyardStr());
    }

    @Test
    void doesCardExist() {
        assertFalse(graveyard.doesCardExist("Axe Raider"));
        graveyard.addCardToGraveyard(Card.getCardByName("Trap Hole"));
        assertTrue(graveyard.doesCardExist("Trap Hole"));
    }

    @Test
    void addCardToGraveyard() {
        assertFalse(graveyard.doesCardExist("Baby dragon"));
        graveyard.addCardToGraveyard(Card.getCardByName("Baby dragon"));
        assertTrue(graveyard.doesCardExist("Baby dragon"));
    }

    @AfterAll
    public static void setAfterTest() {
        graveyard = null;
    }
}