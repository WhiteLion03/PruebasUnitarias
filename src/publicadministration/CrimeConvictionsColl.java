package publicadministration;

import java.util.ArrayList;
import java.util.Date;

public class CrimeConvictionsColl {

    ArrayList<CrimeConviction> crimeConvictions;

    public CrimeConvictionsColl() {
        crimeConvictions = new ArrayList<>();
    }

    public void addCriminalConviction(CrimeConviction crmC) {
        crimeConvictions.add(crmC);
    }

    public CrimeConviction getCrimeConviction(Date date) {
        for(CrimeConviction conviction : crimeConvictions) {
            if(conviction.getCommitDate().equals(date)) {
                return conviction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CrimeConvictionsColl{" +
                "convictions=" + crimeConvictions +
                '}';
    }
}
