package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NifTest {

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> {
            Nif nif = new Nif (null);
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif = new Nif ("655G");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif = new Nif ("402352348");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif = new Nif ("40235a34F");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif = new Nif ("40235234f");
        });
    }

    @Test
    void equalsTest() throws NotCorrectFormatException {
        Nif nif1 = new Nif ("40235234F");
        Nif nif2 = new Nif ("40235234F");
        Nif nif3 = new Nif ("40235234C");
        Nif nif4 = new Nif ("39235234F");
        assertEquals(nif1, nif2);
        assertNotEquals(nif1, nif3);
        assertNotEquals(nif1, nif4);
    }

    @Test
    void toStringTest() throws NotCorrectFormatException {
        Nif nif = new Nif ("40235234F");
        System.out.println(nif);
    }
}