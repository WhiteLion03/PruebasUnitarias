package publicadministration;

import java.util.ArrayList;
import java.util.Date;

public class CrimConvictionsColl {

    ArrayList<CrimConviction> convictions;

    public CrimConvictionsColl() {
        convictions = new ArrayList<>();
    }

    public void addCriminalConviction (CrimConviction crmC) {
        convictions.add(crmC);
    }

    public CrimConviction getCrimConviction (Date date) {
        for(CrimConviction conviction : convictions) {
            if(conviction.getCommitDate().equals(date)) {
                return conviction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CrimConvictionsColl{" +
                "convictions=" + convictions +
                '}';
    }
}
