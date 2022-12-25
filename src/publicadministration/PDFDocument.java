package publicadministration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import data.DocPath;



public class PDFDocument {
    private Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument () {
        this.path = new DocPath("/download");
        this.file = new File("document.pdf");
        this.creatDate = new Date();

    }

    public void moveDoc (DocPath destPath) throws IOException{

        if (!file.exists()) {
            throw new IOException("L'arxiu no existeix.");
        }


        File dest = new File (destPath.toString());
        if (!dest.exists() || !dest.isDirectory()) {
            throw new IOException("La ruta no és vàlida perquè o no existeix, o no és un directori");

        }

        if(!file.renameTo(new File (destPath + file.getName()))) throw new IOException("No s'ha pogut moure");

        path = destPath;

    }

    public void openDoc (DocPath path) throws IOException {
        Desktop.getDesktop().open(new File(path.toString()));

       /* try {
            File path = new File ("temp\laboralLife.pdf");
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */


    }

}
