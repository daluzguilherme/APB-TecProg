/**
 * RelatorioDAO.java
 *This class manages the DAO functions of a report.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Report;

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
	public ResultSet pesquisarPorData(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+report.getDataInicial()+"' AND '"+report.getDataFinal()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date and barber */
	public ResultSet pesquisarPorDataEBarbeiro(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+report.getDataInicial()+"' AND '"+report.getDataFinal()+"' AND barbeiro = '"
				+report.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date and service*/
	public ResultSet pesquisarPorDataEServico(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+report.getDataInicial()+"' AND '"+report.getDataFinal()+"' AND nome = '"
				+report.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	
	}
	
	/* Search by barber*/
	public ResultSet pesquisarPorBarbeiro(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+report.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by barber and service*/
	public ResultSet pesquisarPorBarbeiroEServico(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+report.getBarbeiro()+"' AND nome = '"+report.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by service*/
	public ResultSet pesquisarPorServico(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
				+report.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	/* Search by date, barber and service*/
	public ResultSet pesquisarPorDataBarbeiroEServico(Report report)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+report.getDataInicial()+"' AND '"+report.getDataFinal()+"' AND barbeiro = '"
				+report.getBarbeiro()+"' AND nome = '"+report.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
