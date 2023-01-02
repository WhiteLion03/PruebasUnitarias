package services;

import Exceptions.AnyMobileRegisteredException;
import Exceptions.IncorrectValDateException;
import Exceptions.NifNotRegisteredException;
import Exceptions.NotValidPINException;
import data.Nif;
import data.SmallCode;

import java.net.ConnectException;
import java.util.Date;


public interface    CertificationAuthority {

    boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException,
            IncorrectValDateException, AnyMobileRegisteredException,
            ConnectException;

    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException,
            ConnectException;

}
