package citizenmanagementplatform;

import Exceptions.*;
import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import services.CertificationAuthority;

import java.net.ConnectException;
import java.util.Date;

import static citizenmanagementplatform.Menu.*;
import static org.junit.jupiter.api.Assertions.*;

class UnifiedPlatformTest {
    private UnifiedPlatform application;

    @BeforeEach
    public void setUp() {
        application = new UnifiedPlatform(new CertificationAuthorityDummy(), new JusticeMinistryDummy(), new GPDDummy(), new CASDummy());
    }

    @Test
    public void constructorTest() {
        assertEquals(Menu.MAIN_PAGE, application.getMenu());
        assertEquals(AuthenticateOption.NONE, application.getAuthOp());
    }

    @Test
    public void selectJustMinTest() {
        application.setMenu(MAIN_PAGE);
        try {
            // Cualquier combinación de navegación por el menú
            application.selectJusMin();
            assertEquals(Menu.JUSTICE_MINISTRY, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void selectProceduresTest() {
        application.setMenu(JUSTICE_MINISTRY);
        try {
            // Cualquier combinación de navegación por el menú
            selectJustMinTest();
            application.selectProcedures();
            assertEquals(Menu.JUSTICE_MINISTRY_PROCEDURES, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void selectCriminalReportCertificateTest() {
        try {
            // Cualquier combinación de navegación por el menú
            selectProceduresTest();
            application.selectCriminalReportCertificate();
            assertEquals(Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test
    public void selectAuthMethodTest() {
        try {
            // Cualquier combinación de navegación por el menú
            selectCriminalReportCertificateTest();
            application.selectAuthMethod((byte) 1);
            assertEquals(Menu.AUTHENTICATE_CLAVE, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void enterCredTest() {
        try {
            selectAuthMethodTest();
            application.enterCred(new Nif("48281063S"), new Password("Hola1234"));
        } catch (NifNotRegisteredException | AnyMobileRegisteredException |
                NotCorrectFormatException | ConnectException | NotValidCredException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test
    public void enterNIFAndPINObtTest() {
        try {
            // Cualquier combinación de navegación por el menú
            enterCredTest();
            application.enterNIFAndPINObt(new Nif("48281063S"), new Date());
            assertEquals(Menu.AUTHENTICATE_CLAVE_CHECK, application.getMenu());
            assertEquals(new Nif("48281063S"), application.getNif());
        } catch (ProceduralException | NotCorrectFormatException | NifNotRegisteredException |
                 IncorrectValDateException | AnyMobileRegisteredException | ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void enterPINTest() {
        try {
            // Cualquier combinación de navegación por el menú
            enterNIFAndPINObtTest();
            application.enterPIN(new SmallCode("547"));
            assertEquals(Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE_IN_PROCESS, application.getMenu());
        } catch (ProceduralException | NotCorrectFormatException | ConnectException | NotValidPINException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void enterFormTest() throws NotCorrectFormatException {
        try {
            enterPINTest();
            Citizen pax1 = new Citizen(new Nif("48281063S"), "Laura", "Carrer Major 50", "652143598");
            Goal g1 = new Goal(goalTypes.PUBLIC_WORKERS);
            application.enterForm(pax1, g1);
            assertEquals(Menu.SHOW_AMOUNT_TO_PAY, application.getMenu());
        } catch (ProceduralException  | IncompleteFormException | IncorrectVerificationException | ConnectException e ) {
            System.out.println(e.getMessage());
            fail();

        }
    }

    @Test
    public void realizePaymentTest(){
        try{
            enterFormTest();
            application.realizePayment();
            assertEquals(Menu.CARD_DATA_FORM, application.getMenu());
        }catch (ProceduralException | NotCorrectFormatException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void enterCardDataTest(){
        try {
            realizePaymentTest();
            CreditCard cc = new CreditCard(new Nif("48281063S"), "4111111111111111", new Date(2022,
                    11, 31), new SmallCode("345"));
            application.enterCardData(cc);
            assertEquals(CERTIFICATE_OPTIONS, application.getMenu());
        } catch (NotCorrectFormatException | NotValidPaymentDataException | IncompleteFormException
                | InsufficientBalanceException | ProceduralException | ConnectException e ) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void obtainCertificateTest() {
        try {
            enterCardDataTest();
            application.obtainCertificate();
            assertEquals(Menu.PDF_VIEWER, application.getMenu());
        } catch (ProceduralException | DigitalSignatureException | BadPathException | ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}