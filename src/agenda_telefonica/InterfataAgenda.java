package agenda_telefonica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterfataAgenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Nume;
	private JTextField Numar;
	private JTextField Email;
	private Agenda agenda;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File fisierContacte = new File("serializare.ser");
					
					if (!fisierContacte.exists()) {
						fisierContacte.createNewFile();						
					}
					Agenda agenda = new Agenda(fisierContacte);
					InterfataAgenda frame = new InterfataAgenda(agenda);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public InterfataAgenda(Agenda agenda) {
		this.agenda=agenda;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelNume = new JLabel("Nume");
		LabelNume.setBounds(22, 21, 36, 13);
		contentPane.add(LabelNume);
		
		Nume = new JTextField();
		Nume.setBounds(60, 18, 96, 19);
		contentPane.add(Nume);
		Nume.setColumns(10);
		
		JLabel LabelNumar = new JLabel("Numar");
		LabelNumar.setBounds(22, 50, 45, 13);
		contentPane.add(LabelNumar);
		
		Numar = new JTextField();
		Numar.setBounds(60, 47, 96, 19);
		contentPane.add(Numar);
		Numar.setColumns(10);
		
		JLabel LabelEmail = new JLabel("Email");
		LabelEmail.setBounds(22, 78, 45, 13);
		contentPane.add(LabelEmail);
		
		Email = new JTextField();
		Email.setBounds(60, 73, 96, 19);
		contentPane.add(Email);
		Email.setColumns(10);
		
		JButton Adaugare = new JButton("Adaugare");
		Adaugare.setBounds(22, 114, 85, 21);
		contentPane.add(Adaugare);
		Adaugare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String nume = Nume.getText();
			String numar = Numar.getText();
			String email = Email.getText();
			
			Contact newContact = new Contact(nume, numar, email);
			agenda.adaugaContactSQL(newContact);
			
			Nume.setText("");
			Numar.setText("");
			Email.setText("");}
		});
		
		JButton Stergere = new JButton("Stergere");
		Stergere.setBounds(130, 114, 85, 21);
		contentPane.add(Stergere);
		Stergere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String nume = Nume.getText();
			
			agenda.stergeContacteSQL(nume);
			
			Nume.setText("");}
		});
		
		JButton Cautare = new JButton("Cautare");
		Cautare.setBounds(240, 114, 85, 21);
		contentPane.add(Cautare);
		Cautare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String nume = Nume.getText();
			
			agenda.cautaNumeSQL(nume);
			
			Nume.setText("");}
		});
		
		JButton Afisare = new JButton("Afisare");
		Afisare.setBounds(341, 114, 85, 21);
		contentPane.add(Afisare);
		Afisare.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        agenda.afiseazaContacteSQL(); 
		    }
		});
	}
	
}
