package tests;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		PrintStream previousOut = System.out;
		try {
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
		catch (SQLException e) {
			throw e;
		}
		finally {
			System.setOut(previousOut);
		}
	}

	@Test
	public void accessContent() throws SQLException {
		assertNotNull(connection);
		PreparedStatement statement = connection.prepareStatement("SELECT Nom, Prenom, Age FROM Personnes WHERE id = ?");
		assertNotNull(statement);
		statement.setInt(1, 2);
		ResultSet resultSet = statement.executeQuery();
		display(statement.getMetaData()); 
//		display(statement.getParameterMetaData()); 

		assertTrue(resultSet.next());
		assertEquals("B", resultSet.getString(1));
		assertEquals("Bernard", resultSet.getString("Prenom"));
		assertEquals(64, resultSet.getInt("Age"));
		assertFalse(resultSet.next());

		statement.setInt(1, 3);
		resultSet = statement.executeQuery();
		assertTrue(resultSet.next());
		assertEquals("C", resultSet.getString(1));
		statement.setInt(1, 5);
		resultSet = statement.executeQuery();
		assertFalse(resultSet.next());
	}
	
	private void display(ResultSetMetaData metaData) throws SQLException {
		for(int i = 0; i < metaData.getColumnCount(); ++i) {
			System.out.println(
				"Label " + metaData.getColumnLabel(i + 1) + 
				" Class name " + metaData.getColumnClassName(i + 1)
				);
		}
	}
	private void display(ParameterMetaData metaData) throws SQLException {
		for(int i = 0; i < metaData.getParameterCount(); ++i) {
			System.out.println(metaData.getParameterClassName(i + 1));
		}
	}

}
