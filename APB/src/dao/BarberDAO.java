/**
 * BarbeiroDAO.java
 * This class manages the DAO functions of a barber.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barber;

public class BarberDAO {

	/* Instance to the singleton. */
	private static BarberDAO instance;

	/**
	 * Class Constructor
	 */
	private BarberDAO() {
		
		/* Blank constructor. */
	}

	/**
	 * Singleton implementation
	 * 
	 * @return instance the active AddressBookDAO instance, since it will be just one
	 * 					at a time
	 */
	public static BarberDAO getInstance() {
		if (instance == null)
			instance = new BarberDAO();
		return instance;
	}

	/**
	 * Include new barber in the database.
	 * 
	 * @param barber the barber's name to be included in the database.
	 * @return true if there was no error during the process of including.
	 * @return false if agenda or agenda_alterado is null. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean incluir(Barber barber) throws SQLException {
		if (barber == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "barbeiro (nome, cpf, rg, telefone, cadeira) VALUES ("
				+ "\"" + barber.getNome() + "\", " + "\""
				+ barber.getCpf() + "\", " + "\"" + barber.getRg()
				+ "\", " + "\"" + barber.getTelefone() + "\", " + "\""
				+ barber.getCadeira() + "\"); ");

		return true;
	}

	/**
	 * This updates a barber on the database.
	 * 
	 * @param name A barber's name to be modified in the database.
	 * @param alteredBarber A new instance of Barbeiro to be substituted.
	 * @param barber An old instance of Barbeiro to be substituted.
	 * @return true if there was no error during the process of changing.
	 * @return false if barbeiro or barbeiro_alterado is null. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean alterar(String name, Barber alteredBarber, Barber barber) throws SQLException {
		if (alteredBarber == null || barber == null)
			return false;
		
		this.updateQuery("UPDATE barbeiro SET nome = '"
				+ alteredBarber.getNome() + "', " + "cpf = '"
				+ alteredBarber.getCpf() + "', " + "rg = '"
				+ alteredBarber.getRg() + "', " + "telefone = '"
				+ alteredBarber.getTelefone() + "', " + "cadeira = '"
				+ alteredBarber.getCadeira() + "' WHERE" + " cpf = '"
				+ name + "';");

		return true;
	}

	/**
	 * This removes a barber from the database
	 * 
	 * @param barber an old instance of Barber to be excluded
	 * @return true if there was no error during the process of changing.
	 * @return false if barber is null. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean excluir(Barber barber) throws SQLException {
		if (barber == null)
			return false;
		
		this.updateQuery("DELETE FROM barbeiro WHERE "
				+ "barbeiro.nome = \"" + barber.getNome() + "\";");
		return true;
	}

	/**
	 * This searches for all barbers from the database.
	 * 
	 * @return rs a resultset with all the barbers in the list.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public ResultSet pesquisar() throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM barbeiro;");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

	/**
	 * This updates a query.
	 * 
	 * @param message gives a message to update the query.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/**
	 * Show registered barbers.
	 * 
	 * @param barber NOT USED
	 * @return rs a ResultSet with all registered barbers.
	 * @throws SQLException If there was some problem during the database
	 * 						operation
	 */
	public ResultSet mostrarBarbeirosCadastrados(Barber barber) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select nome, cpf, rg, telefone, cadeira from barbeiro;");
		
		return rs;
	}
	
	/**
	 * Search a barber by name.
	 * @param barber a barber to be  searched.
	 * @return rs a ResultSet with the barber searched.
	 * @throws SQLException If there was some problem during the database
	 *     operation.
	 */
	public ResultSet pesquisarPorNome(Barber barber) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM barbeiro WHERE nome = '" 
							+ barber.getNome() + "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	} 

}
