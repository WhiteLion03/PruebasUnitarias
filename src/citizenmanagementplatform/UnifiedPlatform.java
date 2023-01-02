package citizenmanagementplatform;

import data.*;
import services.*;
import publicadministration.*;
import Exceptions.*;
import data.Goal;
import data.Nif;
import data.SmallCode;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import services.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.security.PrivateKey;
import java.util.Date;

public class UnifiedPlatform {
    // The class members
    private enum Menu {
        MAIN_PAGE, JUSTICE_MINISTRY, JUSTICE_MINISTRY_PROCEDURES, OBTAIN_PENAL_CERTIFICATE
    }
    private Menu menu = Menu.MAIN_PAGE;
    private enum AuthenticateOption {
        NONE, CLAVE_PIN
    }
    private AuthenticateOption authOp = AuthenticateOption.NONE;
    private CertificationAuthority certAuth;
    private GPD gpd;
    private CAS cas;
    private Nif nif;
    private Citizen citizen;
    private Goal goal;
    private CardPayment payment;

    // The constructor
    public UnifiedPlatform() throws IOException {
        System.out.println("""
                Bienvenido al menú principal de la aplicación 'Plataforma Unificada de Gestión Ciudadana'
                Selecciona una Administración:
                1. Ministerio de Justicia
                2. Seguridad Social (No disponible)
                3. Ministerio de Trabajo (No disponible)""");
        byte selection = (byte) System.in.read();
        if (selection == 1) {
            selectJusMin();
        } else {
            throw new IOException("Administración no disponible");
        }
    }

    // Input events
    private AuthenticateOption getAuthenticateOption() {
        return authOp;
    }

    public void selectJusMin() throws IOException {
        this.menu = Menu.JUSTICE_MINISTRY;
        System.out.println("""
                Has entrado en la sección 'Ministerio de justicia'
                Selecciona una sección:
                1. Trámites""");
        byte selection = (byte) System.in.read();
        if (selection == 1) {
            selectProcedures();
        } else {
            throw new IOException("Sección no disponible");
        }
    }

    public void selectProcedures() throws IOException {
        this.menu = Menu.JUSTICE_MINISTRY_PROCEDURES;
        System.out.println("""
                Has entrado en la sección 'Trámites' del Ministerio de Justicia
                Selecciona un trámite:
                1. Obtener certificado de antecedentes penales""");
        byte selection = (byte) System.in.read();
        if (selection == 1) {
            selectCriminalReportCertificate();
        } else {
            throw new IOException("Trámite no disponible");
        }
    }

    public void selectCriminalReportCertificate() throws IOException {
        this.menu = Menu.OBTAIN_PENAL_CERTIFICATE;
        System.out.println("""
                Has seleccionado el trámite 'Obtener certificado de antecedentes penales'
                Selecciona una opción de identificación:
                1. Cl@ve PIN
                2. Cl@ve permanente""");
        byte selection = (byte) System.in.read();
        selectAuthMethod(selection);
    }

    public void selectAuthMethod(byte opc) throws IOException {
        if (opc == 1) {
            this.authOp = AuthenticateOption.CLAVE_PIN;
            System.out.println("Método cl@ve PIN seleccionado. Entre su NIF y recibirá un código PIN en su teléfono");
        } else {
            throw new IOException("Método de autenticación no disponible");
        }
    }

    public void enterNIFAndPINObt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException, ProceduralException {
        if (this.menu != Menu.OBTAIN_PENAL_CERTIFICATE || this.authOp != AuthenticateOption.CLAVE_PIN) {
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
        try {
            if (certAuth.sendPIN(nif, valDate)) {
                this.nif = nif;
            } else {
                throw new ConnectException("Ha habido un error en enviar el PIN");
            }
        } catch (ConnectException e) {
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        } catch (NifNotRegisteredException e) {
            throw new NifNotRegisteredException("No estás registrado en el sistema Cl@ve PIN");
        } catch (IncorrectValDateException e) {
            throw new IncorrectValDateException("Su NIF y fecha de vàlidez no corresponden");
        } catch (AnyMobileRegisteredException e) {
            throw new AnyMobileRegisteredException("No ha registrado su número de teléfono");
        }
    }

    public void enterPIN(SmallCode pin) throws NotValidPINException,
            ConnectException, ProceduralException {
        if (this.menu != Menu.OBTAIN_PENAL_CERTIFICATE || this.authOp != AuthenticateOption.CLAVE_PIN) {
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
        try {
            if (!certAuth.checkPIN(this.nif, pin)) {
                throw new NotValidPINException("El PIN introducido no es correcto o ha expirado");
            }
        } catch (ConnectException e) {
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        } catch (NotValidPINException e) {
            throw new NotValidPINException("El PIN introducido no es correcto o ha expirado");
        }
    }

    private void enterForm(Citizen citz, Goal goal) throws IncompleteFormException, IncorrectVerificationException, ConnectException {
        try{
            if(citz == null || goal == null){
                throw new IncompleteFormException("El formulario no está completo");
            }else if (!gpd.verifyData(citz, goal)){
                throw new IncorrectVerificationException("La información no es correcta");
            }else{
                this.citizen = citz;
                this.goal = goal;
            }
        }catch(ConnectException e){
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        }
    }

    public void realizePayment() {

    }

    private void enterCardData(CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException,
            InsufficientBalanceException, ConnectException {
        try{
            if(cardD == null){
                throw new IncompleteFormException("El formulario no está completo");
            }
            if (cas.askForApproval("no se", cardD, new Date(), new BigDecimal(1))) {
                this.payment = new CardPayment(this.nif, new BigDecimal(1));
            } else {
                throw new ConnectException("Ha habido un error comprobando el pago");
            }
        }catch (ConnectException e) {
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
        } catch (NotCorrectFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public void obtainCertificate() throws BadPathException,
            DigitalSignatureException, ConnectException {

    }

    public void printDocument() throws BadPathException,
            PrintingException {

    }

    private void registerPayment() {

    }

    private void openDocument(DocPath path) throws BadPathException {

    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {

    }

     */
}
