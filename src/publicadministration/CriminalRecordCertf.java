package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;

public class CriminalRecordCertf {

    private Nif nif;
    private String name;
    private Goal goal;
    private DigitalSignature digSign;
    private CrimeConvictionsColl crimeConvictions;

    public CriminalRecordCertf (Nif nif, String name, Goal g, DigitalSignature ds, CrimeConvictionsColl crmC) {
        this.nif = nif;
        this.name = name;
        this. goal = g;
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
