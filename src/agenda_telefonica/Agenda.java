package agenda_telefonica;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clasa Agenda care va contine o lista de contacte
 */


public class Agenda implements Serializable{
	private ArrayList<Contact> contacte = new ArrayList<>();
	private File fisierContacte;
	
	/**
     * Constructorul clasei Agenda
     *
     * @param fisierContacte Fisierul in care stocam contactele
     */
	public Agenda(File fisierContacte) {
        this.fisierContacte = fisierContacte;
    }
	
	/**
	 * Metoda de adaugare a unui contact in lista
	 */
    public void adaugaContact(Contact contact) {
        contacte.add(contact);   
        System.out.println("Contact adaugat: " + contact); 
    }
	
	public void adaugaContactSQL(Contact contact) {
	    String url = "jdbc:mysql://localhost:3306/agenda";
	    String user = "root"; 
	    String password = "123456"; 

	    String sql = "INSERT INTO contacte(nume, numar, email) VALUES(?,?,?)";

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, contact.getNume());
	        pstmt.setString(2, contact.getNumar());
	        pstmt.setString(3, contact.getEmail());
	        pstmt.executeUpdate();
	        System.out.println("Contact adaugat cu success");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
    /**
     * Metoda de afisare a contactelor din lista
     */
    public void afiseazaContacte() {
        for (Contact contact : contacte) {
            System.out.println(contact);
        }
    }
    /**
     * Metoda de afisare a contactelor din lista folosind MySQL
     */
    public void afiseazaContacteSQL() {
    	String url = "jdbc:mysql://localhost:3306/agenda";
	    String user = "root"; 
	    String password = "123456";

        String sql = "SELECT id, nume, numar, email FROM contacte";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String numar = rs.getString("numar");
                String email = rs.getString("email");
                System.out.println("ID: " + id + ", Nume: " + nume + ", Numar: " + numar + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metoda de a cauta contactele dupa nume
     */
    public Contact cautaNume(String nume) {
        for (Contact contact : contacte) {
            if (contact.getNume().equalsIgnoreCase(nume)) {
                return contact;
            }
        }
        return null;
    }
    /**
     * Metoda de a cauta contactele dupa nume folosind MySQL
     */
    public void cautaNumeSQL(String numeSQL) {
    	String url = "jdbc:mysql://localhost:3306/agenda";
	    String user = "root"; 
	    String password = "123456";

        String sql = "SELECT id, nume, numar, email FROM contacte WHERE nume LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + numeSQL + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int id = rs.getInt("id");
                    String nume = rs.getString("nume");
                    String numar = rs.getString("numar");
                    String email = rs.getString("email");
                    System.out.println("ID: " + id + ", Nume: " + nume + ", Numar: " + numar + ", Email: " + email);
                }
                
                if (!found) {
                    System.out.println("Niciun contact gasit cu numele: " + numeSQL);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metoda de a cauta contactele dupa numarul de telefon
     */
    public Contact cautaNumar(String numar) {
        for (Contact contact : contacte) {
            if (contact.getNumar().equals(numar)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Metoda de a a sterge un contact din lista
     */
    public void stergeContact(Contact contact) {
        contacte.remove(contact);
    }

    public void setFisierContacte(File fisierContacte) {
	    this.fisierContacte = fisierContacte;
	}
    /**
     * Metoda de a a sterge un contact din lista folosind MySQL
     */
    public void stergeContacteSQL(String numeContact) {
    	String url = "jdbc:mysql://localhost:3306/agenda";
	    String user = "root"; 
	    String password = "123456";

        String sql = "DELETE FROM contacte WHERE nume = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeContact);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Contactele cu numele '" + numeContact + "' au fost sterse");
            } else {
                System.out.println("Niciun contact cu numele: '" + numeContact);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la stergere: " + e.getMessage());
        }
    }
}

