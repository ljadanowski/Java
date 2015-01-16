import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;

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
	
	public void konwertujArkuszDoCsv(String sciezka) {
		FileInputStream fis;
		Workbook wb; 
		try {
			fis = new FileInputStream(sciezka);
			wb = WorkbookFactory.create(fis);
			xlxs2csv(wb.getSheetAt(0)); //zakladam ze nie ma wiecej arkuszy
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void xlxs2csv(Sheet sheet) {
		Row row = null;
		Cell cell = null;
        PrintWriter zapis = null;
        File plik = new File("D:\\workspace\\jakis.csv");

		try {
			zapis = new PrintWriter(plik);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				switch(cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						zapis.print(cell.getBooleanCellValue());
						System.out.println("Z case1: " +cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						zapis.print(cell.getNumericCellValue());
						System.out.println("Z case2: " +cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						zapis.print(cell.getStringCellValue());
						System.out.println("Z case3: " +cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
				        break;	
					case Cell.CELL_TYPE_ERROR:
				        break;
					case Cell.CELL_TYPE_FORMULA: 
						zapis.print(cell.getNumericCellValue());
						System.out.println("Z case4: " +cell.getNumericCellValue());
				        break;         
				}
				//System.out.print("\"" + row.getCell(j) + "\";");
				//zapis.print(row.getCell(j).toString());
				zapis.print(";");
			}
			zapis.println();
			//System.out.println();
		}
		zapis.close();	
	}
	public void porownajPliki(File plik1, File plik2) {
		//File plik1 = new File(sciezka1);
		//File plik2 = new File(sciezka2);
		int linia = 1;
		//String tekst;
		try {
			PrintWriter log = new PrintWriter(new File(plik1.getName() + ".csv"));
			Scanner skaner1 = new Scanner(plik1);
			Scanner skaner2 = new Scanner(plik2);
			while(skaner1.hasNext()) {
				if(!skaner1.nextLine().equals(skaner2.nextLine())) {
					log.println("Pliki sa rozne!\nLinia: " +linia);
					break;
				}
				linia++;	
			}
			log.close();
			skaner1.close();
			skaner2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		File directory = new File("D:\\raporty");
//		File[] contents = directory.listFiles();
//		for( File f : contents) 
//		 System.out.println(f.getAbsolutePath());
//		FileInputStream inp = null;
//		Workbook wb = null; 
//		try {
//			inp = new FileInputStream("D:\\workspace\\testowy.xlsx");
//			wb = WorkbookFactory.create(inp);
////			System.out.println(wb.getSheetAt(0).getSheetName()); //zakladam ze nie ma wiecej arkuszy
//			xlxs2csv(wb.getSheetAt(0)); //zakladam ze nie ma wiecej arkuszy
////			for(int i=0;i<wb.getNumberOfSheets();i++) {
////		        System.out.println(wb.getSheetAt(i).getSheetName());
////		        xlxs2csv(wb.getSheetAt(i));
////		    }
//			inp.close();
//		}
//		catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		FileCompareManager fcm = new FileCompareManager();
		fcm.konwertujArkuszDoCsv("D:\\workspace\\testowy.xlsx");
		//fcm.konwertujArkuszDoCsv("D:\\workspace\\EOP_UE_po_krajach_do_depozytów.xlsx");
	}
}
