package data;

public class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;

    public Nif (String code) throws NotCorrectFormatException {
        if (code == null) throw new NullPointerException();
        if (verifyCode(code)){
            this.nif = code;
        }else{
            throw new NotCorrectFormatException("El formato del Nif no es correcto");
        }
    }

    private boolean verifyCode(String code){
        if(code.length() != 9 || !isNumeric(code.substring(0, code.length() - 1)) || !isAlphabetic(code.charAt(code.length() - 1))){;
            return false;
        }
        return true;
    }

    private boolean isNumeric(String code){
        try{
            Integer.parseInt(code);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isAlphabetic(char code){
        // Suponemos que la letra debe estar en mayúscula
        int codeNumer = code;
        return (code >= 'A' && code <='Z');
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
