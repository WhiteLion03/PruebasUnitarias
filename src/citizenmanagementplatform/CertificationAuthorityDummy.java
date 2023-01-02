package citizenmanagementplatform;

import Exceptions.AnyMobileRegisteredException;
import Exceptions.IncorrectValDateException;
import Exceptions.NifNotRegisteredException;
import Exceptions.NotValidPINException;
import data.Nif;
import data.SmallCode;

import java.net.ConnectException;
import java.util.Date;

public class CertificationAuthorityDummy implements services.CertificationAuthority {

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException, ConnectException {
        return true;
    }
}
