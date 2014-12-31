import java.io.File;


public class FileCompareManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File directory = new File("D:\\raporty");
		File[] contents = directory.listFiles();
		for ( File f : contents) 
		 System.out.println(f.getAbsolutePath());
		
	}
}
