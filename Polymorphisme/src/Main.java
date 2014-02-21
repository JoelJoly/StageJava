
public class Main {

	private static Personne populace[] = new Personne[3];

	public static void main(String[] args) {
		Personne p1 = new Boulanger("Bidochon");
		Stagiaire s1 = new Stagiaire("David", "Java");
		Boulanger b1 = new Boulanger("Poilane");
		populace[0] = p1;
		populace[1] = s1;
		populace[2] = b1;
		for (int i = 0; i < populace.length; ++i) {
			populace[i].travailler();
			if (populace[i] instanceof Stagiaire)
				System.out.println(((Stagiaire)populace[i]).getCours());
		}
		Personne p2 = new Boulanger("Bidochon");
		System.out.println(p2.equals(p1) ? "Pareil" : "Différent");
		System.out.println(p1.toString());
		System.out.println(p1.getClass().getDeclaredFields().length);
		System.out.println(p1.getClass().getMethods()[0]);
	}

}
