package tests;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

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

}
