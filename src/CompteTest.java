import static org.junit.Assert.*;

import org.junit.Test;

import titulaire.Personne;

import entity.impl.Compte;
import exceptions.DebitNonAutorise;


public class CompteTest {
	private static float maximumDelta = 0.001f;
	@Test
	public void testCompteFloat() {
		assertNotNull(new Compte(100.0f));
	}

	@Test
	public void testCompteFloatTitulaire() {
		assertNotNull(new Compte(100.0f, null));
		assertNotNull(new Compte(100.0f, new Personne("Jack", "Bauer")));
	}

	@Test
	public void testPrint() {
		Compte compte = new Compte(123.45f);
		assertEquals(compte.toString(), "Compte [numero=4, solde=123.45, titulaire=Kahuzac, decouvertAutorise=123.45, operations=[]\n\t]");
	}

	@Test
	public void testGetSolde() {
		Compte compte = new Compte(150.0f);
		assertEquals(compte.getSolde(), 150.f, maximumDelta);
	}

	@Test
	public void testCrediter() {
		Compte compte = new Compte(100.0f);
		float ancienSolde = compte.getSolde();
		compte.crediter(20);
		assertEquals(compte.getSolde(), ancienSolde + 20.f, maximumDelta);
	}

	@Test
	public void testDebiter() {
		Compte compte = new Compte(234.56f);
		try {
			compte.debiter(300.00f);
		} catch (DebitNonAutorise e) {
			fail();
		}
		try {
			compte.debiter(200.00f);
			fail();
		} catch (DebitNonAutorise e) {}
		try {
			compte.debiter(200.00f);
			fail();
		} catch (DebitNonAutorise e) {}
		try {
			compte.debiter(169.12f);
		} catch (DebitNonAutorise e) {
			fail();
		}
		try {
			compte.debiter(0.01f);
			fail();
		} catch (DebitNonAutorise e) {}
		
	}

}
