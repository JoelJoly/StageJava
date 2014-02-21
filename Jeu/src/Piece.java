import java.util.ArrayList;
import java.util.Collection;


public class Piece {

	private String nom;
	public Piece() {
		this.nom = "MaPiece"; 
	}
	private Piece(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}

	public Piece go(PC pc) {
		if (pc == PC.Est) {
			return new Piece("UneAutrePiece");
		}
		return null;
	}
	public Tresor prendre(String tresor) throws TresorNonExistant {
		if (tresor == "Tresor1")
			return new Fruit();
		throw new TresorNonExistant();
	}
	public void deposer(Tresor t) throws Gagne {		
	}
	public Collection<String> yaquoi() {
		ArrayList<String> a  = new ArrayList<String>();
		a.add("Tresor1");
		a.add("Tresor2");
		return a;
	}

}
