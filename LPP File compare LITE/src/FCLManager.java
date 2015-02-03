import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;


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
//	public static ArrayList<String> wczytajSameWyrazy(String linia) {
//		String wyraz = "";
//		ArrayList<String> listaDanych = new ArrayList<String>();
//		StringBuffer wyrazB = new StringBuffer(wyraz);
//		
//		for(int i = 0; i<linia.length(); i++) {
//			if(linia.charAt(i) != ';') {
//				if(linia.charAt(i) == ',') 
//					wyrazB.append('.');
//				else 
//					wyrazB.append(linia.charAt(i));
//			}	
//			else {
//				if(!wyrazB.toString().isEmpty())
//					listaDanych.add(wyrazB.toString());
//				wyraz = "";
//				wyrazB = new StringBuffer(wyraz);
//				while(i < linia.length() && linia.charAt(i) == ';') {
//					i++;
//					continue;
//				}
//				i--;	
//			}
//		}
//		
//		return listaDanych;
//	}
	
	public static ArrayList<Double> znajdzLiczbe(String linia) {
		String liczba = "";
		ArrayList<String> listaLiczbString = new ArrayList<String>();
		ArrayList<Double> listaLiczb = new ArrayList<Double>();
		StringBuffer liczbaB = new StringBuffer(liczba); 
		
		for(int i=0; i<linia.length(); i++) {
			if(Character.isDigit(linia.charAt(i))) {
				liczbaB.append(linia.charAt(i));
				if( i+1 < linia.length() && linia.charAt(i+1) == ';') {
					listaLiczbString.add(liczbaB.toString());	
					liczba = "";
					liczbaB = new StringBuffer(liczba); 
				}	
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
	
	public static ArrayList<String> wyciagnijTekst(ArrayList<String> dane) {
		String textPattern = "([a-zA-Z,\\- _0-9]*)"; 
		ArrayList<String> tekst = new ArrayList<String>();
		
		for(String i : dane) {
			if(Pattern.matches(textPattern, i))
				tekst.add(i);
		}
		return tekst;
	}
	
	public static ArrayList<Double> wyciagnijLiczby(ArrayList<String> dane) {
		String decimalPattern = "([0-9]*)\\.([0-9]*)";
		ArrayList<Double> liczby = new ArrayList<Double>();
		
		for(String i : dane) {
			if(Pattern.matches(decimalPattern, i))
				liczby.add(Double.parseDouble(i));
		}
		return liczby;
	}
	
	public static boolean porownajKolekcjeDouble(ArrayList<Double> kolekcja1, ArrayList<Double> kolekcja2, double roznica ) {	
		if(kolekcja1.size() != kolekcja2.size())
			return false;
		
		for(int i=0; i<kolekcja1.size(); i++) {
			if(Math.abs(kolekcja1.get(i) - kolekcja2.get(i)) > roznica) 
				return false;
		}
		return true;
	}
	public static double konwertujNaDouble(String tekst) {
		int indeks;
		
		if((indeks = tekst.indexOf(',')) != -1) 
			tekst = tekst.replace(',', '.');
		return Double.parseDouble(tekst);
	}
	public static void main(String[] args) {
		String tekst = "0.17213";
		String tekst2 = "1,72";
		System.out.println(konwertujNaDouble(tekst));
		System.out.println(konwertujNaDouble(tekst2));
	}
}
