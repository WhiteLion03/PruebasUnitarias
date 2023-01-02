package citizenmanagementplatform;

import Exceptions.DigitalSignatureException;
import Exceptions.NotCorrectFormatException;
import data.DigitalSignature;
import data.Goal;
import data.Nif;
import data.goalTypes;
import publicadministration.Citizen;
import publicadministration.CrimeConvictionsColl;
import publicadministration.CriminalRecordCertificate;

import java.io.IOException;
import java.net.ConnectException;

public class JusticeMinistryDummy implements services.JusticeMinistry {

    @Override
    public CriminalRecordCertificate getCriminalRecordCertificate(Citizen persD, Goal g) throws DigitalSignatureException, ConnectException {
        byte[] bytes = {1,2};
        try{
            return new CriminalRecordCertificate(new Nif("12345678A"),"Pedro", new Goal(goalTypes.GAME_SECTOR), new DigitalSignature(bytes), new CrimeConvictionsColl());
        }catch(NotCorrectFormatException | IOException e){
            System.out.println(e);
            return null;
        }
    }
}
