import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


public class FileComparatorBI extends SwingWorker<Void, Void>{
	File[] listaPlikow1, listaPlikow2;
	File katalog1, katalog2;
	String sciezka1, sciezka2;
	Scanner skaner1, skaner2, historiaOtworz;
	PrintWriter logi, historiaZapis;
	File plik1, plik2, log, historia;
	String hist, hist2, linia1, linia2, tymczasowa1, tymczasowa2;
	double roznica;
	
	public FileComparatorBI(File katalog1, File katalog2, double roznica) {
		super();
		this.katalog1 = katalog1;
		this.katalog2 = katalog2;
		this.roznica  = roznica;
		
		this.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(getProgress());
				FileCompareOknoLITE.progressBar.setValue(getProgress());
			}
		});
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		listaPlikow1 = katalog1.listFiles();
		listaPlikow2 = katalog2.listFiles();
		
		File[] zawartosc1 = katalog1.listFiles();
		File[] zawartosc2 = katalog2.listFiles();
		
		JOptionPane.showMessageDialog(null, "Rozpoczyna siê proces porównywania plików...");
		
		File katalogLogow = new File(katalog1.getParent()+"\\Logi");			
		katalogLogow.delete();
		if(!katalogLogow.exists()) {
			if(!katalogLogow.mkdir()) 
				JOptionPane.showMessageDialog(null, "Nie mogê utworzyæ katalogu logów!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
		}
		
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
		
		int miarka = 100/(odfpliki1.size()+1);
		int progres = miarka;
		FileCompareOknoLITE.progressBar.setValue(progres);
		FileCompareOknoLITE.aktualneZadanie.setText("Porówywanie: "+odfpliki1.get(0).getName()+"...");
		for(int i=0; i<odfpliki1.size(); i++) {
			boolean takiSam = true;
			plik1 = new File(odfpliki1.get(i).getAbsolutePath());
			plik2 = new File(odfpliki2.get(i).getParent()+"\\"+odfpliki1.get(i).getName());
			if(!plik2.exists()) {
				JOptionPane.showMessageDialog(null, "Plik: "+plik2.getName()+ " nie istnieje!", "Nie istnieje podany plik!", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
//			log = new File(plik1.getParent() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
//					+  "_" 
//					+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
//					+ ".txt");				
			
			log = new File(katalogLogow.getAbsolutePath() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
					+  "_" 
					+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
					+ ".txt");	
			
			System.out.println("taka sciezka: " +katalogLogow.getAbsolutePath() + "\\log_" +plik1.getName().substring(0, plik1.getName().lastIndexOf('.'))
					+  "_" 
					+ plik2.getName().substring(0, plik2.getName().lastIndexOf('.'))
					+ ".txt");
						
			long linia = 1;
			try {
				logi = new PrintWriter(log);
				skaner1 = new Scanner(plik1);
				skaner2 = new Scanner(plik2);
			} catch (FileNotFoundException e1) {
					e1.printStackTrace();
			}
			FileCompareOknoLITE.aktualneZadanie.setText("Analizowanie pliku: " + plik1.getName());
			//-----------------------------------------------------------------------
			while(skaner1.hasNext()) {
				linia1 = FCLManager.wczytajSameWyrazy(tymczasowa1=skaner1.nextLine()); //--
				if(skaner2.hasNext()) {
					linia2 = FCLManager.wczytajSameWyrazy(tymczasowa2=skaner2.nextLine());
					if(! (linia1.equals(linia2 ))) {
						boolean czy_rzeczywiscie_rowne = 
								FCLManager.porownajKolekcjeDouble(
								FCLManager.znajdzLiczbe(tymczasowa1), 
								FCLManager.znajdzLiczbe(tymczasowa2), 
								roznica);
						if(czy_rzeczywiscie_rowne) takiSam = true;
						else {
							takiSam = false;
							logi.println("Pliki sa rozne! Linia: " +linia);
						}
						
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
				FileCompareOknoLITE.aktualneZadanie.setText("Trwa analiza pliku "+plik2.getName()+"...");
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
			
			FileCompareOknoLITE.aktualneZadanie.setText("Porownywanie: "+log.getAbsolutePath());
			System.out.println("Progres: "+progres); 
			FileCompareOknoLITE.progressBar.setValue(progres);	
		}
		FileCompareOknoLITE.progressBar.setValue(100);
		JOptionPane.showMessageDialog(null, "Porównywanie ukoñczone!");	
		FileCompareOknoLITE.aktualneZadanie.setText("Trwa zrzucanie historii...");
		try {
			historiaZapis = new PrintWriter("conf.txt");
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Nie odnaleziono pliku conf.txt!", "conf.txt", JOptionPane.ERROR_MESSAGE);
		}
		historiaZapis.write(katalog1.getAbsolutePath());
		historiaZapis.println("");
		historiaZapis.println(katalog2.getAbsolutePath());
		historiaZapis.close();
		FileCompareOknoLITE.aktualneZadanie.setText("Analiza zakoñczona");
		JOptionPane.showMessageDialog(null, "Logi zrzucone do: "+katalogLogow.getAbsolutePath());
		return null;	
	}

	@Override
	protected void done() {
		super.done();
		System.out.println("Koniec porównywania!");
	}

	@Override
	protected void process(List<Void> chunks) {
		// TODO Auto-generated method stub
		super.process(chunks);
	}
	
}

