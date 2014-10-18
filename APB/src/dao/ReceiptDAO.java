/**
 * ReciboDAO.java
 *This class manages the DAO functions of a receipt.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptDAO {
	
	/* Instance to the singleton. */
	private static ReceiptDAO instance;
	
	private ReceiptDAO(){
		
		/* Blank constructor. */
	}
	
	/* Singleton implementation. */
	public static ReceiptDAO getInstance(){
		if(instance == null)
			instance = new ReceiptDAO();
		return instance;
	}
	
	/* Shows the services Barber*/
	public ResultSet pesquisarServicosDoBarbeiro(String barber, String startDate, String endDate) throws SQLException{
		
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+startDate+"' AND '"+ endDate+"' AND barbeiro = '"
				+barber+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* This updates a database.*/
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	} 

}
