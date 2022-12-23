package data;

public class DigitalSignature {
    private final byte[] code;

    public DigitalSignature(byte[] code) {
        if (code == null) throw new NullPointerException();
        this.code = code;
    }

    public byte[] getCode(){
        return code;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature digitalSignature = (DigitalSignature) o;
        return code.equals(digitalSignature.code);
    }

    @Override
    public int hashCode () { return code.hashCode(); }
}
