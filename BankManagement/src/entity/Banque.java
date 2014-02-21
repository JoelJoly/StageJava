package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.impl.Compte;
import entity.impl.CompteEpargne;
import exceptions.CompteInexistant;
import exceptions.DebitNonAutorise;

import titulaire.interfaces.Titulaire;

public class Banque implements Serializable {
	private static final long serialVersionUID = 1750606226372971499L;
	private static Banque singleton;
	private Map<Integer, Compte> comptes;
	private String name;
	
	protected Banque(String name) {
		comptes = new HashMap<Integer, Compte>();
		this.name = name;
	}

	public static Banque getInstance() {
		if (singleton == null) {
			singleton = new Banque("Banque suisse");
		}
		return singleton;
	}

	public int creerCompte(float depotInitial, Titulaire titulaire) {
		Compte nouveauCompte = new Compte(depotInitial, titulaire);
		int numeroCompte = nouveauCompte.getNumero(); 
		comptes.put(numeroCompte, nouveauCompte);
		return numeroCompte;
	}

	public int creerCompte(float depotInitial, Titulaire titulaire, int taux) {
		Compte nouveauCompte = new CompteEpargne(depotInitial, taux, titulaire);
		int numeroCompte = nouveauCompte.getNumero(); 
		comptes.put(numeroCompte, nouveauCompte);
		return numeroCompte;
	}
	
	public void supprimerCompte(int numeroCompte) throws CompteInexistant {
		if (comptes.remove(numeroCompte) == null)
			throw new CompteInexistant(numeroCompte);
	}
	
	public void crediter(int numeroCompte, float montant) throws CompteInexistant {
		Compte compte = comptes.get(numeroCompte);
		if (compte == null)
			throw new CompteInexistant(numeroCompte);
		compte.crediter(montant);
	}

	public void debiter(int numeroCompte, float montant) throws CompteInexistant, DebitNonAutorise {
		Compte compte = comptes.get(numeroCompte);
		if (compte == null)
			throw new CompteInexistant(numeroCompte);
		compte.debiter(montant);
	}
	
	public Collection<String> getOperations(int numeroCompte) throws CompteInexistant {
		Compte compte = comptes.get(numeroCompte);
		if (compte == null)
			throw new CompteInexistant(numeroCompte);
		return compte.getOperations();
	}
	
	public void calculerInterets() {
		for (Compte c : comptes.values()) {
			if (c instanceof CompteEpargne) {
				((CompteEpargne)c).calculerInterets();
			}
		}
	}
	
	public List<Integer> getComptes() {
		ArrayList<Integer> numeroComptes = new ArrayList<Integer>();
		for (Map.Entry<Integer, Compte> entry : comptes.entrySet()) {
			numeroComptes.add(entry.getKey());
		}
		return numeroComptes;
	}
	@Override
	public String toString() {
		String representation = 
			"Banque [comptes=[";
		Collection<Compte> compteIntances = comptes.values();
		for (Compte c : compteIntances) {
			if (c != compteIntances.iterator().next())
				representation += ","; 
			representation += "\n\t" + c; 
		}
		representation += "]]";
		return representation;
	}
	
	public String toString(int numeroCompte) throws CompteInexistant {
		return toString(numeroCompte, false);
	}

	public String toString(int numeroCompte, boolean displaySummary) throws CompteInexistant {
		Compte compte = comptes.get(numeroCompte);
		if (compte == null)
			throw new CompteInexistant(numeroCompte);
		return compte.toString(displaySummary);
	}

	
	public String getName() {
		return name;
	}

	public static void restaurerBanque(String string) {
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		try {
			fileInput = new FileInputStream("Banque.ser");
			objectInput = new ObjectInputStream(fileInput);
			singleton = (Banque) objectInput.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier ne peut pas être ouvert en écriture: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Sauvegarde banque impossible: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if (objectInput != null) objectInput.close();
				if (fileInput != null) fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	
}
