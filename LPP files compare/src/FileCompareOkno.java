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
	
	public FileCompareOkno() {
		setSize(600,400);
		setTitle("LPP FILES' COMPARER v1.0");
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
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
		
		banalizuj = new JButton("Konwertuj i analizuj!");
		banalizuj.setBounds(150, 180, 180, 30);
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
				"Autor -  Lukasz Jadanowski.\n" +
				"2015r.");
		else if(source == bPrzegladaj) {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("C:\\")); //tutaj potem zmienic np. C:\\
			fc.setDialogTitle("wybierz pierwszy folder");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				String sciezka = plik.getAbsolutePath(); 
				System.out.println("Test: " + sciezka);
			}	
		}
		else if(source == bPrzegladaj2) {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(".")); //tutaj potem zmienic np. C:\\
			fc.setDialogTitle("wybierz drugi folder");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File directory = fc.getSelectedFile();
				File[] listaPlikow = directory.listFiles();
				for(File f : listaPlikow) System.out.println(f.getAbsolutePath());
				
				String sciezka = directory.getAbsolutePath();
				System.out.println("Test: " + sciezka);
			}	
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
