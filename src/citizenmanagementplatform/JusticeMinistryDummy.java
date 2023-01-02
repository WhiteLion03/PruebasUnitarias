package citizenmanagementplatform;

import Exceptions.DigitalSignatureException;
import data.Goal;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertificate;

import java.net.ConnectException;

public class JusticeMinistryDummy implements services.JusticeMinistry {

    @Override
    public CriminalRecordCertificate getCriminalRecordCertificate(Citizen persD, Goal g) throws DigitalSignatureException, ConnectException {
        return null;
    }
}
