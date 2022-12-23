package data;

import java.util.NoSuchElementException;

public class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;

    public Nif (String code) throws NoSuchElementException, NotCorrectNifException {
        if (code == null) {
            throw new NoSuchElementException("El parámetro es null");
        } else if (verifyCode(code)) {
            this.nif = code;
        } else {
            throw new NotCorrectNifException("El formato del nif no és correcto");
        }
    }

    private boolean verifyCode(String code) {
        return code.length() == 9 && isNifFormat(code);
    }

    private boolean isNifFormat(String code) {
        for (int i = 0; i < 9; i++) {
            if (i < 8) {
                if (!Character.isDigit(code.charAt(i))) return false;
            } else {
                if (!Character.isUpperCase(code.charAt(i))) return false;
            }
        }
        return true;
    }

    public String getNif () { return this.nif; }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif niff = (Nif) o;

        return nif.equals(niff.nif);
    }

    @Override
    public int hashCode () { return nif.hashCode(); }

    @Override
    public String toString () {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }
}
