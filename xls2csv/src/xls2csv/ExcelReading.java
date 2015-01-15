package xls2csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReading {

	public static void echoAsCSV(Sheet sheet) {
	        Row row = null;
	        FileWriter zapis = null;
	        String tekst;
	        try {
				zapis = new FileWriter("jakis.csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
	        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	            row = sheet.getRow(i);
	            for (int j = 0; j < row.getLastCellNum(); j++) {
	            	tekst = "\"" + row.getCell(j) + "\";";
	                //System.out.print("\"" + row.getCell(j) + "\";");
	            	System.out.print(tekst);
	            	try {
						zapis.write(tekst);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            System.out.println();
	        }
	        try {
				zapis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		FileInputStream inp = null;
		Workbook wb = null; 
		try {
			inp = new FileInputStream("D:\\workspace_luna\\Java\\LPP files compare\\testowy.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(inp);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<wb.getNumberOfSheets();i++) {
            System.out.println(wb.getSheetAt(i).getSheetName());
            echoAsCSV(wb.getSheetAt(i));
        }
		try {
			inp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
