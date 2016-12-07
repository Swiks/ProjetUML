package controle;

public class ControllerStock extends Controller{
	public static String getStock(){
		return catalogue.toString();
	}
	public static String[] getnomProduits(){
		String[] noms = catalogue.getNomProduits();
		if(noms == null)
			return new String[1];
		return catalogue.getNomProduits();
	}
}
