package citizenmanagementplatform;

import Exceptions.IncorrectVerificationException;
import Exceptions.InsufficientBalanceException;
import Exceptions.NotValidPaymentDataException;
import data.Goal;
import publicadministration.Citizen;
import publicadministration.CreditCard;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;

public class CASDummy implements services.CAS{
    @Override
    public boolean askForApproval(String nTrans, CreditCard cardData, Date date, BigDecimal imp) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        return true;
    }
}
