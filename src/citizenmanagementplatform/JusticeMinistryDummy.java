package citizenmanagementplatform;

import Exceptions.DigitalSignatureException;
import Exceptions.NotCorrectFormatException;
import data.DigitalSignature;
import data.Goal;
import data.Nif;
import data.goalTypes;
import publicadministration.Citizen;
import publicadministration.CrimeConviction;
import publicadministration.CrimeConvictionsColl;
import publicadministration.CriminalRecordCertificate;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

public class JusticeMinistryDummy implements services.JusticeMinistry {

    @Override
    public CriminalRecordCertificate getCriminalRecordCertificate(Citizen persD, Goal g) throws DigitalSignatureException, ConnectException {
        byte[] bytes = {1,2};
        try{
            CrimeConvictionsColl ccc = new CrimeConvictionsColl();
            Date date = new Date();
            CrimeConviction cc1 = new CrimeConviction(date, "Robo 8 botigues", "Presó 4 anys");
            ccc.addCriminalConviction(cc1);
            byte[] bytes1 = new byte[]{0, 20, 4, 8};
            CriminalRecordCertificate cr = new CriminalRecordCertificate(new Nif("12345678V"), "Maria", new Goal(goalTypes.PUBLIC_WORKERS), new DigitalSignature(bytes1), ccc);
            return cr;
        }catch(NotCorrectFormatException | IOException e){
            System.out.println(e);
            return null;
        }
    }
}
