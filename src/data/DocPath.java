package data;

import javax.print.Doc;

public class DocPath {
    // Route in the file system.
    private final String docPath;

    public DocPath (String code) { this.docPath = code; }

    public String getDocPath () { return this.docPath; }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath docPath = (DocPath) o;
        return this.docPath.equals(docPath.docPath);
    }

    @Override
    public int hashCode () { return docPath.hashCode(); }

    @Override
    public String toString () {
        return "Ruta{" + "ruta fixero='" + docPath + '\'' + '}';
    }
}
