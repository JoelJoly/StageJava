
public class Stagiaire extends Personne {
	private String cours;

	public Stagiaire(String nom, String cours) {
		super(nom);
		this.cours = cours;
	}
	
	@Override
	public void travailler()
	{
		System.out.println("Je suis le cours " + cours);
	}

	public String getCours() {
		return cours;
	}

}
