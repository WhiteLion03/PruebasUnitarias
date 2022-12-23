package publicadministration;

import data.Nif;

import java.math.BigDecimal;
import java.util.Date;

public class CardPayment {

    private final String reference;
    private final Nif nif;
    private final Date date;
    private final BigDecimal importe;

    public CardPayment (Nif nif, BigDecimal imp) {
        this.nif = nif;
        this.importe = imp;
        this.reference = "He de mirar com és fa això";
        this.date = getDate();
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
