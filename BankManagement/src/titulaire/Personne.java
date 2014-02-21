package titulaire;
import titulaire.interfaces.Titulaire;


public class Personne implements Titulaire {
	private static final long serialVersionUID = 8483108845046184974L;
	private String nom;
	private String prenom;
	
	public Personne(String nom, String prnom) {
		this.nom = nom;
		this.prenom = prnom;
	}
	
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Personne[Nom : " + nom + ", Prénom : " + prenom + "]";
	}

}
