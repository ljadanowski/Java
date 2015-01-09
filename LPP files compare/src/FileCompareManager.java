import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileCompareManager {
	File plik;
	Scanner scanner;
	FileWriter zapis;
	public void convert2csv(String sciezka, String separator) {
	    plik = new File(sciezka);
	    try {
			zapis = new FileWriter(plik);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
		    scanner = new Scanner(plik);
			while(scanner.hasNext()) {
				;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.close();	
		try {
			zapis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		File directory = new File("D:\\raporty");
//		File[] contents = directory.listFiles();
//		for ( File f : contents) 
//		 System.out.println(f.getAbsolutePath());
		
	}
}
