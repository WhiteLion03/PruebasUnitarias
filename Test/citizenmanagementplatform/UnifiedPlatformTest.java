package citizenmanagementplatform;

import Exceptions.*;
import data.Nif;
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
        application = new UnifiedPlatform(new CertificationAuthorityDummy());
    }

    @Test
    public void constructorTest() {
        assertEquals(UnifiedPlatform.Menu.MAIN_PAGE, application.getMenu());
        assertEquals(UnifiedPlatform.AuthenticateOption.NONE, application.getAuthOp());
    }

    @Test
    public void selectJustMinTest() {
        try {
            application.selectJusMin();
            assertEquals(UnifiedPlatform.Menu.JUSTICE_MINISTRY, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void selectProceduresTest() {
        try {
            application.selectJusMin();
            application.selectProcedures();
            assertEquals(UnifiedPlatform.Menu.JUSTICE_MINISTRY_PROCEDURES, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void selectCriminalReportCertificateTest() {
        try {
            application.selectJusMin();
            application.selectProcedures();
            application.selectCriminalReportCertificate();
            assertEquals(UnifiedPlatform.Menu.OBTAIN_CRIMINAL_REPORT_CERTIFICATE, application.getMenu());
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test
    public void selectAuthMethod() {
        try {
            application.selectJusMin();
            application.selectProcedures();
            application.selectCriminalReportCertificate();
            application.selectAuthMethod((byte) 1);
        } catch (ProceduralException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void enterNIFAndPINObt() {
        try {
            application.selectJusMin();
            application.selectProcedures();
            application.selectCriminalReportCertificate();
            application.selectAuthMethod((byte) 1);
            application.enterNIFAndPINObt(new Nif("48281063S"), new Date());
            assertEquals(new Nif("48281063S"), application.getNif());
        } catch (ProceduralException | NotCorrectFormatException | NifNotRegisteredException |
                 IncorrectValDateException | AnyMobileRegisteredException | ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}