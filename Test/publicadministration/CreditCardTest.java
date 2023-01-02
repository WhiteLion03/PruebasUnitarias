package publicadministration;

import data.Nif;
import Exceptions.NotCorrectFormatException;
import data.SmallCode;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void constructorTest() {
        assertThrows(NotCorrectFormatException.class, () -> {
            new CreditCard(new Nif("12345678G"), "4111111", new Date(2022 - 1900, 11, 31),
                    new SmallCode("345"));
        });
        assertThrows(NotCorrectFormatException.class, () -> {
            new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2022 - 1900, 11,
                    31), new SmallCode("345"));
        });
    }

    @Test
    void getNumberTest() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2023 - 1900,
                11, 31), new SmallCode("345"));
        assertEquals("4111111111111111", cc.getCardNumb());
    }

    @Test
    void getExpirationDateTest() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2023 - 1900,
                11, 31), new SmallCode("345"));
        assertEquals(new Date(2023 - 1900, 11, 31), cc.getExpirDate());
    }

    @Test
    void getSvcTest() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2023 - 1900,
                11, 31), new SmallCode("345"));
        assertEquals(new SmallCode("345"), cc.getSvc());
    }

    @Test
    void toStringTest() throws NotCorrectFormatException {
        CreditCard cc = new CreditCard(new Nif("12345678G"), "4111111111111111", new Date(2023 - 1900,
                11, 31), new SmallCode("345"));
        System.out.println(cc);
    }
}
