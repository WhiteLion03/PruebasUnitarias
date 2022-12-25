package services;

import data.Goal;
import publicadministration.Citizen;

import java.net.ConnectException;

public interface GPD {
    boolean verifyData(Citizen persData, Goal goal)
            throws IncorrectVerificationException, ConnectException;
}
