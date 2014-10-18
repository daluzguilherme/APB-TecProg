/**
 *FactoryConnection 
 *Makes the connection with the database server.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactoryConnection {
	static String statusConnection = "";
	
	private String local = "jdbc:mysql://localhost/apb";
	private String user = "root";
	private String password = "root";
	
	/* Instance for the singleton */
	private static FactoryConnection instance;
		
	private FactoryConnection () {
		
		/* Blank constructor. */
	}
	
	/* Singleton implementation. */
	public static FactoryConnection getInstance(){
		if(instance == null) {
			instance = new FactoryConnection();
		} else {
			// Nothing to do.
		}
		
		return instance;
	}
			
	/*  This generates the connection with the database server. */
	public Connection getConnection() throws SQLException{
		Connection connection = null;
		connection = DriverManager.getConnection(local, user, password);
		
		return connection;
	}

}
