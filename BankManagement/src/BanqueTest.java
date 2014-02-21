import static org.junit.Assert.*;

import org.junit.Test;

import entity.Banque;


public class BanqueTest {

	@Test
	public void testGetInstance() {
		if (Banque.getInstance() == null)
			fail("getInstance is null");
	}

}
