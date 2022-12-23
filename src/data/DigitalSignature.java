package data;

import java.util.Arrays;

public class DigitalSignature {
    private final byte[] signature;

    public DigitalSignature(byte[] code) {
        if (code == null) throw new NullPointerException();
        this.signature = code;
    }

    public byte[] getSignature() {
        return signature;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature digitalSignature = (DigitalSignature) o;
        return Arrays.equals(signature, digitalSignature.signature);
    }

    @Override
    public int hashCode () { return Arrays.hashCode(signature); }

    @Override
    public String toString() {
        return "DigitalSignature{" + "firma digital='" + Arrays.toString(signature) + '\'' + '}';
    }
}
