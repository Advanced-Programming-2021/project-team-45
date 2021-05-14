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
        assertFalse(hand.doesCardExist("Baby dragon"));
        hand.addCard(Card.getCardByName("Baby dragon"));
        assertTrue(hand.doesCardExist("Baby dragon"));
    }

    @Test
    void getCardsInHand() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Card.getCardByName("Axe Raider"));
        cards.add(Card.getCardByName("Battle OX"));
        cards.add(Card.getCardByName("Yomi Ship"));
        cards.add(Card.getCardByName("Horn Imp"));
        cards.add(Card.getCardByName("Baby dragon"));
        assertEquals(cards, hand.getCardsInHand());
    }

    @Test
    void doesCardExistInThisPlace() {
        // exist bug in business code
        // when we remove a card the place must be full by null
        hand.deleteCardWithNumberOfIt(4);
        assertTrue(hand.doesCardExistInThesePlace(1));
        assertFalse(hand.doesCardExistInThesePlace(4));
    }

    @Test
    void deleteCardWithNumberOfIt() {
        assertTrue(hand.doesCardExist("Yomi Ship"));
        hand.deleteCardWithNumberOfIt(2);
        assertFalse(hand.doesCardExist("Yomi Ship"));
    }

    @Test
    void getCardFromHand() {
        assertSame(Card.getCardByName("Battle OX"), hand.getCardFromHand(2));
        assertNotSame(Card.getCardByName("Horn Imp"), hand.getCardFromHand(1));
    }

    @Test
    void getCardByCardName() {
        assertSame(Card.getCardByName("Axe Raider"), hand.getCardByName("Axe Raider"));
        assertNotSame(Card.getCardByName("Horn Imp"), hand.getCardByName("Axe Raider"));
    }

    @Test
    void doesCardExist() {
        assertTrue(hand.doesCardExist("Axe Raider"));
        assertTrue(hand.doesCardExist("Horn Imp"));
        assertFalse(hand.doesCardExist("Baby dragon"));
    }

    @Test
    void deleteCard() {
        assertTrue(hand.doesCardExist("Axe Raider"));
        hand.deleteCard(Card.getCardByName("Axe Raider"));
        assertFalse(hand.doesCardExist("Axe Raider"));
    }



    @AfterAll
    public static void setAfterTest() {
        hand = null;
    }
}