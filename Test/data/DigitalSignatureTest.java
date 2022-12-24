package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureTest {

    @Test
    void testConstructor() {
        assertThrows(NullPointerException.class, () -> {
            DigitalSignature digitalSignature = new DigitalSignature (null);
        });
    }

    @Test
    void testEquals() {
        byte[] bytes1 = {0,20,4,8};
        byte[] bytes2 = {0,20,4,10};
        byte[] bytes3 = {0,20,4};
        DigitalSignature digitalSignature1 = new DigitalSignature (bytes1);
        DigitalSignature digitalSignature2 = new DigitalSignature (bytes1);
        DigitalSignature digitalSignature3 = new DigitalSignature (bytes2);
        DigitalSignature digitalSignature4 = new DigitalSignature (bytes3);
        assertEquals(digitalSignature1, digitalSignature2);
        assertNotEquals(digitalSignature1, digitalSignature3);
        assertNotEquals(digitalSignature1, digitalSignature4);
    }

    @Test
    void testToString() {
        byte[] bytes = {0,20,4};
        DigitalSignature digitalSignature = new DigitalSignature(bytes);
        System.out.println(digitalSignature);
    }
}
