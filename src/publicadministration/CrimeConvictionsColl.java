package publicadministration;

import java.util.ArrayList;
import java.util.Date;

public class CrimeConvictionsColl {

    ArrayList<CrimeConviction> convictions;

    public CrimeConvictionsColl() {
        convictions = new ArrayList<>();
    }

    public void addCriminalConviction (CrimeConviction crmC) {
        convictions.add(crmC);
    }

    public CrimeConviction getCrimeConviction(Date date) {
        for(CrimeConviction conviction : convictions) {
            if(conviction.getCommitDate().equals(date)) {
                return conviction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CrimeConvictionsColl{" +
                "convictions=" + convictions +
                '}';
    }
}
