package model;

import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @BeforeAll
    public void set() {
        User user = new User("hajji", "hajji", "hajji");
        user.increaseMoney(1800);
    }

    @Test
    void doesCardExist() {

    }

    @Test
    void hasEnoughMoney() {
        Shop shop = new Shop("hajji");
        User user = User.getUserByUsername("hajji");
        assertTrue(shop.hasEnoughMoney("Yomi Ship"));
        assertFalse(shop.hasEnoughMoney("Horn Imp"));
    }

    @Test
    void buy() {
        Shop shop = new Shop("hajji");
        shop.buy("Yomi Ship");
        assertNotNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Yomi Ship"));
        assertNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Battle OX"));
    }

    @AfterAll
    public void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}