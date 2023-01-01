package services;

import publicadministration.Citizen;
import data.Goal;
import publicadministration.CriminalRecordCertificate;

import java.net.ConnectException;

public interface JusticeMinistry {
    CriminalRecordCertificate getCriminalRecordCertificate(Citizen persD, Goal g) throws DigitalSignatureException,
            ConnectException;
}
