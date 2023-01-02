package citizenmanagementplatform;

import Exceptions.*;
import data.Nif;
import data.Password;
import data.SmallCode;

import java.net.ConnectException;
import java.util.Date;

public class CertificationAuthorityDummy implements services.CertificationAuthority {

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException {
        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException, ConnectException {
        return true;
    }

    @Override
    public boolean checkCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {
        return true;
    }
}
