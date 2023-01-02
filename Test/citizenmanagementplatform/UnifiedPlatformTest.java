package citizenmanagementplatform;

import Exceptions.*;
import data.Nif;
import data.SmallCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CertificationAuthority;

import java.net.ConnectException;
import java.util.Date;

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
            assertEquals(Menu.AUTHENTICATE_CLAVE_PIN, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void enterNIFAndPINObtTest() {
        try {
            // Cualquier combinación de navegación por el menú
            selectAuthMethodTest();
            application.enterNIFAndPINObt(new Nif("48281063S"), new Date());
            assertEquals(Menu.AUTHENTICATE_CLAVE_PIN_CHECK, application.getMenu());
            assertEquals(new Nif("48281063S"), application.getNif());
        } catch (ProceduralException | NotCorrectFormatException | NifNotRegisteredException |
                 IncorrectValDateException | AnyMobileRegisteredException | ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void enterPIN() {
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

}