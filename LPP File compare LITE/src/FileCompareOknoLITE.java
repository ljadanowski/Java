import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ProgressBarUI;

public class FileCompareOknoLITE extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton banalizuj, bPrzegladaj, bPrzegladaj2;
	private JLabel pierwszyPlik, drugiPlik, aktualneZadanie;
	private JFileChooser fc1, fc2;
	private File katalog1, katalog2;
	private String sciezka1, sciezka2;
	private File[] listaPlikow1, listaPlikow2;
	private Scanner skaner1, skaner2, historiaOtworz;
	private PrintWriter logi, historiaZapis;
	private File plik1, plik2, log, historia;
	private String hist, hist2, linia1, linia2;
	private JProgressBar progressBar;
	private int miarka, progres;
	private JCheckBox ignorujPusteLinie;
	//private boolean takiSam = true;

	public FileCompareOknoLITE() {
		setSize(700,500);
		setTitle("LPP FILES' COMPARER v1.0 LITE");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		banalizuj = new JButton("Analizuj!");
		banalizuj.setBounds(180, 200, 190, 50);
		add(banalizuj);
		banalizuj.addActionListener(this);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBounds(180, 290, 200, 30);
		add(progressBar);
		
		aktualneZadanie = new JLabel("Brak wykonywania aktualnego zadania");
		aktualneZadanie.setBounds(180, 340, 300, 30);
		//aktualneZadanie.setForeground(Color.GREEN);
		aktualneZadanie.setForeground(new Color(20, 100, 100));
		add(aktualneZadanie);
		
//		ignorujPusteLinie = new JCheckBox("Ignoruj puste linie");
//		ignorujPusteLinie.setBounds(450, 200, 160, 30);
//		ignorujPusteLinie.setSelected(true);
//		add(ignorujPusteLinie);
		
		String obecny = (new File("conf.txt")).getAbsolutePath();
		
		historia = new File(obecny);
		
		if(historia.exists() && historia.length() != 0) {
			try {
				historiaOtworz = new Scanner(historia);
				if(historiaOtworz.hasNext()) 
					hist = historiaOtworz.nextLine();
				else hist = "D:\\";
				if(historiaOtworz.hasNext()) 
					hist2 = historiaOtworz.nextLine(); //-------------------------
				else hist2 = "D:\\";
				historiaOtworz.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(!historia.exists() || historia.length() == 0) {
			hist = "D:\\";
			hist2 = "D:\\"; //--
		}

		if(hist.isEmpty()) hist = "D:\\";
		if(hist2.isEmpty()) hist2 = "D:\\"; //--
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == bPrzegladaj) {			
			fc1 = new JFileChooser();
			fc1.setCurrentDirectory(new File(hist));
			fc1.setDialogTitle("wybierz pierwszy folder");
			fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc1.setAcceptAllFileFilterUsed(false);
			
			if(fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog1 = fc1.getSelectedFile();
				listaPlikow1 = katalog1.listFiles();
			}	
		}
		else if(source == bPrzegladaj2) {
			fc2 = new JFileChooser();
			fc2.setCurrentDirectory(new File(hist2)); //potem tuaj zmienic na hist2
			fc2.setDialogTitle("wybierz drugi folder");
			fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc2.setAcceptAllFileFilterUsed(false);
			
			if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog2 = fc2.getSelectedFile();
				listaPlikow2 = katalog2.listFiles();
			}	
		}
		else if(source == banalizuj) {
			progressBar.setValue(0);
			File[] zawartosc1 = katalog1.listFiles();
			File[] zawartosc2 = katalog2.listFiles();
					
			JOptionPane.showMessageDialog(null, "Rozpoczyna siê proces porównywania plików...");
			
			//System.out.println("Tutaj bedzie katalog: "+zawartosc1[0].getParent());
			//File katalogLogow = new File(zawartosc1[0].getParent()+"\\Logi");
			
//			if(!katalogLogow.mkdir()) 
//				JOptionPane.showMessageDialog(null, "Nie mogê utworzyæ katalogu logów!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
			
			//najpierw trzeba odfiltrowac pliki .csv
			
			//System.out.println("RODZIC KATALOGU: "+katalog1.getParent());
			
			ArrayList<File> odfpliki1 = new ArrayList<File>();
			ArrayList<File> odfpliki2 = new ArrayList<File>();
			
			for(File i : zawartosc1) {
				if(i.getName().indexOf(".csv") != -1) 
					odfpliki1.add(i);
			}
			
			for(File i : zawartosc2) {
				if(i.getName().indexOf(".csv") != -1) 
					odfpliki2.add(i);
			}
			
			if(odfpliki1.size() != odfpliki2.size()) 
				JOptionPane.showMessageDialog(null, "Ró¿na iloœæ plików w katalogach!\nProgram moze nie zadzia³aæ prawid³owo!", 
						"Uwaga!", JOptionPane.WARNING_MESSAGE);
			
			miarka = 100/odfpliki1.size();
			progres = miarka;
			
			for(int i=0; i<odfpliki1.size(); i++) {
				boolean takiSam = true;
				plik1 = new File(odfpliki1.get(i).getAbsolutePath());
				//plik2 = new File(odfpliki2.get(i).getAbsolutePath());
				plik2 = new File(odfpliki2.get(i).getParent()+"\\"+odfpliki1.get(i).getName());
				if(!plik2.exists()) {
					JOptionPane.showMessageDialog(null, "Plik: "+plik2.getName()+ " nie istnieje!", "Nie istnieje podany plik!", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				log = new File(plik1.getParent() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
						+  "_" 
						+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
						+ ".txt");				
				
				
				int linia = 1;
				//int liniePierwszegoPliku = 0, linieDrugiegoPliku = 0;
				try {
					logi = new PrintWriter(log);
					skaner1 = new Scanner(plik1);
					skaner2 = new Scanner(plik2);
				} catch (FileNotFoundException e1) {
						e1.printStackTrace();
				}
				aktualneZadanie.setText("Analizowanie pliku: " + plik1.getName());
				//-----------------------------------------------------------------------
				while(skaner1.hasNext()) {
					linia1 = FCLManager.wczytajSameWyrazy(skaner1.nextLine()); //--
					if(skaner2.hasNext()) {
						linia2 = FCLManager.wczytajSameWyrazy(skaner2.nextLine());
						if(! (linia1.equals(linia2 ))) {
							takiSam = false;
							logi.println("Pliki sa rozne! Linia: " +linia);
						}
					}
					else {
						//takiSam = false;
						//logi.println("Pierwszy plik ma wiecej linii! (" + plik1.getAbsolutePath() + ") - Od linii: " +linia);
						//trzeba sprawdzic czy ma jakies niepuste linie, jezeli tak to sa rozne pliki!

						if( !(FCLManager.wczytajSameWyrazy(linia1).isEmpty()) ) {
							takiSam = false;
							logi.println("Blad w linii: "+linia);
							 //tutaj bez breaka moze byc bo to beda wszystkie linie bledne!
						}
						while(skaner1.hasNext()) {
							linia++;
							linia1 = FCLManager.wczytajSameWyrazy(skaner1.nextLine());
							if(!(linia1.isEmpty())) {
								logi.println("Blad w linii: "+linia);
								takiSam = false;
							}
						}
						break;
					}
					linia++;	
					progressBar.setValue(progres);
					progres += miarka;
				}
				//----------------------------------
				while(skaner2.hasNext()) {
					//takiSam = false;
					//logi.println("Drugi plik ma wiecej linii! (" +plik2.getAbsolutePath() + ")");
					linia2 = FCLManager.wczytajSameWyrazy(skaner2.nextLine());
					aktualneZadanie.setText("Trwa analiza pliku "+plik2.getName()+"...");
					if(!(linia2.isEmpty())) {
						logi.println("Blad w linii: "+linia);
						takiSam = false;
					}
					linia++;
				}
				
				if(takiSam) logi.println("Plik sa takie same!");
				
				logi.close();
				skaner1.close();
				skaner2.close();
			}
			JOptionPane.showMessageDialog(null, "Porównywanie ukoñczone!");
			aktualneZadanie.setText("Trwa zrzucanie historii...");
			try {
				historiaZapis = new PrintWriter("conf.txt");
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Nie odnalezieono pliku conf.txt!", "conf.txt", JOptionPane.ERROR_MESSAGE);
			}
			historiaZapis.write(katalog1.getAbsolutePath());
			historiaZapis.println("");
			historiaZapis.println(katalog2.getAbsolutePath());
			historiaZapis.close();
			aktualneZadanie.setText("Analiza zakoñczona");
			JOptionPane.showMessageDialog(null, "Logi zrzucone do: "+katalog1.getAbsolutePath());	
		}
	}
	public static void main(String[] args) {
		FileCompareOknoLITE fco = new FileCompareOknoLITE();
		fco.setVisible(true);
	}

}
