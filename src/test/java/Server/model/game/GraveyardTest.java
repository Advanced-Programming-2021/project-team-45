package Server.model.game;

import Server.controller.GameController;
import Server.model.Shop;
import Server.model.card.Card;
import Server.model.game.fields.Graveyard;
import Server.model.user.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraveyardTest {

    private static Graveyard graveyard;
    private static GameController gameController;
    private static Game game;
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
        assertEquals("Suijin", graveyard.getGraveyardCards().get(0).getCardName());
        assertEquals("Crab Turtle", graveyard.getGraveyardCards().get(1).getCardName());
        assertEquals("Trap Hole", graveyard.getGraveyardCards().get(2).getCardName());
    }

    @Test
    void deleteCardFromGraveyard() {
        assertTrue(graveyard.doesCardExist("Crab Turtle"));
        graveyard.deleteCardFromGraveyard(graveyard.getGraveyardCards().get(1));
        assertFalse(graveyard.doesCardExist("Crab Turtle"));
    }

    @Test
    void getCardByName() {
        assertNull(graveyard.getCardByName("Axe Raider"));
        assertEquals("Trap Hole", graveyard.getCardByName("Trap Hole").getCardName());
    }

    @Test
    void getGraveyardStr() {
        ArrayList<String> expectedGraveyardStr = new ArrayList<>();
        expectedGraveyardStr.add("1. " + "Suijin" + ":" + Card.getCardByName("Suijin").getCardDescription());
        expectedGraveyardStr.add("2. " + "Trap Hole" + ":" + Card.getCardByName("Trap Hole").getCardDescription());

        assertEquals(expectedGraveyardStr, graveyard.getGraveyardStr());
        graveyard.deleteCardFromGraveyard(graveyard.getGraveyardCards().get(0));
        graveyard.deleteCardFromGraveyard(graveyard.getGraveyardCards().get(0));

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
        assertFalse(graveyard.doesCardExist("Baby Dragon"));
        graveyard.addCardToGraveyard(Card.getCardByName("Baby Dragon"));
        assertTrue(graveyard.doesCardExist("Baby Dragon"));
    }

    @AfterAll
    public static void setAfterTest() {
        game = null;
        gameController = null;
        graveyard = null;
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("hoseein");
    }
}