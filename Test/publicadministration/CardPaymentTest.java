package publicadministration;

import data.Nif;
import Exceptions.NotCorrectFormatException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentTest {

    @Test
    void constructorTest() {
        assertThrows(NotCorrectFormatException.class, () -> {
            CardPayment card = new CardPayment(new Nif("48281063S"), new BigDecimal(0));
        });
    }

    @Test
    void toStringTest() throws NotCorrectFormatException {
        CardPayment card = new CardPayment(new Nif("48281063S"), new BigDecimal(5));
        System.out.println(card);
    }
}