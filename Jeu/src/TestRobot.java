import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestRobot {
	Robot robot = null;
	
	@Before
	public void setUp() throws Exception {
		Piece p = new Piece();
		robot = new Robot(p, 10);
	}

	@After
	public void tearDown() throws Exception {
		robot = null;
	}

	@Test
	public void testConstructor() {
		assertNotNull(robot);
	}

	@Test
	public void testGo() {
		assertFalse(robot.go(PC.Nord));
		assertTrue(robot.go(PC.Est));
		String nomPiece = robot.tesou();
		assertEquals("UneAutrePiece", nomPiece);
	}

	@Test
	public void testPrendre() throws Perdu, TresorNonExistant {
		try {
			robot.prendre("TresorNonExistant");
			fail();
		}
		catch (TresorNonExistant e) {			
		}
		try {
			robot.prendre("Tresor1");
		}
		catch (TresorNonExistant e) {
			fail();
		}
		try {
			robot.prendre("Tresor1");
			fail();
		}
		catch (Perdu e) {
		}
	}

	@Test
	public void testDeposer() throws TresorNonExistant, Perdu, Gagne {
		try {
			robot.deposer("Tresor1");
			fail();
		}
		catch (TresorNonExistant e) {			
		}
		robot.prendre("Tresor1");
		try {
			robot.deposer("Tresor1");
		}
		catch (TresorNonExistant e) {
			fail();
		}
	}

	@Test
	public void testTesou() {
		String nomPiece = robot.tesou();
		assertNotNull(nomPiece);
		assertEquals("MaPiece", nomPiece);
	}

	@Test
	public void testTasquoi() throws TresorNonExistant, Perdu {
		Collection<String> t = robot.tasquoi();
		assertEquals(0, t.size());
		robot.prendre("Tresor1");
		assertEquals(1, robot.tasquoi().size());
		assertEquals("Tresor1", robot.tasquoi().iterator().next());
	}

	@Test
	public void testYaquoi() {
		assertEquals(2, robot.yaquoi().size());
		assertEquals("Tresor1", robot.yaquoi().iterator().next());
	}

}
