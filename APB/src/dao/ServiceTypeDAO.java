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
	 * @param serviceType a new type of service to be created.
	 * @return true if it was included successfully
	 * @return false if type of service is null;
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean incluir(ServiceType serviceType) throws SQLException {
		if (serviceType == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + serviceType.getNomeTipoServico() + "\", " + "\""
				+ serviceType.getPreco() + "\"); ");

		return true;
	}

	/**
	 * This updates a service type on the database
	 * 
	 * @param name a name to be altered.
	 * @param alteredServiceType an old type of service altered
	 * @param serviceType a service type to be altered.
	 * @return true if it was altered successfully. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean alterar(String name,ServiceType alteredServiceType, ServiceType serviceType) throws SQLException {
		if (alteredServiceType == null || serviceType == null) 
			return false;
		
		this.updateQuery("UPDATE tiposervico SET nome = '"
				+ alteredServiceType.getNomeTipoServico() + "', " + "preco = '"
				+ alteredServiceType.getPreco()  + "' WHERE"
				+ " nome = '" + name + "';");

		return true;
	}

	/**
	 * This removes a service type from the database
	 * 
	 * @param serviceType a type of service to be deleted
	 * @return true if everything went OK.
	 * @return false if type of service is null.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean excluir(ServiceType serviceType) throws SQLException {
		if (serviceType == null)
			return false;
		
		this.updateQuery("DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + serviceType.getNomeTipoServico() + "\";");
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
	 * @param service NOT USED
	 * @return rs a ResultSet with the services registered
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public ResultSet mostrarTipoServicoCadastrados(ServiceType service) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * FROM tiposervico;");
		
		return rs;
	}
	
	/**
	 * Search a service by name
	 * 
	 * @param service a service to be searched
	 * @return rs a ResultSet with the services found
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public ResultSet pesquisarPorNome(ServiceType service) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
				+ "nome = '" + service.getNomeTipoServico() + "';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
