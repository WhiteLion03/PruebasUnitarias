package publicadministration;

import data.Nif;
import data.NotCorrectFormatException;
import data.SmallCode;
import data.SmallCodeTest;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void getNumberTest() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2002, 12, 31), new SmallCode("345"));
        assertEquals("4111111111111111", cc.getCardNumb());
    }

    @Test
    void getExpirationDate() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2002, 12, 31), new SmallCode("345"));
        assertEquals(new Date(2002, 12, 31), cc.getExpirDate());
    }

    @Test
    void getSVC() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2002, 12, 31), new SmallCode("345"));
        assertEquals(new SmallCode("345"), cc.getSvc());
    }
}