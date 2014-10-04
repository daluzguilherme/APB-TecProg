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

	/**
	 * Class Constructor
	 */
	private ServiceTypeDAO() {
		
		/* Blank constructor. */
	}

	/**
	 * Singleton implementation
	 * 
	 * @return instance the active AddressBookDAO instance, since it will be just one
	 * 					at a time
	 */
	public static ServiceTypeDAO getInstance() {
		if (instance == null)
			instance = new ServiceTypeDAO();
		return instance;
	}

	/**
	 * Include new service type in the database.
	 * 
	 * @param tipoServico a new type of service to be created.
	 * @return true if it was included successfully
	 * @return false if type of service is null;
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean incluir(ServiceType tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + tipoServico.getNomeTipoServico() + "\", " + "\""
				+ tipoServico.getPreco() + "\"); ");

		return true;
	}

	/**
	 * This updates a service type on the database
	 * 
	 * @param nome a name to be altered.
	 * @param tipoServico_alterado an old type of service altered
	 * @param tipoServico a service type to be altered.
	 * @return true if it was altered successfully. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean alterar(String nome,ServiceType tipoServico_alterado, ServiceType tipoServico) throws SQLException {
		if (tipoServico_alterado == null || tipoServico == null) 
			return false;
		
		this.updateQuery("UPDATE tiposervico SET nome = '"
				+ tipoServico_alterado.getNomeTipoServico() + "', " + "preco = '"
				+ tipoServico_alterado.getPreco()  + "' WHERE"
				+ " nome = '" + nome + "';");

		return true;
	}

	/**
	 * This removes a service type from the database
	 * 
	 * @param tipoServico a type of service to be deleted
	 * @return true if everything went OK.
	 * @return false if type of service is null.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean excluir(ServiceType tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + tipoServico.getNomeTipoServico() + "\";");
		return true;
	}

	/**
	 * This updates a query
	 * 
	 * @param message gives a message to update the query.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/**
	 * Shows registered services type
	 * 
	 * @param servico NOT USED
	 * @return rs a ResultSet with the services registered
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public ResultSet mostrarTipoServicoCadastrados(ServiceType servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * FROM tiposervico;");
		
		return rs;
	}
	
	/**
	 * Search a service by name
	 * 
	 * @param servico a service to be searched
	 * @return rs a ResultSet with the services found
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public ResultSet pesquisarPorNome(ServiceType servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
				+ "nome = '" + servico.getNomeTipoServico() + "';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
