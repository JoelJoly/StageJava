package entity.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import exceptions.DebitNonAutorise;

import titulaire.interfaces.Titulaire;


public class Compte implements Serializable {
	private static final long serialVersionUID = 5575733085349501469L;
	private int numero;
	private float solde;
	private float decouvertAutorise;
	private ArrayList<Operation> operations = new ArrayList<Operation>();
	private Titulaire titulaire;
	private static int prochainNumero = 1;
	public Compte(float depotInitial)
	{
		this(depotInitial, null);
	}

	public Compte(float depotInitial, Titulaire titulaire)
	{
		solde = depotInitial;
		decouvertAutorise = depotInitial;
		numero = prochainNumero++;
		this.titulaire = titulaire;	
	}
	
	public void crediter(float montant) {
		if (montant > 0) {
			appliquerTransfert(montant);
		}
	}
	           
	public void debiter(float montant) throws DebitNonAutorise {
		if (montant > 0) {
			if (solde + decouvertAutorise < montant)
				throw new DebitNonAutorise("Montant " + montant + " dépasse le découvert autorisé " + decouvertAutorise + " (solde atuel" + solde + ") ");
			appliquerTransfert(-montant);
		}
	}	
	
	public float getSolde() {
		return solde;
	}

	
	public int getNumero() {
		return numero;
	}

	
	public Collection<String> getOperations() {
		ArrayList<String> operationStrings = new ArrayList<String>(); 
		for (Operation o : operations) {
			operationStrings.add(o.toString());
		}
		return operationStrings;
	}

	private void appliquerTransfert(float montant) {
		operations.add(new Operation(montant));
		solde += montant;		
	}

	@Override
	public String toString() {
		return toStringTab(0, false);
	}

	public String toString(boolean displaySummary) {
		return toStringTab(0, displaySummary);
	}

	protected final String toStringTab(int tabLevel, boolean displaySummary) {
		String tabs = "";
		for (int i = 0; i<tabLevel; ++i) tabs += '\t';
		String representation =				
				tabs + "Compte [numero=" + numero + ", solde=" + solde +
				", titulaire=" + (titulaire != null ? titulaire.getInfo() : "Kahuzac");
				if (! displaySummary) {
					representation += ", decouvertAutorise=" + decouvertAutorise + ", operations=[";
					for (Operation o : operations) {
						if (o != operations.get(0))
							representation += ","; 
						representation += "\n" + tabs + "\t" + o; 
					}
					representation += "]\n";
				}
			return representation + tabs + "\t]";
	}
	       
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(prochainNumero);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		prochainNumero = s.readInt();
	}
}
