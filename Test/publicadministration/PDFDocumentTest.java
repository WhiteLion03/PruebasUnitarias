package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class PDFDocumentTest {

    @Test
    void constructorTest() throws IOException {
        //PDFDocument doc = new PDFDocument();
        assertThrows(IOException.class, () -> {
            PDFDocument doc = new PDFDocument();
            PDFDocument doc2 = new PDFDocument();
        });
    }

    @Test
    void moveDocTest() throws IOException {
        PDFDocument doc = new PDFDocument();
        assertTrue(doc.moveDoc(new DocPath(System.getProperty("user.home") + "\\Documents\\")));
    }

    @Test
    void openDocTest() throws IOException {
        PDFDocument doc = new PDFDocument();
        doc.openDoc();
    }
}
