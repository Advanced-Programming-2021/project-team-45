package model.game;

import controller.GameController;
import model.Shop;
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
    private static Shop shop1;
    private static Shop shop2;
    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        User user1 = new User("hossein", "hossein", "hossein");
        shop1 = new Shop("hajji");
        shop2 = new Shop("hossein");

        shop1.buy("Battle OX");
        shop1.buy("Axe Raider");
        shop1.buy("Yomi Ship");
        shop1.buy("Horn Imp");
        shop1.buy("Silver Fang");
        shop1.buy("Suijin");
        shop1.buy("Fireyarou");

        shop2.buy("Battle OX");
        shop2.buy("Axe Raider");
        shop2.buy("Yomi Ship");
        shop2.buy("Horn Imp");
        shop2.buy("Silver Fang");
        shop2.buy("Suijin");
        shop2.buy("Fireyarou");


        user.getUserDeck().createDeck("deck1", user);
        user1.getUserDeck().createDeck("deck1", user1);

        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Suijin", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Fireyarou", false, user);

        user1.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Suijin", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Fireyarou", false, user);

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
    }

    @Test
    void isFull() {
        assertTrue(fieldZone.isFull());
        fieldZone.setFieldCard(Card.getCardByName("Forest"));
    }

    @Test
    void getFieldCard() {
        assertEquals("Forest", fieldZone.getFieldCard().getCardName());
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