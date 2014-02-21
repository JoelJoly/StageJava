
public abstract class Personne {
	private String nom;
	public Personne(String nom) {
		this.nom = nom;
	}

	public abstract void travailler();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Personne))
			return false;
		Personne other = (Personne) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + "]";
	}
	public boolean equals2(Object o) {
		if (o instanceof Personne) {
			return this.nom.equals(((Personne)o).nom);
		}
		return false;
	}
}
