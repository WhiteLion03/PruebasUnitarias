package publicadministration;

import data.Nif;
import Exceptions.NotCorrectFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTest {

    @Test
    void constructorNameTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Citizen(new Nif("48056732V"), (String)null, "Carrer Major, 26", "653543123");
        });
    }

    @Test
    void constructorAddTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Citizen(new Nif("48056732V"), "Helena", (String)null, "653543123");
        });
    }

    @Test
    void constructorMobileTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Citizen(new Nif("48056732V"), "Helena", "Carrer Major 26", (String)null);
        });
    }

    @Test
    void getNameTest() throws NotCorrectFormatException {
        Nif nif1 = new Nif ("40235234F");
        Citizen persona1 = new Citizen(nif1, "Maria", "C/Paraguay", "654631643");
        assertEquals("Maria", persona1.getName());
    }

    @Test
    void getNifTest() throws NotCorrectFormatException {
        Nif nif1 = new Nif ("40235234F");
        Citizen persona1 = new Citizen(nif1, "Maria", "C/Paraguay", "654631643");
        assertEquals("40235234F", persona1.getNif().getNif());
    }

    @Test
    void getAdressTest() throws NotCorrectFormatException{
        Nif nif1 = new Nif ("40235234F");
        Citizen persona1 = new Citizen(nif1, "Maria", "C/Paraguay", "654631643");
        assertEquals("C/Paraguay", persona1.getAddress());
    }
}