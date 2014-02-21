
public class Fruit extends Tresor {
	private String couleur;
	
	public Fruit(String n, int p, String c) {
		super(n, p);
		couleur = c;
	}

	public String toString() {
		String r = super.toString();
		r = r + "Fruit : [couleur : " + couleur + "]\n";
		return r;
	}
}
