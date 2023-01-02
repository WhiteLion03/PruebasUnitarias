package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DocPathTest {

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> {
            DocPath docPath = new DocPath (null);
        });
    }

    @Test
    void equalsTest() {
        DocPath docPath1 = new DocPath ("/home/users/Pedro/desktop");
        DocPath docPath2 = new DocPath ("/home/users/Pedro/desktop");
        DocPath docPath3 = new DocPath ("/home/users/Pedro");
        assertEquals(docPath1, docPath2);
        assertNotEquals(docPath1, docPath3);
    }

    @Test
    void toStringTest() {
        DocPath docPath = new DocPath ("/home/users/Juan/desktop/archivos");
        System.out.println(docPath);
    }
}
