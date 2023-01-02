package citizenmanagementplatform;

import Exceptions.IncorrectVerificationException;
import data.Goal;
import publicadministration.Citizen;

import java.net.ConnectException;

public class GPDDummy implements services.GPD{
    @Override
    public boolean verifyData(Citizen persData, Goal goal) throws IncorrectVerificationException, ConnectException {
        return true;
    }
}
