package metier;

public class Produit implements I_Produit{

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static double tauxTVA = 0.2;
	
	public Produit(String nom, double prixUnitaireHT,int quantiteStock){
		this.quantiteStock = quantiteStock;
		this.nom = nom.replaceAll("\t"," ").trim();
		this.prixUnitaireHT = prixUnitaireHT;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {
		quantiteStock += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue <= quantiteStock){
			quantiteStock-=qteVendue;
			return true;
		}
		return false;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return prixUnitaireHT*(1+tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return quantiteStock*getPrixUnitaireTTC();
	}
}
