/**
 * AgendaDAO.java
 *This class manages the DAO functions of an address book.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;
import dao.FactoryConnection;

public class AgendaDAO {

	/* Instance to the singleton. */
	private static AgendaDAO instance;

	/**
	 * Class Constructor.
	 */
	private AgendaDAO() {
		
		/* Blank constructor. */
	}

	/**
	 * Singleton implementation.
	 * 
	 * @return instance the active AgendaDAO instance, since it will be just one
	 * 					at a time
	 */
	public static AgendaDAO getInstance() {
		if (instance == null)
			instance = new AgendaDAO();
		return instance;
	}

	/**
	 * Include new address book in the database.
	 * 
	 * @param agenda an adress book to be included in the database.
	 * @return true if there was no error during the database insertion.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean incluir(Agenda agenda) throws SQLException {
		if (agenda == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "agenda (nome, telefone, descricao) VALUES (" + "\""
				+ agenda.getNome() + "\", " + "\"" + agenda.getTelefone()
				+ "\", " + "\"" + agenda.getDescricao() + "\"); ");
		return true;
	}

	/**
	 * This updates an address book on the database.
	 * 
	 * @param nome A name to modified in the adress book.
	 * @param agenda_alterado A new instance of Agenda to be substituted.
	 * @param agenda  An old instance of Agenda to be substituted.
	 * @return true if there was no error during the process of changing.
	 * @return false if agenda or agenda_alterado is null. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean alterar(String nome, Agenda agenda_alterado, Agenda agenda)
			throws SQLException {	
		if(agenda == null || agenda_alterado == null)
			return false;
		
		this.updateQuery("UPDATE agenda SET " +
				"nome = \"" + agenda_alterado.getNome() + "\", " +
				"telefone = \"" + agenda_alterado.getTelefone() + "\", "+
				"descricao = \"" + agenda_alterado.getDescricao() + "\""+
				" WHERE " +
				" agenda.nome = \"" + nome + "\";");
			
		return true;
	}

	/**
	 * This removes an address book from the database.
	 * 
	 * @param contato A contact to be excluded from the database.
	 * @return false If contato given as parameter is null.
	 * @return true If there was no error during the database operation.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public boolean excluir(Agenda contato) throws SQLException {
		if(contato ==  null)
			return false;
		
		this.updateQuery("DELETE FROM agenda WHERE " + "agenda.telefone = \""
				+ contato.getTelefone() + "\";");
		return true;
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
	 * Shows registered contacts in address book.
	 * 
	 * @param contato an adress book to have its contacts shown.
	 * @return rs a ResultSet with all elements of an adress book.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select * from agenda;");
		
		return rs;
	}

	/**
	 * Search a contact by name.
	 * 
	 * @param contato a contact to be searched.
	 * @return rs A ResultSet with all names matching the name searched.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "nome = '" + contato.getNome()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

	/**
	 * Search a contact by phone number.
	 * 
	 * @param contato a contact to be searched.
	 * @return rs A ResultSet with all phone numbers matching the name searched.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "telefone = '" + contato.getTelefone()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

}
