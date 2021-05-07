package model.user;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifepointTest {
    @BeforeAll
    public void set() {
        User user =new User("hajji", "hajji", "hajji");
    }

    @Test
    void getLifepoint(){
        assertEquals(8000, User.getUserByUsername("hajji").getLifepoint().getLifepoint());
    }

    @Test
    void setLifepoint() {
        User.getUserByUsername("hajji").getLifepoint().setLifepoint(9800);
        assertEquals(9800, User.getUserByUsername("hajji").getLifepoint().getLifepoint());
    }

    @Test
    void increaseLifepoint() {
        User.getUserByUsername("hajji").getLifepoint().increaseLifepoint(441);
        assertEquals(8441, User.getUserByUsername("hajji").getLifepoint().getLifepoint());
    }

    @Test
    void decreaseLifepoint() {
        User.getUserByUsername("hajji").getLifepoint().decreaseLifepoint(5655);
        assertEquals(2345, User.getUserByUsername("hajji").getLifepoint().getLifepoint());
    }

    @AfterAll
    public void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}