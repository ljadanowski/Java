import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class FileCompareOkno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton banalizuj, bPrzegladaj, bPrzegladaj2;
	JLabel pierwszyPlik, drugiPlik;
	JMenuBar menuBar;
	JMenu menuProgram, menuNarzedzia;
	JMenuItem mKonwertuj, mOProgramie, mWyjscie;
	JTextArea notatnik;
	
	public FileCompareOkno() {
		
		setSize(600,400);
		setTitle("LPP FILES' COMPARER v1.0");
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuProgram = new JMenu("Program");
		menuBar.add(menuProgram);
		menuNarzedzia = new JMenu("Narzedzia");
		menuBar.add(menuNarzedzia);
		
		mKonwertuj = new JMenuItem("Konwertuj");
		mOProgramie = new JMenuItem("O programie");
		mWyjscie = new JMenuItem("Wyjscie");
		
		menuProgram.add(mOProgramie);
		menuProgram.addSeparator();
		menuProgram.add(mWyjscie);
		menuNarzedzia.add(mKonwertuj);
		
		mWyjscie.addActionListener(this);
		mOProgramie.addActionListener(this);
		
		pierwszyPlik = new JLabel("Pierwszy plik: ");
		pierwszyPlik.setBounds(50, 50, 120, 30);
		add(pierwszyPlik);
		
		drugiPlik = new JLabel("Drugi plik: ");
		drugiPlik.setBounds(50, 100, 120, 30);
		add(drugiPlik);
		
		bPrzegladaj = new JButton("Przegladaj dla 1 pliku...");
		bPrzegladaj.setBounds(150, 50, 190, 20);
		add(bPrzegladaj);
		bPrzegladaj.addActionListener(this);
		
		bPrzegladaj2 = new JButton("Przegladaj dla 2 pliku...");
		bPrzegladaj2.setBounds(150, 110, 190, 20);
		add(bPrzegladaj2);
		bPrzegladaj2.addActionListener(this);
		
		banalizuj = new JButton("Analizuj!");
		banalizuj.setBounds(120, 180, 120, 30);
		add(banalizuj);
		banalizuj.addActionListener(this);
		
		notatnik = new JTextArea();
		notatnik.setBounds(120, 200, 200, 80);
		add(notatnik);
		
	}
	public static void main(String[] args) {
		FileCompareOkno fco = new FileCompareOkno();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == mWyjscie) dispose();
		else if(source == mOProgramie) JOptionPane.showMessageDialog(this, "Program napisany przez Lukasza Jadanowskiego.");
		else if(source == bPrzegladaj) {
			JFileChooser fc = new JFileChooser();
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				String sciezka = plik.getAbsolutePath(); //getName()
				System.out.println("Test: " + sciezka);
			}
		}
		else if(source == bPrzegladaj2) {
			File directory = new File("");
			
		}
	}

}
