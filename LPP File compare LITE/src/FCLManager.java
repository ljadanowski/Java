import java.io.File;
import java.util.ArrayList;


public class FCLManager {
	public static boolean czyPustaLinia(String linia) {
		if(linia.isEmpty()) return false;
		for(int i=0; i<linia.length(); i++) {
			if(linia.charAt(i) != ';') {
				return false;
			}
		}
		return true;
	}
	public static String wczytajSameWyrazy(String linia) {
		String zwracany = "";
		StringBuffer zwracanyB = new StringBuffer(zwracany);
		
		for(int i = 0; i<linia.length(); i++) {
			if(linia.charAt(i) != ';') 
				zwracanyB.append(linia.charAt(i));		
		}
		
		return zwracanyB.toString();
	}
	public static void kopiujPlikiZKatalogu(File katalogZrodlowy, File katalogDocelowy, String rozszerzenie) {
		File[] listaPlikow1 = katalogZrodlowy.listFiles(); 
		File[] listaPlikow2 = katalogDocelowy.listFiles(); 
		ArrayList <File> odflista1 = new ArrayList<File>();
		for(File i : listaPlikow1) {
			if(i.getAbsolutePath().endsWith(rozszerzenie)) 
				odflista1.add(i);	
		}
		
		//rozszerzenie.endsWith(".txt");
	}
	public static void main(String[] args) {
//		FCLManager fcl = null;
//		String tekst = ";.;";
//		System.out.println(FCLManager.czyPustaLinia(tekst));
		String tekst = ";;Ala;Tomek;;;Alicja;;;;;;;;";
		System.out.println("Tekst po wywolaniu funkcji: "+wczytajSameWyrazy(tekst));
	}
}
