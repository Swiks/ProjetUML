package metier;

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
		if(produit == null)
			return false;
		
		if(!isProduitValide(produit))
			return false;
		
		for(I_Produit prod : produits)
			if(prod.getNom().equals(produit.getNom()))
				return false;

		return produits.add(produit);
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		I_Produit p = new Produit(nom,prix,qte);
		
		if(!isProduitValide(p))
			return false;
		
		for(I_Produit prod : produits)
			if(prod.getNom().equals(p.getNom()))
				return false;
		return produits.add(p);
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		if(l == null)
			return 0;
		
		int nb_add = 0;
		
		for(I_Produit prod : l)
			if(addProduit(prod))
				nb_add++;
		
		return nb_add;
	}

	@Override
	public boolean removeProduit(String nom) {
		if(nom == null)
			return false;
		
		nom = nom.trim();
		int i = 0;
		for(I_Produit prod : produits){
			if(prod.getNom().equals(nom)){
				produits.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(qteAchetee<=0)
			return false;
		nomProduit = nomProduit.trim();
		for(I_Produit prod : produits)
			if(prod.getNom().equals(nomProduit))
				return prod.ajouter(qteAchetee);
		
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if(qteVendue<=0)
			return false;
		
		nomProduit = nomProduit.trim();
		for(I_Produit prod : produits)
			if(prod.getNom().equals(nomProduit))
				return prod.enlever(qteVendue);

		return false;
	}

	@Override
	public String[] getNomProduits() {
		if(produits.size() == 0)
			return new String[0];
		
		String[] nomProduits = new String[produits.size()];
		
		int i=0;
		for(I_Produit prod : produits){
			nomProduits[i] = prod.getNom();
			i++;
		}
		
		Arrays.sort(nomProduits);
		
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double montant = 0;
		for(I_Produit prod : produits)
			montant += prod.getPrixStockTTC();
		montant = Math.round(montant*100);
		return montant/100;
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
	
	private boolean isProduitValide(I_Produit prod){ 
		return (prod.getNom() != null) &&
				(prod.getNom().equals(prod.getNom().trim())) &&
				(prod.getQuantite() >= 0) && 
				(prod.getPrixUnitaireHT() > 0) && 
				(prod.getPrixUnitaireTTC() > 0);
	}
}
