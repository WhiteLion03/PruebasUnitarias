package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {

    @Test
    void testConstructor() {
        assertThrows(NullPointerException.class, () -> {
            Nif nif1 = new Nif (null);
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif1 = new Nif ("655G");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif1 = new Nif ("402352348");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif1 = new Nif ("40235a34F");
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            Nif nif1 = new Nif ("40235234f");
        });
    }

    @Test
    void testEquals() throws NotCorrectFormatException {
        Nif nif1 = new Nif ("40235234F");
        Nif nif2 = new Nif ("40235234F");
        Nif nif3 = new Nif ("40235234C");
        assertEquals(nif1, nif2);
        assertNotEquals(nif1, nif3);
    }

    @Test
    void testToString() throws NotCorrectFormatException {
        Nif nif1 = new Nif ("40235234F");
        System.out.println(nif1);
    }
}