package publicadministration;

import Exceptions.NotCorrectFormatException;
import data.DigitalSignature;
import data.Goal;
import data.Nif;
import data.goalTypes;
import java.io.IOException;
import java.util.Date;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriminalRecordCertificateTest {

    @Test
    void constructorSentenceTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new CriminalRecordCertificate(new Nif("12345678V"), null, new Goal(goalTypes.PUBLIC_WORKERS), new DigitalSignature(new byte[]{0, 20, 4, 8}), new CrimeConvictionsColl());
        });
    }

    @Test
    void CriminalRecordCertifTest() throws NotCorrectFormatException, IOException {

        CrimeConvictionsColl ccc = new CrimeConvictionsColl();
        Date date = new Date();
        CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
        ccc.addCriminalConviction(cc1);
        byte[] bytes1 = new byte[]{0, 20, 4, 8};
        CriminalRecordCertificate cr = new CriminalRecordCertificate(new Nif("12345678V"), "Maria", new Goal(goalTypes.PUBLIC_WORKERS), new DigitalSignature(bytes1), ccc);

        assertEquals(new Nif("12345678V"), cr.getNif());
        assertEquals("Maria", cr.getName());
        assertEquals(new Goal(goalTypes.PUBLIC_WORKERS), cr.getGoal());
        assertEquals(new DigitalSignature(bytes1), cr.getDigSign());
        assertEquals(ccc, cr.getCrimeConvictions());
    }
}