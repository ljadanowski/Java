import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;


public class ReadExcel {
//	
//	private String inputFile;
//	
//	public void setInputFile(String inputFile) {
//	    this.inputFile = inputFile;
//	}
//	public void read() {
//		File inputWorkbook = new File(inputFile);
//	    Workbook w;
//	    w = Workbook.getWorkbook(inputWorkbook);
//	    
//	}
	public void read(String inputFile) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;
		    
		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();
		    int cols = 0; // No of columns
		    int tmp = 0;
//		    for(int i = 0; i < 10 || i < rows; i++) {
//		        row = sheet.getRow(i);
//		        if(row != null) {
//		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//		            if(tmp > cols) cols = tmp;
//		            System.out.println("Wiersz: "+tmp);
//		        }
//		    }
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
		ReadExcel re = new ReadExcel();
		re.read("D:\\workspace_luna\\LPP files compare\\testowy.xls");

	}

}
