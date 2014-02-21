
public class Boulanger extends Personne implements Vivable {

	public Boulanger(String nom) {
		super(nom);
	}

	@Override
	public void travailler() {
		System.out.println("Je fais du pain");
	}

	@Override
	public void manger() {
		System.out.println("Je mange... du pain");
	}

	@Override
	public void mourir() {
		System.out.println("Arggggh...");
	}
	
	
}
