package test;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import titulaire.Entreprise;
import titulaire.Personne;
import entity.Banque;
import exceptions.CompteInexistant;


public class TestCompte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Banque banque = Banque.getInstance(); 
		int compte1 = banque.creerCompte(123.45f, null);
		System.out.println(compte1);
		int compte2 = banque.creerCompte(234.56f, null);
		System.out.println(compte2);

		testCredit(compte1, 21.f);
		
		testDebit(compte2, 300.00f);
		
		testDebit(compte2, 200.00f);

		testDebit(compte2, 169.12f);

		testDebit(compte2, 0.01f);

		testCredit(compte2, 114.f);
		
		int compte3 = banque.creerCompte(150.f, null, 5);
		System.out.println(compte3);
		testInterets();
		testDebit(compte3, 7.5f);
		testCredit(compte3, 50.0f);
		testInterets();

		testDebit(-58, 7.5f);
		
		int compte4 = banque.creerCompte(150.f, new Personne("Alain", "A"), 5);
		System.out.println(compte4);

		int compte5 = banque.creerCompte(500.f, new Entreprise("Orsys", 123456789), 5);
		System.out.println(compte5);
		
		System.out.println(banque.toString());
		testInterets();
		System.out.println(banque.toString());
		try {
			banque.supprimerCompte(compte5);
		}
		catch (CompteInexistant e) {
			System.out.println("Erreur, le compte a disparu: " + e.getMessage());
		}
		try {
			banque.supprimerCompte(compte5);
			System.out.println("Erreur, le compte n'a pas été enlevé " + compte5);
		}
		catch (CompteInexistant e) {
			System.out.println("OK le compte avait bien été supprimé: " + e.getMessage());
		}
		System.out.println(banque.toString());
		serialize();
	}
	static private void serialize() {
		FileOutputStream fileOuput = null;
		ObjectOutputStream objectOutput = null;
		
		try {
			fileOuput = new FileOutputStream("Banque.ser");
			objectOutput = new ObjectOutputStream(fileOuput);
			objectOutput.writeObject(Banque.getInstance());
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier ne peut pas être ouvert en écriture: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Sauvegarde banque impossible: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if (objectOutput != null) objectOutput.close();
				if (fileOuput != null) fileOuput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	static private void testCredit(int compte, float montant) {
		Banque banque = Banque.getInstance(); 
		System.out.println("credit de " + montant);
		try {
			banque.crediter(compte, montant);
			System.out.println(banque.toString(compte));
		}
		catch (Exception e) {
			System.out.println("Credit impossible " + e.getMessage());
		}
	}

	static private void testDebit(int compte, float montant) {
		Banque banque = Banque.getInstance(); 
		System.out.print("debit de " + montant);
		try {
			banque.debiter(compte, montant);
			System.out.println(" réussi");			
			System.out.println(banque.toString(compte));		
		}
		catch (Exception e) {
			System.out.println(" raté");						
			System.out.println("Debit impossible " + e.getMessage());
		}
	}

	static private void testInterets() {
		Banque banque = Banque.getInstance(); 
		banque.calculerInterets();
		System.out.println(banque.toString());		
	}
	
}
