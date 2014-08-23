package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReciboDAO {
	
	private static ReciboDAO instance;
	
	private ReciboDAO(){}
	
	public static ReciboDAO getInstance(){
		if(instance == null)
			instance = new ReciboDAO();
		return instance;
	}
	
	public ResultSet pesquisarServicosDoBarbeiro(String barbeiro, String dataInicial, String dataFinal) throws SQLException{
		
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+dataInicial+"' AND '"+ dataFinal+"' AND barbeiro = '"
				+barbeiro+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	} 

}
