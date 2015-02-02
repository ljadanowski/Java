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
	}
	
	public static ArrayList<Double> znajdzLiczbe(String linia) {
		String liczba = "";
		ArrayList<String> listaLiczbString = new ArrayList<String>();
		ArrayList<Double> listaLiczb = new ArrayList<Double>();
		StringBuffer liczbaB = new StringBuffer(liczba); 
		
		for(int i=0; i<linia.length(); i++) {
			if(Character.isDigit(linia.charAt(i))) {
				liczbaB.append(linia.charAt(i));
			}
			else if(linia.charAt(i) == ',') {
				liczbaB.append('.');
				int k = i+1;
				while(k<linia.length() && Character.isDigit(linia.charAt(k)) ) {
					liczbaB.append(linia.charAt(k++)); 
				}
				
				listaLiczbString.add(liczbaB.toString());
				liczba = "";
				liczbaB = new StringBuffer(liczba); 
				i = k;
			}
				
		}
		for(int s = 0; s<listaLiczbString.size(); s++) 
			listaLiczb.add(Double.parseDouble((String) listaLiczbString.get(s)));
		return listaLiczb;
	}
	
	public static void main(String[] args) {
//		FCLManager fcl = null;
//		String tekst = ";.;";
//		System.out.println(FCLManager.czyPustaLinia(tekst));
		String tekst = "Alicja;;;;0,345;;ab;;1,8;0,56;ss7,89;";
//		System.out.println("Tekst po wywolaniu funkcji: "+wczytajSameWyrazy(tekst));
		System.out.println("Tekst po wywolaniu funkcji2: "+znajdzLiczbe(tekst));
		ArrayList listaLiczbString = new ArrayList<String>();
		listaLiczbString = znajdzLiczbe(tekst);
		for(int s=0; s<listaLiczbString.size(); s++) System.out.println(listaLiczbString.get(s));
	}
}
