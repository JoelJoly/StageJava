
public class Config {

	public Config() {
		super();
	}
	
	public Robot Configurer() {
		
		// definition des tresors
		Tresor fruit1 = new Fruit("Pomme", 5, "Verte");
		Tresor fruit2 = new Fruit("Poire", 5, "Jaune");
		Tresor chaise1 = new Chaise("Stark", 50);
		Tresor chaise2 = new Chaise("LouisXIV", 100);
		Tresor bouteille1 = new Bouteille("Rhum", 10, 50);
		Tresor bouteille2 = new Bouteille("EauDeVie", 10, 55);
		
		// definition des pieces
		Piece entree = new Piece("Entree", true);
		Piece salle = new Piece("Salle", false);
		Piece chambre = new Piece("Chambre", false);
		Piece cuisine = new Piece("Cuisine", false);
		Piece cellier = new Piece("Cellier", false);
		
		entree.setIssue(PC.Sud, salle);
		salle.setIssue(PC.Est, chambre);
		salle.setIssue(PC.Nord, entree);
		salle.ajouterTresor(fruit1);
		salle.ajouterTresor(chaise1);
		chambre.setIssue(PC.Sud, cuisine);
		chambre.ajouterTresor(bouteille1);
		cuisine.setIssue(PC.Ouest, cellier);
		cuisine.ajouterTresor(chaise2);
		cuisine.ajouterTresor(fruit2);
		cellier.setIssue(PC.Nord, salle);
		cellier.ajouterTresor(bouteille2);
		
		Robot r = new Robot(entree, 100);
		return r;
	}
}
