package model.user;


import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardInventoryTest {
    @BeforeEach
    static void set() {
        User user = new User("amir", "1380", "hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        user.getUserDeck().createDeck("me", user);
        user.getUserDeck().addCardToDeck("me", "Yomi Ship", false, user);
    }

    @Test
    void addCardToCardInventory(){
        User.getUserByUsername("amir").getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        assertEquals(1, User.getUserByUsername("amir").getCardInventory().cardsCount.get("Battle OX"));
    }

    @Test
    void getCardCount() {
        User.getUserByUsername("amir").getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        User.getUserByUsername("amir").getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        assertEquals(2, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
    }
    @Test
    void doesCardExistToAddToDeck() {
        User user = User.getUserByUsername("amir");
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Yomi Ship"));
    }

    @Test
    void getCardByCardName() {
        Card card = (Card.getCardByName("Battle OX")).clone();
        assertSame(card, User.getUserByUsername("amir").getCardInventory().getCardByCardName("Battle OX"));
    }
}