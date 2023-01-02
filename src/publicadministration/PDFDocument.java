package publicadministration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import data.DocPath;



public class PDFDocument {
    private final Date creatDate;
    private DocPath path;
    private final File file;

    /***
     * Crea un documento PDF en la carpeta de descargas dentro de la de usuario de windows
     * @throws IOException si la ruta "c:\Users\<user.home>\Downloads" no existiera o ya existiera un archivo en dicha
     * ruta con el mismo nombre
     */
    public PDFDocument() throws IOException {
        this.path = new DocPath(System.getProperty("user.home") + "\\Downloads");
        this.file = new File(path.getPath() + "\\criminal_record_certificate.pdf");
        if (!file.createNewFile()) throw new IOException("El archivo no se ha podido crear");
        this.creatDate = new Date();
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public DocPath getPath() {
        return path;
    }

    /***
     * Mueve el documento a otra carpeta, si el documento ya existe en dicha carpeta lanza IOException
     * @param destPath ruta de destino
     * @return true si se ha podido mover
     * @throws IOException si el archivo a mover no existe, la ruta de destino no existe o no es directorio i la ruta de
     * destino ya contiene un archivo con el mismo nombre.
     */
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

    /***
     * Abre el documento
     * @throws IOException si no puede abrirlo porque no existe
     */
    public void openDoc() throws IOException {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
