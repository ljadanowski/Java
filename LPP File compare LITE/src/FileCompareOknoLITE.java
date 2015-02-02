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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ProgressBarUI;

public class FileCompareOknoLITE extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton banalizuj, bPrzegladaj, bPrzegladaj2;
	private JLabel pierwszyPlik, drugiPlik, aktualneZadanie, zakresBledu;
	private JFileChooser fc1, fc2;
	private File katalog1, katalog2;
	private String sciezka1, sciezka2;
	private File[] listaPlikow1, listaPlikow2;
	private Scanner skaner1, skaner2, historiaOtworz;
	private PrintWriter logi, historiaZapis;
	private File plik1, plik2, log, historia;
	private String hist, hist2, linia1, linia2;
	private JProgressBar progressBar;
	static int miarka, progres;
	private JCheckBox ignorujPusteLinie;
	private JTextField tZakresBledu;
	//private boolean takiSam = true;

	public FileCompareOknoLITE() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
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
		aktualneZadanie.setForeground(new Color(20, 100, 100));
		add(aktualneZadanie);
		
		zakresBledu = new JLabel("Zakres b³êdu: ");
		zakresBledu.setBounds(450, 50, 90, 40);
		add(zakresBledu);
		
		tZakresBledu = new JTextField();
		tZakresBledu.setBounds(450, 80, 100, 40);
		tZakresBledu.setToolTipText("Wpisz o ile maksymalnie mog¹ siê ró¿niæ dane liczbowe, aby program nie wzi¹³ tego za b³¹d");
		add(tZakresBledu);
		
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
		System.out.println(hist);
		if(hist2.isEmpty()) hist2 = "D:\\"; //--
		System.out.println(hist2);
		
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
			}
		}
		else if(source == bPrzegladaj2) {
			fc2 = new JFileChooser();
			fc2.setCurrentDirectory(new File(hist2)); //potem tutaj zmienic na hist2
			fc2.setDialogTitle("wybierz drugi folder");
			fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc2.setAcceptAllFileFilterUsed(false);
			
			if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog2 = fc2.getSelectedFile();
			}	
		}
		else if(source == banalizuj) {
			if(katalog1 == null) katalog1 = new File(hist);
			if(katalog2 == null) katalog2 = new File(hist2);
			listaPlikow1 = katalog1.listFiles();
			listaPlikow2 = katalog2.listFiles();
			progressBar.setValue(0);
			progressBar.setIndeterminate(true);
			File[] zawartosc1 = katalog1.listFiles();
			File[] zawartosc2 = katalog2.listFiles();
					
			JOptionPane.showMessageDialog(null, "Rozpoczyna siê proces porównywania plików...");
			
//			katalogLogow = katalog1.getParent();
//			System.out.println("Tutaj bedzie katalog: "+katalogLogow);
			
			File katalogLogow = new File(katalog1.getParent()+"\\Logi");			
			katalogLogow.delete();
			if(!katalogLogow.exists()) {
				if(!katalogLogow.mkdir()) 
					JOptionPane.showMessageDialog(null, "Nie mogê utworzyæ katalogu logów!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
			}

			System.out.println(katalogLogow.getAbsolutePath());
			
			//najpierw trzeba odfiltrowac pliki .csv			
			
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
				plik2 = new File(odfpliki2.get(i).getParent()+"\\"+odfpliki1.get(i).getName());
				if(!plik2.exists()) {
					JOptionPane.showMessageDialog(null, "Plik: "+plik2.getName()+ " nie istnieje!", "Nie istnieje podany plik!", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
//				log = new File(plik1.getParent() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
//						+  "_" 
//						+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
//						+ ".txt");				
				
				log = new File(katalogLogow.getAbsolutePath() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
						+  "_" 
						+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
						+ ".txt");	
				
				System.out.println("taka sciezka: " +katalogLogow.getAbsolutePath() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
						+  "_" 
						+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
						+ ".txt");
							
				int linia = 1;
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
				progres += miarka;
				
				aktualneZadanie.setText("Porownywanie: "+log.getAbsolutePath());
				aktualneZadanie.repaint(100);
				System.out.println("Progres: "+progres); 
				progressBar.setValue(progres);	
				progressBar.repaint(100);
			}
			JOptionPane.showMessageDialog(null, "Porównywanie ukoñczone!");
			progressBar.setValue(100);
			aktualneZadanie.setText("Trwa zrzucanie historii...");
			try {
				historiaZapis = new PrintWriter("conf.txt");
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Nie odnaleziono pliku conf.txt!", "conf.txt", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println("Katalog1 = "+katalog1.getAbsolutePath());
			historiaZapis.write(katalog1.getAbsolutePath());
			historiaZapis.println("");
			System.out.println("Katalog2 = "+katalog2.getAbsolutePath());
			historiaZapis.println(katalog2.getAbsolutePath());
			historiaZapis.close();
			aktualneZadanie.setText("Analiza zakoñczona");
			JOptionPane.showMessageDialog(null, "Logi zrzucone do: "+katalogLogow.getAbsolutePath());	
		}
	}
	public static void main(String[] args) {
//		new Thread(){
//			@Override
//			public void run() 	{
				FileCompareOknoLITE fco = new FileCompareOknoLITE();
				fco.setVisible(true);
//        	}
//		}.start();
	}

}
