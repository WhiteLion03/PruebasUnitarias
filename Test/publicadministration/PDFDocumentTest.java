package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PDFDocumentTest {

    @Test
    void newDoc() throws IOException {
        PDFDocument doc = new PDFDocument();
        doc.moveDoc(new DocPath("Desktop/document.pdf"));
    }
}