package publicadministration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import data.DocPath;



public class PDFDocument {
    private final Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument() throws IOException {
        this.path = new DocPath(System.getProperty("user.home") + "\\Downloads");
        this.file = new File(path.getPath() + "\\document.pdf");
        if (!file.createNewFile()) throw new IOException("El archivo no se ha podido crear");
        this.creatDate = new Date();
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public DocPath getPath() {
        return path;
    }

    public boolean moveDoc(DocPath destPath) throws IOException{
        if (!file.exists()) {
            throw new IOException("El archivo a mover no existe");
        }

        File dest = new File(destPath.getPath());
        if (!dest.exists() || !dest.isDirectory()) {
            throw new IOException("La ruta de destino no es válida bien porqué no existe o no es un directorio");
        }

        if(!file.renameTo(new File(destPath.getPath() + file.getName())))
            throw new IOException("El archivo no se ha podido mover");

        path = destPath;
        return true;
    }

    public void openDoc() throws IOException {
        Desktop.getDesktop().open(file);

       /* try {
            File path = new File ("temp\laboralLife.pdf");
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */
    }
}
