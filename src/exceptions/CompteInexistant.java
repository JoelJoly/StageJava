package exceptions;

public class CompteInexistant extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8912653878357345682L;

	public CompteInexistant(int numeroCompte) {
		super("Compte inexistant: " + numeroCompte);
	}
	
}
