package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Relatorio;

public class RelatorioDAO {

	private static RelatorioDAO instance;
	
	private RelatorioDAO(){}

	public static RelatorioDAO getInstance() {
		if (instance == null)
			instance = new RelatorioDAO();
		return instance;
	}

	public ResultSet pesquisarPorData(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"' AND barbeiro = '"
				+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				+relatorio.getDataInicial()+"' AND '"+relatorio.getDataFinal()+"' AND nome = '"
				+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	
	}
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
				+relatorio.getBarbeiro()+"' AND nome = '"+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	public ResultSet pesquisarPorServico(Relatorio relatorio)
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
				+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
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
