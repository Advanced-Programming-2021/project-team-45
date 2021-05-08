package model.card;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @BeforeAll
    public void set() {

    }

    @Test
    void getCardByName() {
        assertNull(Card.getCardByName("hajji"));
        assertNotNull(Card.getCardByName("Yomi Ship"));
        assertEquals("Yomi Ship", Card.getCardByName("Yomi Ship").getCardName());
    }

    @Test
    void getCardName() {
        assertEquals("Axe Raider", Card.getCardByName("Axe Raider"));
        assertNotEquals("Yomi Ship", Card.getCardByName("Horn Imp").getCardName());
    }

    @Test
    void getCardDescription() {
        assertEquals("A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n",
                Card.getCardByName("Horn Imp").getCardDescription());
        assertNotEquals("A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n",
                Card.getCardByName("Axe Raider").getCardDescription());
    }

    @Test
    void getPrice() {
        assertEquals(3000, Card.getCardByName("Solemn Warning").getPrice());
        assertNotEquals(1300, Card.getCardByName("Silver Fang").getPrice());
    }

    @Test
    void getOwner() {

    }

    @Test
    void showCard() {
        assertEquals("Name: Horn Imp\n" + "Level: 4\n" + "Type: Normal\n" + "ATK: 1300\n" + "DEF: 1000\n" +
                "Description: A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n",
                Card.getCardByName("Horn Imp").getCardDescription());
    }

    @AfterAll
    public void setAfterTest() {

    }
}