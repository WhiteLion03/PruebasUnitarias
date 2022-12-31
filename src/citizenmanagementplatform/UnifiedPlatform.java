package citizenmanagementplatform;

import data.Nif;

import java.util.Date;

public class UnifiedPlatform {
    // The class members
    private Nif nif;
    private Date valDate;
    private byte selectedOption;

    UnifiedPlatform (Nif nif, Date valDate) {
        this.nif = nif;
        this.valDate = valDate;
    }

    UnifiedPlatform (Nif nif) {
        this.nif = nif;
    }

    // Input events
    public void selectJusMin () { System.out.println("Has entrat a la secció 'Ministeri de justícia'"); };
    public void selectProcedures () { System.out.println("Has entrat a la secció 'Tràmits' del Ministeri de justícia"); };
    public void selectCriminalReportCertf () { "Has seleccionat el tràmit 'Obtenir certificat d'antecedents penals'" };
    public void selectAuthMethod (byte opc) {
        this.selectedOption = opc;
    }
    public void enterNIFandPINobt (Nif nif, Date valDate) { . . . } throws
    NifNotRegisteredException, IncorrectValDateException,
    AnyMobileRegisteredException, ConnectException;
    public void enterPIN (SmallCode pin) { . . . } throws NotValidPINException,
    ConnectException;
    private void enterForm (Citizen citz, Goal goal) { . . . }
throws IncompleteFormException, IncorrectVerificationException,
    ConnectException;
    private void realizePayment () { . . . };
    private void enterCardData (CreditCard cardD) { . . . }
throws IncompleteFormException, NotValidPaymentDataException,
    InsufficientBalanceException, ConnectException;
    private void obtainCertificate () { . . . } throws BadPathException,
    DigitalSignatureException, ConnectException;
    private void printDocument () { . . . } throws BadPathException,
    PrintingException;
 (. . .) // The setter methods for injecting the dependences

         (. . .) // Other input events (not required)
    // Other internal operations (not required)
    private void registerPayment () { . . . };
    private void openDocument (DocPath path) { . . . } throws BadPathException;
    private void printDocument (DocPath path) { . . . }
throws BadPathException, PrintingException;
}
