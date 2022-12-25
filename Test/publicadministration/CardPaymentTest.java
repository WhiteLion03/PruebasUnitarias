package publicadministration;

import data.Nif;
import data.NotCorrectFormatException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentTest {

    @Test
    void testToString() throws NotCorrectFormatException {
        CardPayment card = new CardPayment(new Nif("48281063S"), new BigDecimal(5));
        System.out.println(card);
    }
}