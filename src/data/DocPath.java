package data;

public class DocPath {
    private final String path;

    public DocPath (String code) {
        if (code == null) throw new NullPointerException("El parámetro es null");
        this.path = code;
    }

    public String getPath () { return this.path; }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath docPath = (DocPath) o;
        return this.path.equals(docPath.path);
    }

    @Override
    public int hashCode () { return path.hashCode(); }

    @Override
    public String toString () {
        return "DocPath{" + "ruta fichero='" + path + '\'' + '}';
    }
}
