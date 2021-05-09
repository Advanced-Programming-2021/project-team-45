package model.game;

import model.card.Card;
import model.game.fields.Hand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand hand = new Hand();

    @BeforeAll
    public void beforeAll() {
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
        assertFalse(this.hand.doesCardExist("Baby Dragon"));
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
        assertFalse(this.hand.doesCardExist("Baby Dargon"));
        this.hand.addCard(Card.getCardByName("Baby Dragon"));
        assertTrue(this.hand.doesCardExist("Baby Dragon"));
    }

    @Test
    void deleteCard() {

    }

    @Test
    void doesCardExistInThisPlace() {

    }

    @Test
    void getCardFromHand() {

    }

    @Test
    void deleteCardWithNumberOfIt() {

    }
}