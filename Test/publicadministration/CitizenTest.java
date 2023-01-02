package publicadministration;

import data.Nif;
import Exceptions.NotCorrectFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTest {

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