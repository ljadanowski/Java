import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelXlsx {
	
	public void read(String inputFile) {
		File plik = new File(inputFile);
		try {
			FileInputStream fs = new FileInputStream(plik);
			//XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
		    XSSFCell cell;
		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();
		    int cols = 0; // No of columns
		    int tmp = 0;
		    
		    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	            row = sheet.getRow(i);
	            for (int j = 0; j < row.getLastCellNum(); j++) {
	                System.out.print("\"" + row.getCell(j) + "\";");
	            }
	            System.out.println();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		ReadExcelXlsx re = new ReadExcelXlsx();
		re.read("D:\\LPP\\20141231_RMS_stan_IW_per_season.xlsx");
	}

}
