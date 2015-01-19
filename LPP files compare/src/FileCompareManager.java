import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileCompareManager {
	
	File plik;
	FileWriter zapis;
	File[] tymczasowa;
	ArrayList<String> lista;
	public void konwertujArkuszDoCsv(File sciezka) {
		FileInputStream fis;
		Workbook wb; 
		try {
			fis = new FileInputStream(sciezka);
			wb = WorkbookFactory.create(fis);
			xlxs2csv(wb.getSheetAt(0), sciezka); //zakladam ze nie ma wiecej arkuszy
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void xlxs2csv(Sheet sheet, File sciezka) {
		Row row = null;
		Cell cell = null;
        PrintWriter zapis = null;
        //File plik = new File("D:\\workspace\\jakis.csv");
        String fileName = sciezka.getName();
        if(fileName.substring(fileName.lastIndexOf('.') + 1 ).equals("xlsx") ) 
        	fileName = fileName.replace("xlsx", "csv");
		else if(fileName.substring(fileName.lastIndexOf('.') + 1 ).equals("xls")) 
			fileName = fileName.replace("xls", "csv");
        
        String sciezkaDoZapisu = sciezka.getParent() + "\\" + fileName;
        
        File plik = new File(sciezkaDoZapisu);
		try {
			zapis = new PrintWriter(plik);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Blad utworzenia pliku!", "Blad", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				switch(cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						zapis.print(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						zapis.print(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						zapis.print(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
				        break;	
					case Cell.CELL_TYPE_ERROR:
				        break;
					case Cell.CELL_TYPE_FORMULA: 
						zapis.print(cell.getNumericCellValue());
				        break;         
				}
				zapis.print(";");
			}
			zapis.println();
		}
		zapis.close();	
	}
	public boolean porownajPliki(String sciezka1, String sciezka2) {
		File plik1 = new File(sciezka1);
		File plik2 = new File(sciezka2);
		File log = new File(plik1.getParent() + "\\log_" + plik1.getName() + "_" +plik2.getName() + ".txt");
		boolean takiSam = true;
		int linia = 1;
		//String tekst;
		try {
			PrintWriter logi = new PrintWriter(log);
			Scanner skaner1 = new Scanner(plik1);
			Scanner skaner2 = new Scanner(plik2);
			while(skaner1.hasNext()) {
				if(! (skaner1.nextLine().equals(skaner2.nextLine() ))) {
					System.out.println("Plik sa rozne!");
					takiSam = false;
					logi.println("Pliki sa rozne! Linia: " +linia);
					//break;
				}
				linia++;	
			}

			if(takiSam) logi.println("Plik sa takie same!");
			
			logi.close();
			skaner1.close();
			skaner2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return takiSam;
	}
	public File[] znajdzCsv(File tablica[]) {
		System.out.println("Dlugosc: " +tablica.length);
		tymczasowa = new File[tablica.length];
		int index = 0;
		for(File i : tablica) {
			System.out.println("Link: " +i.getAbsolutePath());
			if(i.getName().indexOf(".csv") != -1) {
				tymczasowa[index++] = i.getAbsoluteFile();
			}
		}
		for(File i : tymczasowa) System.out.println("Wyciagniete: " +i.getAbsolutePath());
		return tymczasowa;
	}
	public ArrayList<String> znajdzCsv2(File tablica[]) {
		lista = new ArrayList<String>();
		
		System.out.println("Dlugosc: " +tablica.length);
		int index = 0;
		for(File i : tablica) {
			System.out.println("Link: " +i.getAbsolutePath());
			if(i.getName().indexOf(".csv") != -1) {
				lista.add(i.getAbsolutePath());
			}
		}
		for(File i : tymczasowa) System.out.println("Wyciagniete: " +i.getAbsolutePath());
		return lista;
	}
	public static void main(String[] args) {

	}
}
