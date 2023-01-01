package services;

import Exceptions.DigitalSignatureException;
import publicadministration.Citizen;
import data.Goal;
import publicadministration.CriminalRecordCertf;

import java.net.ConnectException;

public interface JusticeMinistry {
    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g) throws DigitalSignatureException, ConnectException;
}
