package services;

import Exceptions.*;
import data.Nif;
import data.Password;
import data.SmallCode;

import java.net.ConnectException;
import java.util.Date;


public interface CertificationAuthority {

    boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException,
            IncorrectValDateException, AnyMobileRegisteredException,
            ConnectException;

    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException,
            ConnectException;

    boolean checkCredent(Nif nif, Password passw)  throws
            NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException;


}
