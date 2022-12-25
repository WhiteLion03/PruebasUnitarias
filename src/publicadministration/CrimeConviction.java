package publicadministration;

import java.util.Date;

public class CrimeConviction {
    private final Date commitDate;
    private final String offense;
    private final String sentence;

    public CrimeConviction(Date commit, String off, String sent) {
        this.commitDate = commit;
        this.offense = off;
        this.sentence = sent;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public String getOffense() {
        return offense;
    }

    public String getSentence() {
        return sentence;
    }

    @Override
    public String toString() {
        return "CrimConviction{" +
                "commitDate=" + commitDate +
                ", offense='" + offense + '\'' +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}
