package publicadministration;

import data.Nif;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class CardPayment {
    private final String reference;
    private final Nif nif;
    private final Date date;
    private final BigDecimal importe;

    // TODO falta el CardPayment.reference
    public CardPayment (Nif nif, BigDecimal imp) {
        this.nif = nif;
        this.importe = imp;
        this.reference = generateReference();
        this.date = new Date();
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

    public BigDecimal getImporte() {
        return importe;
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
                ", importe=" + importe +
                '}';
    }
}
