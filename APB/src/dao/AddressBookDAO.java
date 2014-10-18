/**
 * AddressBookDAO.java
 *This class manages the DAO functions of an address book.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AddressBook;
import dao.FactoryConnection;

public class AddressBookDAO {

	/* Instance to the singleton. */
	private static AddressBookDAO instance;

	/**
	 * Class Constructor.
	 */
	private AddressBookDAO() {
		
		/* Blank constructor. */
	}

	/**
	 * Singleton implementation.
	 * 
	 * @return instance the active AddressBookDAO instance, since it will be
	 * 		just one	at a time
	 */
	public static AddressBookDAO getInstance() {
		
		if (instance == null) {
			instance = new AddressBookDAO();
		} else {
			// Nothing to do.
		}
		
		return instance;
	}

	/**
	 * Include new address book in the database.
	 * 
	 * @param addressBook an address book to be included in the database.
	 * @return true if there was no error during the database insertion.
	 * @throws SQLException If there was some problem during the database
	 * 						deletion
	 */
	public boolean incluir(AddressBook addressBook) throws SQLException {
		
		if (addressBook == null) {
		
			return false;
		}
		
		this.updateQuery("INSERT INTO "
				+ "agenda (nome, telefone, descricao) VALUES (" + "\""
				+ addressBook.getNome() + "\", " + "\"" + addressBook.getTelefone()
				+ "\", " + "\"" + addressBook.getDescricao() + "\"); ");
		
		return true;
	}

	/**
	 * This updates an address book on the database.
	 * 
	 * @param nome A name to be modified in the address book.
	 * @param alteredAddressBook A new instance of Agenda to be substituted.
	 * @param addressBook  An old instance of Agenda to be substituted.
	 * @return true if there was no error during the process of changing.
	 * @return false if agenda or agenda_alterado is null. 
	 * @throws SQLException If there was some problem during the database
	 * 						deletion.
	 */
	public boolean alterar(String nome, AddressBook alteredAddressBook, 
			AddressBook addressBook) throws SQLException {	
		
		if(addressBook == null || alteredAddressBook == null) {
			
			return false;
		} else {
			// Nothing to do.
		}
		
		this.updateQuery("UPDATE agenda SET " +
				"nome = \"" + alteredAddressBook.getNome() + "\", " +
				"telefone = \"" + alteredAddressBook.getTelefone() + "\", "+
				"descricao = \"" + alteredAddressBook.getDescricao() + "\""+
				" WHERE " +
				" agenda.nome = \"" + nome + "\";");
			
		return true;
	}

	/**
	 * This removes an address book from the database.
	 * 
	 * @param contact A contact to be excluded from the database.
	 * @return false If contato given as parameter is null.
	 * @return true If there was no error during the database operation.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public boolean excluir(AddressBook contact) throws SQLException {
		if(contact ==  null) {
		
			return false;
		} else {
			//Nothing to do
		}
		
		this.updateQuery("DELETE FROM agenda WHERE " + "agenda.telefone = \""
				+ contact.getTelefone() + "\";");
		
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
	 * @param contact an address book to have its contacts shown.
	 * @return rs a ResultSet with all elements of an address book.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet mostrarContatosCadastrados(AddressBook contact) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select * from agenda;");
		
		return rs;
	}

	/**
	 * Search a contact by name.
	 * 
	 * @param contact a contact to be searched.
	 * @return rs A ResultSet with all names matching the name searched.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet pesquisarPorNome(AddressBook contact) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT *" 
				+ " FROM agenda WHERE " + "nome = '" + contact.getNome()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

	/**
	 * Search a contact by phone number.
	 * 
	 * @param contact a contact to be searched.
	 * @return rs A ResultSet with all phone numbers matching the name searched.
	 * @throws SQLException If there was some problem during the database
	 * 						operation.
	 */
	public ResultSet pesquisarPorTelefone(AddressBook contact) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT *" 
				+ " FROM agenda WHERE " + "telefone = '" + contact.getTelefone()
					+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

}
