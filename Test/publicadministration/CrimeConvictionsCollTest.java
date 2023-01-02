package publicadministration;

import org.junit.jupiter.api.Test;
import publicadministration.CrimeConviction;
import publicadministration.CrimeConvictionsColl;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;

class CrimeConvictionsCollTest {

    @Test
    void getCrimeConvictionTest() {

            CrimeConvictionsColl ccc = new CrimeConvictionsColl();
            Date date = new Date();
            Date date2 = new Date(2022, 12, 31);
            CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
            CrimeConviction cc2 = new CrimeConviction(new Date(date.getDate()), "Matar 4 persones", "Presó 20 anys");
            CrimeConviction cc3 = new CrimeConviction(new Date(2022, 12, 31), "Matar 4 persones", "Presó 20 anys");

            ccc.addCriminalConviction(cc1);
            ccc.addCriminalConviction(cc2);
            ccc.addCriminalConviction(cc3);

            assertNull(ccc.getCrimeConviction(new Date(2002, 12, 30)));
            assertEquals(cc1, ccc.getCrimeConviction(date));
            assertEquals(cc2, ccc.getCrimeConviction(new Date(date.getDate())));
            assertEquals(cc3, ccc.getCrimeConviction(date2));

    }
}