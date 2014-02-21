package titulaire;
import titulaire.interfaces.Titulaire;


public class Entreprise implements Titulaire {
	private static final long serialVersionUID = 5704987839955881566L;
	private String nom;
	private int siret;	
	
	public Entreprise(String nom, int siret) {
		this.nom = nom;
		this.siret = siret;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Entreprise[Nom : " + nom + ", SIRET : " + siret + "]";
	}

}
