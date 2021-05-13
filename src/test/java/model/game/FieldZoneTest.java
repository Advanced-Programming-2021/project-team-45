package model.game;

import controller.GameController;
import model.card.Card;
import model.game.fields.FieldZone;
import model.game.fields.Graveyard;
import model.user.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class FieldZoneTest {
    private static Graveyard graveyard;
    private static FieldZone fieldZone;
    private static Game game;
    private static GameController gameController;
    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        User user1 = new User("hossein", "hossein", "hossein");
        user.getUserDeck().createDeck("deck1", user);
        user1.getUserDeck().createDeck("deck1", user1);
        user.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user1);
        user.getUserDeck().activateDeck("deck1");
        user1.getUserDeck().activateDeck("deck1");
        gameController = new GameController("hajji", "hossein", 3);
        game = new Game(User.getUserByUsername("hajji"), User.getUserByUsername("hossein"), 3, gameController);
        graveyard = new Graveyard(game);
        fieldZone = new FieldZone(graveyard);
    }

    @Test
    void setFieldCard() {
        fieldZone.setFieldCard(Card.getCardByName("Closed Forest"));
        assertTrue(fieldZone.doesCardExist("Closed Forest"));
        assertFalse(graveyard.doesCardExist("Forest"));

    }

    @Test
    void isFull() {
        assertTrue(fieldZone.isFull());
        fieldZone.setFieldCard(Card.getCardByName("Forest"));
    }

    @Test
    void getFieldCard() {
        assertSame(Card.getCardByName("Forest"), fieldZone.getFieldCard());
    }

    @Test
    void doesCardExist() {
        assertTrue(fieldZone.doesCardExist("Forest"));
        assertFalse(fieldZone.doesCardExist("Closed Forest"));
    }

    @AfterAll
    public static void setAfterTest() {
        fieldZone = null;
        graveyard = null;
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("hossein");
    }

}