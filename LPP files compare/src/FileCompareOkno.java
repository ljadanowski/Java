import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FileCompareOkno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton banalizuj, bPrzegladaj, bPrzegladaj2;
	private JLabel pierwszyPlik, drugiPlik;
	private JMenuBar menuBar;
	private JMenu menuProgram, menuNarzedzia;
	private JMenuItem mKonwertuj, mOProgramie, mWyjscie, mPorownajPliki;
	private NarzedziaKonwertuj narzedziaKonwertuj;
	private NarzedziaPorownaj narzedziaPorownaj;
	private JFileChooser fc1, fc2;
	private File katalog1, katalog2;
	private String sciezka1, sciezka2;
	private File[] listaPlikow1, listaPlikow2;
	
	public FileCompareOkno() {
		setSize(600,400);
		setTitle("LPP FILES' COMPARER v1.0");
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuProgram = new JMenu("Program");
		menuBar.add(menuProgram);
		menuNarzedzia = new JMenu("Narzedzia");
		menuBar.add(menuNarzedzia);
		
		mKonwertuj = new JMenuItem("Konwertuj plik/folder do .csv");
		mPorownajPliki = new JMenuItem("Porownaj dwa pliki");
		mOProgramie = new JMenuItem("O programie");
		mWyjscie = new JMenuItem("Wyjscie");
		
		menuProgram.add(mOProgramie);
		menuProgram.addSeparator();
		menuProgram.add(mWyjscie);
		menuNarzedzia.add(mKonwertuj);
		menuNarzedzia.add(mPorownajPliki);
		
		mWyjscie.addActionListener(this);
		mOProgramie.addActionListener(this);
		mPorownajPliki.addActionListener(this);
		mKonwertuj.addActionListener(this);
		
		pierwszyPlik = new JLabel("Pierwszy plik: ");
		pierwszyPlik.setBounds(30, 50, 120, 40);
		add(pierwszyPlik);
		
		drugiPlik = new JLabel("Drugi plik: ");
		drugiPlik.setBounds(30, 110, 120, 40);
		add(drugiPlik);
		
		bPrzegladaj = new JButton("Przegladaj dla 1 pliku...");
		bPrzegladaj.setBounds(180, 50, 190, 30);
		add(bPrzegladaj);
		bPrzegladaj.addActionListener(this);
		
		bPrzegladaj2 = new JButton("Przegladaj dla 2 pliku...");
		bPrzegladaj2.setBounds(180, 110, 190, 30);
		add(bPrzegladaj2);
		bPrzegladaj2.addActionListener(this);
		
		banalizuj = new JButton("Konwertuj i analizuj!");
		banalizuj.setBounds(180, 200, 190, 50);
		add(banalizuj);
		banalizuj.addActionListener(this);
	}
	public static void main(String[] args) {
		FileCompareOkno fco = new FileCompareOkno();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == mWyjscie) dispose();
		else if(source == mOProgramie) JOptionPane.showMessageDialog(this, 
				"Autor -  Lukasz Jadanowski (BSS IT).\n" +
				"2015r.");
		else if(source == bPrzegladaj) {
			fc1 = new JFileChooser();
			fc1.setCurrentDirectory(new File("D:\\")); //tutaj potem zmienic np. C:\\
			fc1.setDialogTitle("wybierz pierwszy folder");
			fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc1.setAcceptAllFileFilterUsed(false);
			
			if(fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog1 = fc1.getSelectedFile();
				listaPlikow1 = katalog1.listFiles();
				for(File f : listaPlikow1) System.out.println(f.getAbsolutePath());
			}	
		}
		else if(source == bPrzegladaj2) {
			fc2 = new JFileChooser();
			fc2.setCurrentDirectory(new File("D:\\")); //tutaj potem zmienic np. C:\\
			fc2.setDialogTitle("wybierz drugi folder");
			fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc2.setAcceptAllFileFilterUsed(false);
			
			if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog2 = fc2.getSelectedFile();
				listaPlikow2 = katalog2.listFiles();
				for(File f : listaPlikow2) System.out.println(f.getAbsolutePath());
				
				String sciezka = katalog2.getAbsolutePath();
				//System.out.println("sciezka katalogu" +sciezka);
			}	
		}
		else if(source == banalizuj) {
			FileCompareManager fcm = new FileCompareManager();
			File[] zawartosc1 = katalog1.listFiles();
			File[] zawartosc2 = katalog2.listFiles();
			
			for(File f : zawartosc1) 
				fcm.konwertujArkuszDoCsv(f.getAbsoluteFile());
			for(File f : zawartosc2) 
				fcm.konwertujArkuszDoCsv(f.getAbsoluteFile());
			
			JOptionPane.showMessageDialog(null, "Konwersja ukoñczona pomyœlnie!\n" 
			+ "Teraz rozpoczyna siê proces porównywania plików..."
					);
			ArrayList<String> lista = new ArrayList<String>();
			for(File i : zawartosc1) {
				if(i.getName().indexOf(".csv") != -1) {
					lista.add(i.getAbsolutePath());
				}
				System.out.println("Do listy: " +i.getAbsolutePath());	
			}
			System.out.println(lista.toString());	
//			File[] zawartosc1Csv = fcm.znajdzCsv(zawartosc1);
//			File[] zawartosc2Csv = fcm.znajdzCsv(zawartosc2);
//			ArrayList<String> lista1 = fcm.znajdzCsv2(zawartosc1);
//			System.out.println("=-=-=-=-" +lista1.get(0));
//			ArrayList<String> lista2 = fcm.znajdzCsv2(zawartosc2);
//			System.out.println("=================");
//			for(String a : lista1)  System.out.println(a);
//			System.out.println("=================");
//			for(String a : lista2)  System.out.println(a);
		}
		else if(source == mKonwertuj) {
			if(narzedziaKonwertuj == null) narzedziaKonwertuj  = new NarzedziaKonwertuj(this);
			narzedziaKonwertuj.setVisible(true);
		}
		else if(source == mPorownajPliki) {
			if(narzedziaPorownaj == null) narzedziaPorownaj  = new NarzedziaPorownaj(this);
			narzedziaPorownaj.setVisible(true);
		}
	}
}
