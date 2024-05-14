package agenda_telefonica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AgendaTest {
    private static File testFile;

    @Test
    void testAdaugareContact() {
        Agenda agenda = new Agenda(testFile);
        Contact contact1 = new Contact("Dan Valentin", "0719679460", "dan.2000@yahoo.com");
        Contact contact2 = new Contact("Popescu Viorel", "0257894035", "popescu.viorel01@gmail.com");

        agenda.adaugaContact(contact1);
        agenda.adaugaContact(contact2);

        Assertions.assertNotNull(agenda);
    }
    
    @Test
    void testGasireContactNume() {
        Agenda agenda = new Agenda(testFile);
        Contact contact1 = new Contact("Dan Valentin", "0719679460", "dan.2000@yahoo.com");
        Contact contact2 = new Contact("Popescu Viorel", "0257894035", "popescu.viorel01@gmail.com");

        agenda.adaugaContact(contact1);
        agenda.adaugaContact(contact2);

        Contact gasitNume = agenda.cautaNume("Dan");
        Assertions.assertNotNull(gasitNume);
        assertEquals("Dan", gasitNume.getNume());
    }
    
    @Test
    void testGasireContactNumar() {
        Agenda agenda = new Agenda(testFile);
        Contact contact1 = new Contact("Dan Valentin", "0719679460", "dan.2000@yahoo.com");
        Contact contact2 = new Contact("Popescu Viorel", "0257894035", "popescu.viorel01@gmail.com");

        agenda.adaugaContact(contact1);
        agenda.adaugaContact(contact2);

        Contact gasitNumar = agenda.cautaNumar("0257894035");
        Assertions.assertNotNull(gasitNumar);
        assertEquals("0257894035", gasitNumar.getNumar());
    }
    
    @Test
    void testStergereContact() {
        Agenda agenda = new Agenda(testFile);
        Contact contact = new Contact("Dumitrescu Marian", "0799348249", "dumitrescu@gmail.com");

        agenda.adaugaContact(contact);
        agenda.stergeContact(contact);

        Contact deletedContact = agenda.cautaNume("Dumitrescu Marian");
        Assertions.assertNull(deletedContact);
    }
}

