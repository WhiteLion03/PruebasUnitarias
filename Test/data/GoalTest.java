package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GoalTest {

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> {
            Goal goal = new Goal (null);
        });
    }

    @Test
    void equalsTest() {
        Goal goal1 = new Goal(goalTypes.PUBLIC_WORKERS);
        Goal goal2 = new Goal(goalTypes.PUBLIC_WORKERS);
        Goal goal3 = new Goal(goalTypes.PUBLIC_ADMIN_CONSORTIUM);
        assertEquals(goal1, goal2);
        assertNotEquals(goal1, goal3);
    }

    @Test
    void toStringTest() {
        Goal goal = new Goal(goalTypes.GAME_SECTOR);
        System.out.println(goal);
    }
}
