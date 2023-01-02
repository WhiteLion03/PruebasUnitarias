package publicadministration;


import data.Nif;
import Exceptions.NotCorrectFormatException;
import data.SmallCode;

import java.util.Date;

public class CreditCard {

    private final Nif nif;
    private final String cardNumb;
    private final Date expirDate;
    private final SmallCode svc;

    public CreditCard (Nif nif, String cNum, Date d, SmallCode c) throws NotCorrectFormatException {
        if (cNum == null) throw new NullPointerException("Un parámetro es null");
        this.nif = nif;
        this.svc = c;
        if (cNum.length() == 16) {
            this.cardNumb = cNum;
        } else {
            throw new NotCorrectFormatException("Número de tarjeta no válido");
        }
        if (d.compareTo(new Date()) > 0) {
            this.expirDate = d;
        } else {
            throw new NotCorrectFormatException("La tarjeta está caducada");
        }
    }

    public Nif getNif() {
        return nif;
    }

    public String getCardNumb() {
        return cardNumb;
    }

    public Date getExpirDate() {
        return expirDate;
    }

    public SmallCode getSvc() {
        return svc;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "nif=" + nif +
                ", cardNumb='" + cardNumb + '\'' +
                ", expirDate=" + expirDate +
                ", svc=" + svc +
                '}';
    }
}
