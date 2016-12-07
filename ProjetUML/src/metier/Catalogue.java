package metier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalogue  implements I_Catalogue{

	private ArrayList<I_Produit> produits;
	
	public Catalogue() {
		produits = new ArrayList<I_Produit>();
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		for(I_Produit prod : produits)
			if(prod.getNom() == produit.getNom() || produit.getPrixUnitaireHT() <= 0)
				return false; 

		return produits.add(produit);
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		if(prix <= 0.0 || qte < 0) 
			return false;
		for(I_Produit prod : produits)
			if(prod.getNom() == nom ) return false;
		return produits.add(new Produit(nom,prix,qte));
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int nb_add = 0;
		
		for(I_Produit prod : l)
			if(addProduit(prod))
				nb_add++;
		
		return nb_add;
	}

	@Override
	public boolean removeProduit(String nom) {
		int i = 0;
		for(I_Produit prod : produits){
			if(prod.getNom() == nom){
				produits.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for(I_Produit prod : produits)
			if(prod.getNom() == nomProduit)
				return prod.ajouter(qteAchetee);
		
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		int i = 0;
		for(I_Produit prod : produits)
			if(prod.getNom() == nomProduit)
				return prod.enlever(qteVendue);

		return false;
	}

	@Override
	public String[] getNomProduits() {
		if(produits.size() == 0)
			return null;
		
		String[] nomProduits = new String[produits.size()];
		
		int i=0;
		for(I_Produit prod : produits)
			nomProduits[i] = prod.getNom();
		
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double montant = 0;
		for(I_Produit prod : produits)
			montant += prod.getPrixStockTTC();
		return montant*100/100;
	}

	@Override
	public void clear() {
		produits.clear();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(I_Produit prod : produits)
			sb.append(prod.getNom()+" - prix HT : "+String.format("%.2f",prod.getPrixUnitaireHT())+" € - prix TTC : "+String.format("%.2f", prod.getPrixUnitaireTTC())+" € - quantité en stock : "+prod.getQuantite()+"\n");
		sb.append("\n" + "Montant total TTC du stock : "+String.format("%.2f",getMontantTotalTTC())+" €");
		return sb.toString();
	}
}
