package services;

import publicadministration.Citizen;
import data.Goal;
import publicadministration.CriminalRecordCertf;

import java.net.ConnectException;

public interface JusticeMinistery {
    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g) throws DigitalSignatureException, ConnectException;
}
