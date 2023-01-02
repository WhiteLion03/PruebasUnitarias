package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;

import java.io.IOException;

public class CriminalRecordCertificate extends PDFDocument {

    private final Nif nif;
    private final String name;
    private final Goal goal;
    private final DigitalSignature digSign;
    private final CrimeConvictionsColl crimeConvictions;

    public CriminalRecordCertificate(Nif nif, String name, Goal g, DigitalSignature ds, CrimeConvictionsColl crmC)
            throws IOException {
        super();
        if (name == null) throw new NullPointerException("Hi ha un element que és null");
        this.nif = nif;
        this.name = name;
        this.goal = g;
        this.digSign = ds;
        this.crimeConvictions = crmC;
    }

    public Nif getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public Goal getGoal() {
        return goal;
    }

    public DigitalSignature getDigSign() {
        return digSign;
    }

    public CrimeConvictionsColl getCrimeConvictions() {
        return crimeConvictions;
    }
}
