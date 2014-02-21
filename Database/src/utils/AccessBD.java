package utils;


import java.sql.*;


public class AccessBD {
    private static final String URL = 
    		"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
    
    /** Creates a Connection to a Access Database */
    public static Connection getConnection(String path) throws SQLException {
    	
    	try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch(ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
        }
        return DriverManager.getConnection(URL + path, "", "");
       // url,  uid, password
    }  
}