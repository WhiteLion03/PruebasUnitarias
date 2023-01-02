package citizenmanagementplatform;

import services.*;
import publicadministration.*;
import Exceptions.*;
import data.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;

public class UnifiedPlatform {
    // The class members
    public enum Menu {
        MAIN_PAGE, JUSTICE_MINISTRY, JUSTICE_MINISTRY_PROCEDURES, OBTAIN_CRIMINAL_REPORT_CERTIFICATE
    }
    private Menu menu;
    public enum AuthenticateOption {
        NONE, CLAVE_PIN
    }
    private AuthenticateOption authOp;
    private final CertificationAuthority certAuth;
    private GPD gpd;
    private CAS cas;
    private Nif nif;
    private Citizen citizen;
    private Goal goal;
    private CardPayment payment;

    // The constructor
    public UnifiedPlatform(CertificationAuthority certAuth) {
        this.menu = Menu.MAIN_PAGE;
        this.authOp = AuthenticateOption.NONE;
        this.certAuth = certAuth;
        this.nif = null;
        this.citizen = null;
        this.goal = null;
        this.payment = null;
        System.out.println("""
                Bienvenido al menú principal de la aplicación 'Plataforma Unificada de Gestión Ciudadana'
                Seleccione una Administración:
                1. Ministerio de Justicia
                2. Seguridad Social (No disponible)
                3. Ministerio de Trabajo (No disponible)""");
    }

    // The getters
    public Menu getMenu() {
        return this.menu;
    }
    public AuthenticateOption getAuthOp() {
        return this.authOp;
    }
    public Nif getNif() {
        return nif;
    }

    // Input events
    public void selectJusMin() throws ProceduralException {
        if (this.menu != Menu.MAIN_PAGE) {
            throw new ProceduralException("No está en la página correcta para seleccionar esta opción");
        }
        this.menu = Menu.JUSTICE_MINISTRY;
        System.out.println("""
                Ha entrado en la sección 'Ministerio de justicia'
                Seleccione una sección:
                1. Trámites""");
    }

    public void selectProcedures() throws ProceduralException {
        if (this.menu != Menu.JUSTICE_MINISTRY) {
            throw new ProceduralException("No está en la página correcta para seleccionar esta opción");
        }
        this.menu = Menu.JUSTICE_MINISTRY_PROCEDURES;
        System.out.println("""
                Ha entrado en la sección 'Trámites' del Ministerio de Justicia
                Seleccione un trámite:
                1. Obtener certificado de antecedentes penales""");
    }

    public void selectCriminalReportCertificate() throws ProceduralException {
        if (this.menu != Menu.JUSTICE_MINISTRY_PROCEDURES) {
            throw new ProceduralException("No estás en la página correcta para seleccionar esta opción");
        }
        this.menu = Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE;
        System.out.println("""
                Has seleccionado el trámite 'Obtener certificado de antecedentes penales'
                Selecciona una opción de identificación:
                1. Cl@ve PIN
                2. Cl@ve permanente""");
    }

    public void selectAuthMethod(byte opc) throws ProceduralException {
        if (this.menu != Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE) {
            throw new ProceduralException("No estás en la página correcta para seleccionar esta opción");
        }
        if (opc == 1) {
            this.authOp = AuthenticateOption.CLAVE_PIN;
            System.out.println("Método cl@ve PIN seleccionado. Entre su NIF y recibirá un código PIN en su teléfono");
        } else {
            throw new ProceduralException("Método de autenticación no disponible");
        }
    }

    public void enterNIFAndPINObt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException, ProceduralException {
        if (this.menu != Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE || this.authOp != AuthenticateOption.CLAVE_PIN) {
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
        try {
            if (certAuth.sendPIN(nif, valDate)) {
                this.nif = nif;
            } else {
                throw new ProceduralException("Ha habido un error en enviar el PIN");
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
        if (this.menu != Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE || this.authOp != AuthenticateOption.CLAVE_PIN) {
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
