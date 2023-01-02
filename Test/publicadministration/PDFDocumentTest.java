package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

class PDFDocumentTest {

    @BeforeEach
    void eliminateDocument(){
        DocPath path = new DocPath(System.getProperty("user.home") + "\\Downloads");
        File file = new File(path.getPath() + "\\criminal_record_certificate.pdf");
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    void constructorTest() throws IOException {
        assertThrows(IOException.class, () -> {
            PDFDocument doc = new PDFDocument();
            PDFDocument doc2 = new PDFDocument();
        });
    }

    @Test
    void moveDocTest() throws IOException {
        PDFDocument doc = new PDFDocument();
        assertThrows(IOException.class, () -> {
            doc.moveDoc(new DocPath(System.getProperty("user.home") + "\\hbkbnoi\\"));
        });
        assertTrue(doc.moveDoc(new DocPath(System.getProperty("user.home") + "\\Documents\\")));
    }

    @Test
    void openDocTest() throws IOException {
        PDFDocument doc = new PDFDocument();
        doc.openDoc();
    }
}
