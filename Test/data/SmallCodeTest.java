package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SmallCodeTest {

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> {
            SmallCode smallCode = new SmallCode (null);
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            SmallCode smallCode = new SmallCode ("63");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            SmallCode smallCode = new SmallCode ("402352348");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            SmallCode smallCode = new SmallCode ("fjk");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            SmallCode smallCode = new SmallCode ("38c");
        });
    }

    @Test
    void equalsTest() throws NotCorrectFormatException {
        SmallCode smallCode1 = new SmallCode ("376");
        SmallCode smallCode2 = new SmallCode ("376");
        SmallCode smallCode3 = new SmallCode ("379");
        assertEquals(smallCode1, smallCode2);
        assertNotEquals(smallCode1, smallCode3);
    }

    @Test
    void toStringTest() throws NotCorrectFormatException {
        SmallCode smallCode = new SmallCode ("523");
        System.out.println(smallCode);
    }
}
