/**
 * ProvidedServiceDAO.java
 *This class manages the DAO functions of services rendered.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProvidedService;

public class ProvidedServiceDAO {
	
	/* Instance to the singleton. */
	private static ProvidedServiceDAO instance;

	private ProvidedServiceDAO() {

		/* Blank constructor. */
	}

	/**
	 * Singleton implementation
	 * 
	 * @return instance the active AddressBookDAO instance, since it will be just one
	 * 					at a time
	 */
	public static ProvidedServiceDAO getInstance() {
		
		if (instance == null) {
			instance = new ProvidedServiceDAO();
		} else {
			// Nothing to do.
		}
		
		return instance;
	}

	/**
	 * This includes a service from the database.
	 * 
	 * @param service a service to be included
	 * @return true if the deletion was successfully performed.
	 * @return false if there were some problem. 
	 * @throws SQLException If there was some problem during the database
	 * 		deletion.
	 */
	public boolean incluir(ProvidedService service) throws SQLException {
		
		if (service != null) {
			this.updateQuery("INSERT INTO "
					+ "servicoprestado (nome, preco, barbeiro, data) VALUES ("
					+ "\"" + service.getNomeServico() + "\", " + "\""
					+ service.getPreco() + "\", " + "\""
					+ service.getNomeBarbeiro() + "\", " + "\""
					+ service.getData() + "\"); ");
			
			return true;
		} else {
			// Nothing to do.
		}

		return false;
	}

	/**
	 * This removes a service from the database.
	 * 
	 * @param service a service to be deleted
	 * @return true if the deletion was successfully performed.
	 * @return false if there were some problem. 
	 * @throws SQLException If there was some problem during the database
	 * 		deletion.
	 */
	public boolean excluir(ProvidedService service) throws SQLException {
		
		if (service != null) {
			this.updateQuery("DELETE FROM servicoprestado WHERE "
				+ "servicoprestado.idservicoprestado = \"" + pesquisar(service)+ "\";");
			return true;
		} else {
			//Nothing to do.
		}
		
		return false;
	}

	/**
	 * This searches for services from the database.
	 * 
	 * @param servico a service to be searched.
	 * @return the id of the service searched
	 * @throws SQLException If there was some problem during the database
	 * 		deletion.
	 */
	private String pesquisar(ProvidedService servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM servicoprestado WHERE "
						+ "servicoprestado.nome = \""
						+ servico.getNomeServico()
						+ "\" AND servicoprestado.preco = \""
						+ servico.getPreco()
						+ "\" AND servicoprestado.barbeiro = \""
						+ servico.getNomeBarbeiro()
						+ "\" AND servicoprestado.data = \""
						+ servico.getData() + "\";");
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return rs.getString("idservicoprestado");
	}

	/**
	 * This updates a query.
	 * 
	 * @param message a message to be the Query
	 * @throws SQLException If there was some problem during the database
	 * 		deletion.
	 */
	private void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/**
	 * Shows registered services rendered
	 * 
	 * @param service a service to be shown
	 * @return rs a resultset with the results
	 * @throws SQLException If there was some problem during the database
	 * 		deletion.
	 */
	public ResultSet mostrarServicosPrestadosCadastrados(ProvidedService service) 
			throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
						"SELECT nome, preco, barbeiro, data FROM servicoprestado" 
								+ " ORDER BY data;");
		
		return rs;
	}

}
