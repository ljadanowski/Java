
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
//	public static void main(String[] args) {
//		FCLManager fcl = null;
//		String tekst = ";.;";
//		System.out.println(FCLManager.czyPustaLinia(tekst));
//	}
}
