package publicadministration;

import data.Nif;
import Exceptions.NotCorrectFormatException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class CardPayment {
    private final String reference;
    private final Nif nif;
    private final Date date;
    private final BigDecimal imp;

    public CardPayment (Nif nif, BigDecimal imp) throws NotCorrectFormatException {
        this.nif = nif;
        this.reference = generateReference();
        this.date = new Date();

        if (imp.compareTo(new BigDecimal(0)) > 0) {
            this.imp = imp;
        } else {
            throw new NotCorrectFormatException("El importe es 0 o inferior, pago no válido");
        }
    }

    public String getReference() {
        return reference;
    }

    public Nif getNif() {
        return nif;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getImp() {
        return imp;
    }

    private String generateReference() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "reference='" + reference + '\'' +
                ", nif=" + nif +
                ", date=" + date +
                ", importe=" + imp +
                '}';
    }
}
