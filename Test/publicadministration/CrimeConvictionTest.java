package publicadministration;

import data.Nif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CrimeConvictionTest {

    @Test
    void constructorTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new CrimeConviction(new Date(2002, 12, 30), null, "Presó 4 anys");
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            new CrimeConviction(new Date(2002, 12, 30), "Robatori 4 botigues", null);
        });


    }

    @Test
    void getCommitDateTest() {

        Date date = new Date();

        CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
        CrimeConviction cc2 = new CrimeConviction(new Date(date.getDate()), "Matar 4 persones", "Presó 20 anys");
        CrimeConviction cc3 = new CrimeConviction(new Date(2022, 12, 31), "Matar 4 persones", "Presó 20 anys");

        assertEquals(date, cc1.getCommitDate());
        assertEquals(new Date(date.getDate()), cc2.getCommitDate());
        assertEquals(new Date(2022, 12, 31), cc3.getCommitDate());
    }

    @Test
    void getOffenseTest() {
        Date date = new Date();

        CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
        CrimeConviction cc2 = new CrimeConviction(new Date(date.getDate()), "Matar 4 persones", "Presó 20 anys");

        assertEquals("Robo 8 botigues", cc1.getOffense());
        assertEquals("Matar 4 persones", cc2.getOffense());

    }

    @Test
    void getSentenceTest() {
        Date date = new Date();

        CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
        CrimeConviction cc2 = new CrimeConviction(new Date(date.getDate()), "Matar 4 persones", "Presó 20 anys");

        assertEquals("Presó 4 anys", cc1.getSentence());
        assertEquals("Presó 20 anys", cc2.getSentence());
    }
}