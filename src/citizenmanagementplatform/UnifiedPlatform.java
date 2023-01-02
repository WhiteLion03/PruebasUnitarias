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
    private Menu menu;
    private AuthenticateOption authOp;

    private final CertificationAuthority certAuth;
    private final JusticeMinistry justiceMinistry;
    private final GPD gpd;
    private final CAS cas;

    private CriminalRecordCertificate certificate;
    private Nif nif;
    private Citizen citizen;
    private Goal goal;
    private CardPayment payment;
    private int transfId;

    // The constructor
    public UnifiedPlatform(CertificationAuthority certAuth, JusticeMinistry justiceMinistry, GPD gpd, CAS cas) {
        this.menu = Menu.MAIN_PAGE;
        this.authOp = AuthenticateOption.NONE;
        this.certAuth = certAuth;
        this.justiceMinistry = justiceMinistry;
        this.gpd = gpd;
        this.cas = cas;
        this.nif = null;
        this.citizen = null;
        this.goal = null;
        this.payment = null;
        this.transfId = 1;
        System.out.println("""
                Bienvenido al menú principal de la aplicación 'Plataforma Unificada de Gestión Ciudadana'
                Seleccione una Administración:
                1. Ministerio de Justicia
                2. Seguridad Social (No disponible)
                3. Ministerio de Trabajo (No disponible)
                """);
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
        if (this.menu == Menu.MAIN_PAGE || this.menu == Menu.JUSTICE_MINISTRY_PROCEDURES) {
            this.menu = Menu.JUSTICE_MINISTRY;
            System.out.println("""
                Ha entrado en la sección 'Ministerio de justicia'
                Seleccione una sección:
                1. Trámites
                """);
        } else {
            throw new ProceduralException("No está en la página correcta para seleccionar esta opción");
        }
    }

    public void selectProcedures() throws ProceduralException {
        if (this.menu == Menu.JUSTICE_MINISTRY || this.menu == Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE) {
            this.menu = Menu.JUSTICE_MINISTRY_PROCEDURES;
            System.out.println("""
                Ha entrado en la sección 'Trámites' del Ministerio de Justicia
                Seleccione un trámite:
                1. Obtener certificado de antecedentes penales
                """);
        } else {
            throw new ProceduralException("No está en la página correcta para seleccionar esta opción");
        }
    }

    public void selectCriminalReportCertificate() throws ProceduralException {
        if (this.menu == Menu.JUSTICE_MINISTRY_PROCEDURES || this.menu == Menu.AUTHENTICATE_CLAVE ||
                this.menu == Menu.AUTHENTICATE_CLAVE_CHECK ||
                this.menu == Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE_IN_PROCESS) {
            if (this.authOp == AuthenticateOption.CLAVE_PIN) {
                this.authOp = AuthenticateOption.NONE;
                System.out.println("Su sesión iniciada mediante Cl@ve PIN se ha cerrado");
            }
            this.menu = Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE;
            System.out.println("""
                Has seleccionado el trámite 'Obtener certificado de antecedentes penales'
                Selecciona una opción de identificación:
                1. Cl@ve PIN
                2. Cl@ve permanente
                """);
        } else {
            throw new ProceduralException("No estás en la página correcta para seleccionar esta opción");
        }
    }

    public void selectAuthMethod(byte opc) throws ProceduralException {
        if (this.menu == Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE) {
            switch(opc){
                case 1:
                    this.authOp = AuthenticateOption.CLAVE_PIN;
                    this.menu = Menu.AUTHENTICATE_CLAVE;
                    System.out.println("Método cl@ve PIN seleccionado. Entre su NIF y fecha de validez" +
                            "para recibir un código PIN en su teléfono.\n");
                    break;
                case 2:
                    this.authOp = AuthenticateOption.CLAVE_PERMANENTE;
                    this.menu = Menu.AUTHENTICATE_CLAVE;
                    System.out.println("Método cl@ve permanente seleccionado.");
                    break;
                case 3:
                    this.authOp = AuthenticateOption.CLAVE_PERMANENTE_REFORZADA;
                    this.menu = Menu.AUTHENTICATE_CLAVE;
                    System.out.println("Método cl@ve permanente reforzada seleccionado.");
                    break;
                default:
                    throw new ProceduralException("Método de autenticación no disponible");
            }
        }
    }

    public void enterCred (Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {
        try {
            if (this.menu == Menu.AUTHENTICATE_CLAVE && (authOp == AuthenticateOption.CLAVE_PERMANENTE ||
                    authOp == AuthenticateOption.CLAVE_PERMANENTE_REFORZADA)) {
                if (certAuth.checkCredent(nif, passw)) {
                    this.nif = nif;
                    if (this.authOp == AuthenticateOption.CLAVE_PERMANENTE){
                        this.menu = Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE;
                        System.out.println("Su NIF ha sido registrado correctamente.");
                    }else{
                        this.menu = Menu.AUTHENTICATE_CLAVE_CHECK;
                        System.out.println("Su NIF ha sido registrado correctamente i le han enviado un PIN a su teléfono" +
                                "para autenticarse. Entre el PIN\n");
                    }

                }
            }
        } catch (NifNotRegisteredException e) {
            throw new NifNotRegisteredException("No estás registrado en el sistema Clave Permanente.");
        } catch (NotValidCredException e) {
            throw new NotValidCredException("Las credenciales proporcionadas no son válidas. Por favor, revisa" +
                    "el usuario y la contraseña y vuelve a intentarlo.");
        } catch (AnyMobileRegisteredException e) {
            throw new AnyMobileRegisteredException("No hay ningún dispositivo registrado.");
        } catch (ConnectException e) {
            throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable" +
                    "y vuelve a intentarlo");
        }
    }

    public void enterNIFAndPINObt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException, ProceduralException {
        if (this.menu == Menu.AUTHENTICATE_CLAVE && authOp == AuthenticateOption.CLAVE_PIN) {
            try {
                if (certAuth.sendPIN(nif, valDate)) {
                    this.nif = nif;
                    this.menu = Menu.AUTHENTICATE_CLAVE_CHECK;
                    System.out.println("Su NIF ha sido registrado correctamente i le han enviado un PIN a su teléfono" +
                            "para autenticarse. Entre el PIN\n");
                } else {
                    throw new ProceduralException("Ha habido un error de la autoridad de certificación en enviar el PIN, vuelve a intentarlo más tarde");
                }
            } catch (ConnectException e) {
                throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable" +
                        "y vuelve a intentarlo");
            } catch (NifNotRegisteredException e) {
                throw new NifNotRegisteredException("No estás registrado en el sistema Cl@ve PIN");
            } catch (IncorrectValDateException e) {
                throw new IncorrectValDateException("Su NIF y fecha de vàlidez no corresponden");
            } catch (AnyMobileRegisteredException e) {
                throw new AnyMobileRegisteredException("No ha registrado su número de teléfono");
            }
        } else {
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
    }

    public void enterPIN(SmallCode pin) throws NotValidPINException,
            ConnectException, ProceduralException {
        if (this.menu == Menu.AUTHENTICATE_CLAVE_CHECK && (authOp == AuthenticateOption.CLAVE_PIN ||
                authOp == AuthenticateOption.CLAVE_PERMANENTE_REFORZADA)) {
            try {
                if (certAuth.checkPIN(this.nif, pin)) {
                    this.menu = Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE_IN_PROCESS;
                    System.out.println("Se ha autenticado correctamente\n");
                } else {
                    throw new NotValidPINException("El PIN introducido no es correcto o ha expirado");
                }
            } catch (ConnectException e) {
                throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
            } catch (NotValidPINException e) {
                throw new NotValidPINException("El PIN introducido no es correcto o ha expirado");
            }
        } else {
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
    }

    private void enterForm(Citizen citz, Goal goal) throws IncompleteFormException, IncorrectVerificationException,
            ConnectException, ProceduralException {
        if (this.menu == Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE_IN_PROCESS && authOp == AuthenticateOption.CLAVE_PIN){
            try{
                if(citz == null || goal == null){
                    throw new IncompleteFormException("El formulario no está completo");
                }else if (!gpd.verifyData(citz, goal)){
                    throw new IncorrectVerificationException("La información no es correcta");
                }else{
                    this.citizen = citz;
                    this.goal = goal;
                    this.menu = Menu.SHOW_AMOUNT_TO_PAY;
                    System.out.println("\n");
                }
            }catch(ConnectException e){
                throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
            }
        }else{
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
    }

    public void realizePayment() throws ProceduralException {
        if (this.menu == Menu.SHOW_AMOUNT_TO_PAY && authOp == AuthenticateOption.CLAVE_PIN){
            this.menu = Menu.CARD_DATA_FORM;
        }else{
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }

    }

    private void enterCardData(CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException,
            InsufficientBalanceException, ConnectException, ProceduralException {
        if (this.menu == Menu.CARD_DATA_FORM && authOp == AuthenticateOption.CLAVE_PIN){
            try{
                if(cardD == null){
                    throw new IncompleteFormException("El formulario no está completo");
                }
                if (cas.askForApproval(transfId+"", cardD, new Date(), new BigDecimal(1))) {
                    this.payment = new CardPayment(this.nif, new BigDecimal(1));
                    registerPayment();
                    transfId++;
                    this.menu = Menu.CERTIFICATE_OPTIONS;
                    System.out.println("""
                Ha entrado en la sección 'Seleccionar opciones de certificado'
                Seleccione una opción:
                1. Sin apostilla
                2. Con apostilla
                """);
                } else {
                    throw new ConnectException("Ha habido un error comprobando el pago");
                }
            }catch (ConnectException e) {
                throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
            }catch (NotCorrectFormatException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }
    }


    public void obtainCertificate() throws BadPathException, DigitalSignatureException, ConnectException, ProceduralException {
        if(menu == Menu.CERTIFICATE_OPTIONS || authOp != AuthenticateOption.CLAVE_PIN){
            try{
                certificate = justiceMinistry.getCriminalRecordCertificate(citizen, goal);
                openDocument(certificate.getPath());
                this.menu = Menu.PDF_VIEWER;
                System.out.println("Ya puedes ver el certificado.");
            } catch (ConnectException e){
                throw new ConnectException("Ha habido un error de conexión, asegúrate de tener una conexión estable y vuelve a intentarlo");
            }
        }else{
            throw new ProceduralException("Error procedural, no se encuentra en el trámite 'Obtener certificado de" +
                    " antecedentes penales' o no ha escogido el método" +
                    " de autenticación Cl@ve PIN.");
        }

    }

    public void printDocument() throws BadPathException, PrintingException {
        //...
    }

    private void registerPayment() {
        //...
    }

    private void openDocument(DocPath path) throws BadPathException {
        //...
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        //...
    }
}
