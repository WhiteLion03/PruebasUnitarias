package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {

    @Test
    void testEquals() {
        try{
            Nif nif1 = new Nif ("40235234F");
            Nif nif2 = new Nif ("40235234F");
            Nif nif3 = new Nif ("40235234C");
            assertTrue(nif1.equals(nif2));
            assertFalse(nif1.equals(nif3));
            assert
        }catch (NotCorrectFormatException e){

        }
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {

    }
}