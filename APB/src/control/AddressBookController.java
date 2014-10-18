/**
 * AgendaController
 * This class adds new contacts, modifies the contacts previously created,
 * searches contacts and shows contacts.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AddressBookDAO;
import model.AddressBook;

public class AddressBookController {

	private static AddressBookController instance;

	private AddressBookController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active AgendaController instance, since it will be just one
	 * at time.
	 */
	public static AddressBookController getInstance() {
		
		if (instance == null){
			instance = new AddressBookController();
		}
		
		return instance;
	}

	/**
	 * Includes an Address book into the database.
	 * @param adressBook of the barber shop.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if agenda is null.
	 */
	public boolean incluir(AddressBook adressBook) throws SQLException {
		
		boolean result = false;
		
		if (adressBook != null) {
			AddressBookDAO.getInstance().incluir(adressBook);
			result = true;
		} else{
			result = false;	
		}
		
		return result;
	}

	/**
	 * Alters a name into a given address book.
	 * @param nome of one person in the address book.
	 * @param adressbook of the barber shop.
	 * @return false if agenda is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database update
	 */
	public boolean alterar(String nome, AddressBook adressbook)
			throws SQLException {
		
		boolean result = false;
		
		if (adressbook != null) {
			AddressBook agenda_alterado = adressbook;
			AddressBookDAO.getInstance()
			.alterar(nome, agenda_alterado, adressbook);
			result = true;
		} else{
			result = false;
		}
		
		return result;
	}

	/**
	 * Excludes a contact from the address book.
	 * @param contact specific person in the address book.
	 * @return false if contact is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public boolean excluir(AddressBook contact) throws SQLException {
		
		boolean result = false;
		
		if (contact != null) {
			AddressBookDAO.getInstance().excluir(contact);
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

	/**
	 * Displays all contacts from the address book.
	 * @param contact specific person in the address book.
	 * @return Show the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarContatosCadastrados(AddressBook contact)
			throws SQLException {
		
		ResultSet resultContact = AddressBookDAO.getInstance()
				.mostrarContatosCadastrados(contact); 
		
		return resultContact;
	}

	/**
	 * Search a contact in the address book by name.
	 * @param contact specific person in the address book.
	 * @return Search by name the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorNome(AddressBook contact) throws SQLException {
		
		ResultSet resultContact = AddressBookDAO.getInstance()
				.pesquisarPorNome(contact);
		
		return resultContact;
	}

	/**
	 * Search a contact in the address book by phone number.
	 * @param contact specific person in the address book.
	 * @return Search by phone number the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorTelefone(AddressBook contact) 
			throws SQLException {
		
		ResultSet resultContact = AddressBookDAO.getInstance()
				.pesquisarPorTelefone(contact);
		
		return resultContact;
	}

}
