package controle;

import metier.Produit;

public class ControllerProduit extends Controller{
	public static boolean nouveauProduit(String nom, String prix,String qte){
		double parse_prix = -1;
		int parse_qte = -1;
		
		try{
			parse_prix = Double.parseDouble(prix);
			parse_qte = Integer.parseInt(qte);
		}catch(Exception e){}
		
		if(parse_prix >= 0 && parse_qte >= 0)
			return catalogue.addProduit(nom, Double.parseDouble(prix), Integer.parseInt(qte));
		return false;
	}
	
	public static boolean supprimerProduit(Object p){
		
		if (p instanceof String)
			return catalogue.removeProduit((String) p);
		
		return false;
	}
	
}