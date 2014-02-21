package tests;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import utils.AccessBD;


public class DBTest {

	Connection connection;
	 
	@Before
	public void setUp() throws Exception {
		connection = AccessBD.getConnection("../BD/bd.mdb");
	}

	@After
	public void tearDown() throws Exception {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetConnection() throws SQLException {
		assertNotNull(connection);
	}

	@Test
	public void displayContent() throws SQLException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));
		assertNotNull(connection);
		Statement statement = connection.createStatement();
		assertNotNull(statement);
		ResultSet all = statement.executeQuery("SELECT * FROM Personnes");
		while (all.next()) {
			System.out.println(
				"ID " + all.getInt(1) +
				" Nom " + all.getString(2) +
				" Prenom " + all.getString(3) +
				" Age " + all.getInt(4));
		}
		assertEquals(
			"ID 1 Nom A Prenom Alain Age 35\r\n" +
			"ID 2 Nom B Prenom Bernard Age 64\r\n" +
			"ID 3 Nom C Prenom Christian Age 32\r\n", outStream.toString());
	}

}
