import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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


public class FileCompareOknoLITE extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton banalizuj, bPrzegladaj, bPrzegladaj2;
	private JLabel pierwszyPlik, drugiPlik;
	private JFileChooser fc1, fc2;
	private File katalog1, katalog2;
	private String sciezka1, sciezka2;
	private File[] listaPlikow1, listaPlikow2;
	private Scanner skaner1, skaner2, historiaOtworz;
	private PrintWriter logi, historiaZapis;
	private File plik1, plik2, log, historia;
	private String hist;
	
	public FileCompareOknoLITE() {
		setSize(600,400);
		setTitle("LPP FILES' COMPARER v1.0 LITE");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setVisible(true);
		
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
		
		//historia = new File("H:\\IT\\Business System Support\\ljadanowski\\conf.txt");
		historia = new File("D:\\conf.txt");
		try {
			historiaOtworz = new Scanner(historia);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//hist = "H:\\IT\\Business System Support\\ljadanowski";
		hist = historiaOtworz.nextLine();
		
//		historia = new File("H:\\IT\\Business System Support\\ljadanowski\\conf.txt");
//		try {
//			historiaOtworz = new Scanner(historia);
//		} catch (FileNotFoundException e1) {
//			JOptionPane.showMessageDialog(null, "Nie wczytano pliku z historia!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
//			//e1.printStackTrace();
//		} 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
				
		if(source == bPrzegladaj) {
			//Otwieranie pliku aby zapisac historie
			
//			hist = historiaOtworz.nextLine();
//			historiaOtworz.close();
			
			fc1 = new JFileChooser();
			fc1.setCurrentDirectory(new File(hist));
			fc1.setDialogTitle("wybierz pierwszy folder");
			fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc1.setAcceptAllFileFilterUsed(false);
			
			if(fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog1 = fc1.getSelectedFile();
				listaPlikow1 = katalog1.listFiles();
				//for(File f : listaPlikow1) System.out.println(f.getAbsolutePath());
			}	
//			try {
//				historiaZapis = new PrintWriter(historia);
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//			historiaZapis.write(katalog1.getAbsolutePath());
//			historiaZapis.close();
		}
		else if(source == bPrzegladaj2) {
			fc2 = new JFileChooser();
			fc2.setCurrentDirectory(new File(hist)); 
			fc2.setDialogTitle("wybierz drugi folder");
			fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc2.setAcceptAllFileFilterUsed(false);
			
			if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				katalog2 = fc2.getSelectedFile();
				listaPlikow2 = katalog2.listFiles();
				//for(File f : listaPlikow2) System.out.println(f.getAbsolutePath());
			}	
		}
		else if(source == banalizuj) {
			File[] zawartosc1 = katalog1.listFiles();
			File[] zawartosc2 = katalog2.listFiles();
					
			JOptionPane.showMessageDialog(null, "Teraz rozpoczyna siê proces porównywania plików...");
			
			//System.out.println("Tutaj bedzie katalog: "+zawartosc1[0].getParent());
			//File katalogLogow = new File(zawartosc1[0].getParent()+"\\Logi");
			
//			if(!katalogLogow.mkdir()) 
//				JOptionPane.showMessageDialog(null, "Nie mogê utworzyæ katalogu logów!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
			
			//najpierw trzeba odfiltrowac pliki .csv
			
			ArrayList<File> odfpliki1 = new ArrayList<File>();
			ArrayList<File> odfpliki2 = new ArrayList<File>();
//			
//			zawartosc1 = katalog1.listFiles();
//			zawartosc2 = katalog2.listFiles();
			
			for(File i : zawartosc1) {
				//System.out.println("Link: " +i.getAbsolutePath());
				if(i.getName().indexOf(".csv") != -1) 
					odfpliki1.add(i);
			}
			
			for(File i : zawartosc2) {
				//System.out.println("Link: " +i.getAbsolutePath());
				if(i.getName().indexOf(".csv") != -1) 
					odfpliki2.add(i);
			}
			
			if(odfpliki1.size() != odfpliki2.size()) JOptionPane.showMessageDialog(null, "Ró¿na iloœæ plików w katalogach!", "Uwaga!", JOptionPane.INFORMATION_MESSAGE);
//			System.out.println("ODF1 = "+odfpliki1.toString());
//			System.out.println("ODF2 = "+odfpliki2.toString());
			
			for(int i=0; i<odfpliki1.size(); i++) {
				plik1 = new File(odfpliki1.get(i).getAbsolutePath());
				plik2 = new File(odfpliki2.get(i).getAbsolutePath());
				
				log = new File(plik1.getParent() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
						+  "_" 
						+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
						+ ".txt");
				//log = new File(plik1.getParent() + "\\log_" + plik1.getName() + "_" +plik2.getName() + ".txt");				
				
				boolean takiSam = true;
				int linia = 1;
				try {
					logi = new PrintWriter(log);
					skaner1 = new Scanner(plik1);
					skaner2 = new Scanner(plik2);
				} catch (FileNotFoundException e1) {
						e1.printStackTrace();
				}
				while(skaner1.hasNext()) {
					if(! (skaner1.nextLine().equals(skaner2.nextLine() ))) {
						//System.out.println("Plik sa rozne!");
						takiSam = false;
						logi.println("Pliki sa rozne! Linia: " +linia);
					}
					linia++;	
				}

				if(takiSam) logi.println("Plik sa takie same!");
					
				logi.close();
				skaner1.close();
				skaner2.close();
				JOptionPane.showMessageDialog(null, "Ukoñczono!");
			}
		}
	}
	public static void main(String[] args) {
		FileCompareOknoLITE fco = new FileCompareOknoLITE();
		fco.setVisible(true);
	}

}
