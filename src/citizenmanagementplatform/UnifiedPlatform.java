package citizenmanagementplatform;

import data.Goal;
import data.Nif;
import data.SmallCode;
import publicadministration.Citizen;
import services.*;

import java.net.ConnectException;
import java.util.Date;

public class UnifiedPlatform {
    // The class members
    private Nif nif;
    private Date valDate;
    private byte selectedOption;
    Citizen citizen;
    Goal goal;

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
    public void enterNIFandPINobt (Nif nif, Date valDate) {
        try{
            CertificationAuthority certificationAuthority = new CertificationAuthority();
            //S'ha de fer una clase per CertificationAuthority ¿?
            if (!certificationAuthority.sendPIN(nif, valDate)){
                throw new AnyMobileRegisteredException("No estás registrado en el sistema Cl@ve PIN");
            }
            //Què vol dir el Nif y la fecha del ciudadano no corresponden??
        }catch(ConnectException e){
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        }
    } throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException;
    public void enterPIN (SmallCode pin) { . . . } throws NotValidPINException,
    ConnectException;
    private void enterForm (Citizen citz, Goal goal) {
        try{
            if(citz == null || goal == null){
                throw new IncompleteFormException("El formulario no está completo");
            }else if (!GPD.verifyData(citz, goal)){
                throw new IncorrectVerificationException("La información no es correcta");
            }else{
                this.citizen = citz;
                this.goal = goal;
            }
        }catch(ConnectException e){
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        }

    }
throws IncompleteFormException, IncorrectVerificationException, ConnectException;
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
