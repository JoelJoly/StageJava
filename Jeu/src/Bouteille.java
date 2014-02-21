
public class Bouteille extends Tresor {
	private float degre;
	
	public Bouteille(String n, int p, float d) {
		super(n, p);
		degre = d;
	}

	public String toString() {
		String r = super.toString();
		r = r + "Bouteille : [degre : " + degre + "]\n";
		return r;
	}
}
