/**
 * ServiceTypeDAO.java
 *This class manages the DAO functions of services type.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceType;

public class ServiceTypeDAO {

	/* Instance to the singleton. */
	private static ServiceTypeDAO instance;

	private ServiceTypeDAO() {
		
		/* Blank constructor. */
	}

	/* Singleton implementation. */
	public static ServiceTypeDAO getInstance() {
		if (instance == null)
			instance = new ServiceTypeDAO();
		return instance;
	}

	/* Include new service type in the database. */
	public boolean incluir(ServiceType tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + tipoServico.getNomeTipoServico() + "\", " + "\""
				+ tipoServico.getPreco() + "\"); ");

		return true;
	}

	/* This updates a service type on the database. */
	public boolean alterar(String nome,ServiceType tipoServico_alterado, ServiceType tipoServico) throws SQLException {
		if (tipoServico_alterado == null || tipoServico == null) 
			return false;
		
		this.updateQuery("UPDATE tiposervico SET nome = '"
				+ tipoServico_alterado.getNomeTipoServico() + "', " + "preco = '"
				+ tipoServico_alterado.getPreco()  + "' WHERE"
				+ " nome = '" + nome + "';");

		return true;
	}

	/* This removes a service type from the database. */
	public boolean excluir(ServiceType tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + tipoServico.getNomeTipoServico() + "\";");
		return true;
	}

	/* This updates a query. */
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/* Shows registered services type */
	public ResultSet mostrarTipoServicoCadastrados(ServiceType servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * FROM tiposervico;");
		
		return rs;
	}
	
	/* Search by name */
	public ResultSet pesquisarPorNome(ServiceType servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
				+ "nome = '" + servico.getNomeTipoServico() + "';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
