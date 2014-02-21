
public abstract class Tresor {
	static int nombre = 0;
	private String nom;
	private int poids;
	
	public Tresor(String n, int p){
		nom = n;
		poids = p;
		nombre++;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getPoids() {
		return poids;
	}
	
	public String toString() {
		return "Tresor : [nombre : " + nombre + ", nom : " + nom + ", poids : " + poids + "]\n";
	}
}
