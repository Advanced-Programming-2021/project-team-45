package model.game;

import model.card.Card;
import model.game.fields.Hand;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand = new Hand();

    @BeforeAll
    void beforeAll() {
        this.hand.addCard(Card.getCardByName("Axe Raider"));
        this.hand.addCard(Card.getCardByName("Battle OX"));
        this.hand.addCard(Card.getCardByName("Yomi Ship"));
        this.hand.addCard(Card.getCardByName("Horn Imp"));
        this.hand.addCard(Card.getCardByName("Fireyarou"));
    }

    @Test
    void doesCardExist() {
        assertTrue(this.hand.doesCardExist("Yomi Ship"));
        assertTrue(this.hand.doesCardExist("Horn Imp"));
        assertFalse(this.hand.doesCardExist("Baby dragon"));
    }

    @Test
    void getCardByCardName() {
        assertSame(Card.getCardByName("Fireyarou"), this.hand.getCardByName("Fireyarou"));
        assertNotSame(Card.getCardByName("Horn Imp"), this.hand.getCardByName("Axe Raider"));
    }

    @Test
    void getCardsInHand() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Card.getCardByName("Axe Raider"));
        cards.add(Card.getCardByName("Battle OX"));
        cards.add(Card.getCardByName("Yomi Ship"));
        cards.add(Card.getCardByName("Horn Imp"));
        cards.add(Card.getCardByName("Fireyarou"));
        assertEquals(cards, this.hand.getCardsInHand());
    }

    @Test
    void addCard() {
        assertFalse(this.hand.doesCardExist("Baby dragon"));
        this.hand.addCard(Card.getCardByName("Baby Dragon"));
        assertTrue(this.hand.doesCardExist("Baby dragon"));
    }

    @Test
    void deleteCard() {
        assertTrue(this.hand.doesCardExist("Fireyarou"));
        this.hand.deleteCard(Card.getCardByName("Horn Imp"));
        assertFalse(this.hand.doesCardExist("Fireyarou"));
    }

    @Test
    void doesCardExistInThisPlace() {
        assertTrue(this.hand.doesCardExistInThesePlace(1));
        assertFalse(this.hand.doesCardExistInThesePlace(5));
    }

    @Test
    void getCardFromHand() {
        assertSame(Card.getCardByName("Battle OX"), this.hand.getCardFromHand(2));
        assertNotSame(Card.getCardByName("Horn Imp"), this.hand.getCardFromHand(3));
    }

    @Test
    void deleteCardWithNumberOfIt() {
        assertTrue(this.hand.doesCardExist("Yomi Ship"));
        this.hand.deleteCardWithNumberOfIt(3);
        assertFalse(this.hand.doesCardExist("Yomi Ship"));
    }

    @AfterAll
    void setAfterTest() {
        this.hand = null;
    }
}