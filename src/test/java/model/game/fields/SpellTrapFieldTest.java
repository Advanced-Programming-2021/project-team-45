package model.game.fields;

import controller.GameController;
import model.Shop;
import model.card.Card;
import model.card.SpellTrapCard;
import model.game.Game;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SpellTrapFieldTest {

    private static SpellTrapField spellTrapField;
    private static Graveyard graveyard;
    private static GameController gameController;
    private static Game game;
    private static Shop shop1;
    private static Shop shop2;

    @BeforeAll
    public static void setBeforeTest() {
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
        spellTrapField = new SpellTrapField(user, graveyard);
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Yami"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Negate Attack"));
    }

    @Test
    void isFull() {
        assertFalse(spellTrapField.isFull());
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Forest"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Closed Forest"));
        assertTrue(spellTrapField.isFull());
    }

    @Test
    void getPlayerSpellTrapCard() {
        assertEquals("Negate Attack", spellTrapField.getPlayerSpellTrapCard(2).getCardName());
    }

    @Test
    void isThisCellOfPlayerSpellTrapFieldEmpty() {
        assertFalse(spellTrapField.isThisCellOfPlayerSpellTrapFieldEmpty(5));
    }

    @Test
    void isFullPlace() {
        assertTrue(spellTrapField.isFull(2));
        spellTrapField.deleteAndDestroySpellTrap(spellTrapField.getPlayerSpellTrapCard(3));
        assertFalse(spellTrapField.isFull(2));
    }

    @Test
    void getSpellTrapCardsPositionsArray() {
        SpellTrapCard[] expectedSpellTrapCards = new SpellTrapCard[5];
        expectedSpellTrapCards[0] = (SpellTrapCard) Card.getCardByName("Yami");
        expectedSpellTrapCards[1] = (SpellTrapCard) Card.getCardByName("Negate Attack");
        expectedSpellTrapCards[2] = null;
        expectedSpellTrapCards[3] = (SpellTrapCard) Card.getCardByName("Forest");
        expectedSpellTrapCards[4] = (SpellTrapCard) Card.getCardByName("Closed Forest");


        assertEquals("Yami", spellTrapField.getSpellTrapCardsPositionsArray()[0].getCardName());
        assertEquals("Negate Attack", spellTrapField.getSpellTrapCardsPositionsArray()[1].getCardName());
        assertNull(spellTrapField.getSpellTrapCardsPositionsArray()[2]);
        assertEquals("Forest", spellTrapField.getSpellTrapCardsPositionsArray()[3].getCardName());
        assertEquals("Closed Forest", spellTrapField.getSpellTrapCardsPositionsArray()[4].getCardName());

    }

    @Test
    void deleteAndDestroyAllSpellTrapCards() {
        spellTrapField.deleteAndDestroyAllSpellTrapCards();
        assertTrue(graveyard.doesCardExist("Yami"));
        assertTrue(graveyard.doesCardExist("Negate Attack"));
        assertTrue(graveyard.doesCardExist("Forest"));
        assertTrue(graveyard.doesCardExist("Closed Forest"));
    }

    @Test
    void getOpponentSpellTrapCard() {
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Negate Attack"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Solemn Warning"));
        assertNull(spellTrapField.getOpponentSpellTrapCard(2));
        assertEquals("Solemn Warning", spellTrapField.getOpponentSpellTrapCard(3).getCardName());
    }

    @Test
    void addSpellTrapCard() {
        assertFalse(spellTrapField.doesCardExist("Forest"));
        spellTrapField.addSpellTrapCard((SpellTrapCard) Card.getCardByName("Forest"));
        assertTrue(spellTrapField.doesCardExist("Forest"));
    }


    @Test
    void deleteAndDestroySpellTrap() {
        assertTrue(spellTrapField.doesCardExist("Forest"));
        spellTrapField.deleteAndDestroySpellTrap(spellTrapField.getPlayerSpellTrapCard(3));
        assertFalse(spellTrapField.doesCardExist("Forest"));
        assertTrue(graveyard.doesCardExist("Forest"));
    }

    @Test
    void doesSpellTrapCardExistInField() {
        assertTrue(spellTrapField.doesSpellTrapCardExistInField(((SpellTrapCard) (Card.getCardByName("Negate Attack"))).copy()));
        assertFalse(spellTrapField.doesSpellTrapCardExistInField(((SpellTrapCard) (Card.getCardByName("Trap Hole"))).copy()));
    }

    @Test
    void isThisCellOfOpponentSpellTrapFieldEmpty() {
        assertFalse(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(1));
        assertTrue(spellTrapField.isThisCellOfOpponentSpellTrapFieldEmpty(4));
    }

    @AfterAll
    public static void setAfterTest() {
        spellTrapField = null;
        game = null;
        graveyard = null;
        gameController = null;
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("hossein");
    }

}