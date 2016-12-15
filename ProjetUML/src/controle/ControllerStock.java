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
	
	public static boolean acheterProduit(Object nom, String quantite){
		if (nom instanceof String)
		try {
			int qte = Integer.parseInt(quantite);
			return catalogue.acheterStock((String) nom, qte);
		} catch (Exception e) {}
		return false;
	}
	
}
