import java.io.File;
import java.util.ArrayList;


public class DoTestow {

	public static void main(String[] args) {
		String tekst = "pliczek.csv";
		ArrayList<String> lista = new ArrayList<String>();
		File [] pliki = {
				new File("pliczek.csv"),
				new File("plik.xlsx")
		};
		
		for(File i : pliki) {
			System.out.println("Ostatni indeks " +i.getName().lastIndexOf('.'));
			System.out.println("Nazwa " +i.getName().substring(0, i.getName().lastIndexOf('.')));
			if(i.getName().indexOf(".csv") != -1) {
				System.out.println(i.getAbsolutePath());
				lista.add(i.getAbsolutePath());
			}
		}
		System.out.println("Lista: "+lista.toString());
		
		//System.out.println(tekst.indexOf(".csv"));

	}

}
