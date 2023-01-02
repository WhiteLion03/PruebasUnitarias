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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrimeConvictionsColl)) return false;

        CrimeConvictionsColl that = (CrimeConvictionsColl) o;

        return crimeConvictions != null ? crimeConvictions.equals(that.crimeConvictions) : that.crimeConvictions == null;
    }

    @Override
    public int hashCode() {
        return crimeConvictions != null ? crimeConvictions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CrimeConvictionsColl{" +
                "convictions=" + crimeConvictions +
                '}';
    }
}
