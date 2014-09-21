/**
 * RelatorioDAO.java
 *This class manages the DAO functions of a report.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Relatorio;

public class ReportDAO {

	/* Instance to the singleton. */
	private static ReportDAO instance;
	
	private ReportDAO(){
		/* Blank constructor. */
	}

	/* Singleton implementation. */
	public static ReportDAO getInstance() {
		if (instance == null)
			instance = new ReportDAO();
		return instance;
	}

	/* Search by date */
	public ResultSet pesquisarPorData(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date and barber */
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"' AND barbeiro = '"
				+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date and service*/
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"' AND nome = '"
				+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	
	}
	
	/* Search by barber*/
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by barber and service*/
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+relatorio.getBarbeiro()+"' AND nome = '"+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by service*/
	public ResultSet pesquisarPorServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
				+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date, barber and service*/
	public ResultSet pesquisarPorDataBarbeiroEServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"' AND barbeiro = '"
				+relatorio.getBarbeiro()+"' AND nome = '"+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
