package agenda_telefonica;

import java.io.Serializable;

/**
 * Clasa contract care stocheaza detalile unui contact
 */

public class Contact implements Serializable{
	private String nume;
	private String numar;
	private String email;
	
	/**
     * Constructorul clasei contact cu: name, numar de telefon, si email.
     *
     * @param nume  Numele contactului.
     * @param numar Numarul de telefon al contactului.
     * @param email Adresa de email a contactului.
     */
	public Contact(String nume, String numar, String email) {
		this.nume=nume;
		this.numar=numar;
		this.email=email;
	}
	
	/**
     * Settere si gettere pentru variabilele clasei contact
     */
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNumar() {
		return numar;
	}

	public void setNumar(String numar) {
		this.numar = numar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
     * Metoda de scriere/afisare/returnare a unui contact
     */
    @Override
    public String toString() {
        return "Nume: " + nume + ", Telefon: " + numar + ", Email: " + email;
    }
}
