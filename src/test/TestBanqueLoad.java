package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import entity.Banque;

public class TestBanqueLoad {

	public static void main(String[] args) {
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		Banque banque = null;
		try {
			fileInput = new FileInputStream("Banque.ser");
			objectInput = new ObjectInputStream(fileInput);
			banque = (Banque) objectInput.readObject();
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
		if (banque != null) {
			System.out.println(banque);
			int nouveauCompte = banque.creerCompte(600, null);
			System.out.println("Compte créé après deserialization " + nouveauCompte);
			System.out.println(banque);
		}
	}

}
