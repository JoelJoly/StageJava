package entity.impl;
import titulaire.interfaces.Titulaire;


public class CompteEpargne extends Compte {
	private static final long serialVersionUID = 7080523507988317227L;
	private int taux; // pourcentage
	private float cumul = 0.0f;
	public CompteEpargne(float depotInitial, int taux) {
		super(depotInitial);
		this.taux = taux;
	}
	
	public CompteEpargne(float depotInitial, int taux, Titulaire titulaire) {
		super(depotInitial, titulaire);
		this.taux = taux;
	}
	
	public void calculerInterets()
	{
		if (this.getSolde() > 0) {
			float interets = this.getSolde() * taux / 100.f;
			crediter(interets);
			this.cumul += interets;
		}
	}

	@Override
	public String toString() {
		return "CompteEpargne [\n"+ super.toStringTab(1, true) + ",\n\ttaux=" + taux + ", cumul=" + cumul + "]";
	}

}
