package agenda_telefonica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Clasa main care va face majoritatea functionalitati
 */
public class Main {

	/**
     * Adaugarea contactelor intr-un fisier folosindu-ne de serializare
     */
	public static void serializare(Agenda agenda, File fisier) {
		try(FileOutputStream fileos = new FileOutputStream(fisier);
		ObjectOutputStream objectos = new ObjectOutputStream(fileos)){
			objectos.writeObject(agenda);
		objectos.close();
		fileos.close();
		System.out.println("Este construit fisierul. Dati refresh la folder. ");
	} catch (IOException e) {
        e.printStackTrace();
    }
	}

    public static Agenda deserializare(File fisier) {
        Agenda agenda = null;
        try (FileInputStream fileis = new FileInputStream(fisier);
             ObjectInputStream objectis = new ObjectInputStream(fileis)) {

            Object o = objectis.readObject();
            if (o instanceof Agenda) {
                agenda = (Agenda) o;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return agenda;
    }


    public static void main(String[] args) {
    	/**
         * @param fisierContacte Fisierul in care stocam contactele
         * @param agenda O lista de contacte
         * @param scanner Pentru a citi linile de la tastatura
         */
    	File fisierContacte = new File("fisier.txt");
        Agenda agenda = new Agenda(fisierContacte);
        Scanner scanner = new Scanner(System.in);
        
        /**
    	 * Adaugarea unor noi contacte
    	 */
        Contact contact1 = new Contact("Ion Gheorghe", "0722552299", "ion.gheorghe@gmail.com");
        agenda.adaugaContact(contact1);
        Contact contact2 = new Contact("Vasile", "0745346599", "ion.vasile@gmail.com");
        agenda.adaugaContact(contact2);
        Contact contact3 = new Contact("Grigore Valentin", "0719263460", "grigore.2003@yahoo.com");
        Contact contact4 = new Contact("Popescu Dan", "0257347535", "popescu.dan01@gmail.com");
        agenda.adaugaContact(contact3);
        agenda.adaugaContact(contact4);
        
        /**
    	 * Adaugarea unui nou contact de la tastatura
    	 */
        System.out.print("Introduceți numele pentru adaugare: ");
        String numeAdaugat = scanner.nextLine();
        System.out.print("Introduceți numarul pentru adaugare: ");
        String numarAdaugat = scanner.nextLine();
        System.out.print("Introduceți email-ul pentru adaugare: ");
        String emailAdaugat = scanner.nextLine();
        Contact contact5 = new Contact(numeAdaugat, numarAdaugat, emailAdaugat);
        agenda.adaugaContact(contact5);
        
        /**
    	 * Cautarea unui contact dupa numele dat de la tastatura
    	 */
        System.out.print("Introduceți numele pentru căutare: ");
        String numeleCautat = scanner.nextLine();
        Contact contactulCautat = agenda.cautaNume(numeleCautat);
        if (contactulCautat != null) {
            System.out.println("Contact găsit: " + contactulCautat);
        } else {
            System.out.println("Contactul nu a fost găsit.");}
        
        /**
    	 * Cautarea unui contact dupa numarul de telefon dat de la tastatura
    	 */
        System.out.print("Introduceți numarul de telefon pentru căutare: ");
        String numarulCautat = scanner.nextLine();
        Contact contactulCautat2 = agenda.cautaNumar(numarulCautat);
        if (contactulCautat2 != null) {
            System.out.println("Contact găsit: " + contactulCautat2);
        } else {
            System.out.println("Contactul nu a fost găsit.");}
        
        /**
    	 * Stergerea unui contact dupa numele dat de la tastatura
    	 */
        System.out.print("Introduceți numele pentru stergere: ");
        String numeleCautat2 = scanner.nextLine();
        Contact contactulCautat3 = agenda.cautaNume(numeleCautat2);
        if (contactulCautat3 != null) {
            agenda.stergeContact(contactulCautat3);
        } else {
            System.out.println("Contactul nu a fost găsit.");}
        
        /**
    	 * Crearea fisierului unde se afla serializarea
    	 */
        File file = new File("C:\\Users\\Alin\\eclipse-workspace\\Agenda Telefonica\\serializare.ser");		
		serializare(agenda, file);
		
		/**
    	 * Afisarea agendei cu contacte
    	 */
        agenda.afiseazaContacte();
    }
}