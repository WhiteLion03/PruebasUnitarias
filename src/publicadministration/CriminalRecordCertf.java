package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;

import java.io.IOException;

public class CriminalRecordCertf extends PDFDocument {

    private final Nif nif;
    private final String name;
    private final Goal goal;
    private final DigitalSignature digSign;
    private final CrimeConvictionsColl crimeConvictions;

    public CriminalRecordCertf(Nif nif, String name, Goal g, DigitalSignature ds, CrimeConvictionsColl crmC)
            throws IOException {
        super();
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
