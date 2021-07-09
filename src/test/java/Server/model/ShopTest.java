package Server.model;

import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    private static Shop shop;
    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        shop = new Shop("hajji");
        user.increaseMoney(1800);
    }

    @Test
    void buy() {
        shop.buy("Yomi Ship");
        assertNotNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Yomi Ship"));
        assertNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Battle OX"));
    }


    @Test
    void doesCardExist() {
        assertTrue(shop.doesCardExist("Yomi Ship"));
        assertTrue(shop.doesCardExist("Horn Imp"));
    }

    @Test
    void hasEnoughMoney() {
        assertTrue(shop.hasEnoughMoney("Yomi Ship"));
    }


    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}