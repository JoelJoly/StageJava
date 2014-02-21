import java.util.ArrayList;
import java.util.Collection;


public class Piece {

	private ArrayList<Tresor> lesTresors;
	private Piece lesIssues[] = new Piece[4];
	private String nom;
	private boolean depart;

	public Piece(String nom, boolean depart) {
		this.nom = nom;
		this.depart = depart;
		lesTresors = new ArrayList<Tresor>(); 
	}
	
	public Piece go(PC pc){
		int i = pc.ordinal();
		return lesIssues[i];
	}
	
	public String getNom() {
		return nom;
	}

	public Tresor prendre(String tresor) throws TresorNonExistant{
		for(Tresor t:lesTresors)
		{
			if ( t.getNom().equals(tresor) ){
				lesTresors.remove(t);
				return t;				
			}
		}
		throw new TresorNonExistant("le tresor " + tresor + " n'existe pas dans la piece");
	}

	public void deposer(Tresor t) throws ExceptionGagne{
		lesTresors.add(t);
		if (Tresor.nombre == lesTresors.size() && depart) {
			throw new ExceptionGagne("");
		}
	}
	
	public void setIssue(PC pc, Piece piece){
		lesIssues[pc.ordinal()] = piece;
	}
	
	public void ajouterTresor(Tresor t){
		try {
			deposer(t);
		} catch (ExceptionGagne e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Collection<String> yaquoi() {
		Collection<String> res = new ArrayList<String>();
		for (Tresor t : lesTresors)
			res.add(t.getNom());
		return res;
	}
	
}
