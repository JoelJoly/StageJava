package tests;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

import utils.AccessBD;


public class DBTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = AccessBD.getConnection("../BD/bd.mdb");
		assertNotNull(connection);
	}

}
