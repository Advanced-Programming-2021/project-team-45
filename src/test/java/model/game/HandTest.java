package model.game;

import model.card.Card;
import model.game.fields.Hand;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private static  Hand hand = new Hand();

    @BeforeAll
    public static void beforeAll() {
        hand.addCard(Card.getCardByName("Axe Raider"));
        hand.addCard(Card.getCardByName("Battle OX"));
        hand.addCard(Card.getCardByName("Yomi Ship"));
        hand.addCard(Card.getCardByName("Horn Imp"));
    }

    @Test
    void addCard() {
        assertFalse(hand.doesCardExist("Baby Dragon"));
        hand.addCard(Card.getCardByName("Baby Dragon"));
        assertTrue(hand.doesCardExist("Baby Dragon"));
    }

    @Test
    void getCardsInHand() {
        assertEquals("Axe Raider", hand.getCardsInHand().get(0).getCardName());
        assertEquals("Battle OX", hand.getCardsInHand().get(1).getCardName());
        assertEquals("Yomi Ship", hand.getCardsInHand().get(2).getCardName());//
        assertEquals("Horn Imp", hand.getCardsInHand().get(3).getCardName());
        assertEquals("Baby Dragon", hand.getCardsInHand().get(4).getCardName());//
    }

    @Test
    void doesCardExistInThisPlace() {
        hand.deleteCardWithNumberOfIt(4);
        assertTrue(hand.doesCardExistInThesePlace(2));
        assertFalse(hand.doesCardExistInThesePlace(5));
    }

    @Test
    void deleteCardWithNumberOfIt() {
        assertTrue(hand.doesCardExist("Yomi Ship"));
        hand.deleteCardWithNumberOfIt(2);
        assertFalse(hand.doesCardExist("Yomi Ship"));
    }

    @Test
    void getCardFromHand() {
        assertEquals("Battle OX", hand.getCardFromHand(2).getCardName());
        assertNotEquals("Horn Imp", hand.getCardFromHand(1).getCardName());
    }

    @Test
    void getCardByCardName() {
        assertEquals("Axe Raider", hand.getCardByName("Axe Raider").getCardName());
    }

    @Test
    void doesCardExist() {
        assertTrue(hand.doesCardExist("Axe Raider"));
        assertTrue(hand.doesCardExist("Horn Imp"));
        assertFalse(hand.doesCardExist("Baby Dragon"));
    }

    @Test
    void deleteCard() {
        assertTrue(hand.doesCardExist("Axe Raider"));
        hand.deleteCard(hand.getCardsInHand().get(0));
        assertFalse(hand.doesCardExist("Axe Raider"));
    }



    @AfterAll
    public static void setAfterTest() {
        hand = null;
    }
}