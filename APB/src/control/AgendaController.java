/**
 * AgendaController
 * This class adds new contacts, modifies the contacts previously created,
 * searches contacts and shows contacts.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AddressBookDAO;
import model.Agenda;

public class AgendaController {

	private static AgendaController instance;

	private AgendaController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active AgendaController instance, since it will be just one
	 * at time.
	 */
	public static AgendaController getInstance() {
		if (instance == null) instance = new AgendaController();
		return instance;
	}

	/**
	 * Includes an Address book into the database.
	 * @param agenda of the barber shop.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if agenda is null.
	 */
	public boolean incluir(Agenda agenda) throws SQLException {
		if (agenda == null) return false;

		AddressBookDAO.getInstance().incluir(agenda);
		return true;
	}

	/**
	 * Alters a name into a given address book.
	 * @param nome of one person in the address book.
	 * @param agenda of the barber shop.
	 * @return false if agenda is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database update
	 */
	public boolean alterar(String nome, Agenda agenda) throws SQLException {
		if (agenda == null) return false;

		Agenda agenda_alterado = agenda;
		AddressBookDAO.getInstance().alterar(nome, agenda_alterado, agenda);
		return true;
	}

	/**
	 * Excludes a contact from the address book.
	 * @param contato specific person in the address book.
	 * @return false if contato is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public boolean excluir(Agenda contato) throws SQLException {
		if (contato == null) return false;

		AddressBookDAO.getInstance().excluir(contato);
		return true;
	}

	/**
	 * Displays all contacts from the address book.
	 * @param contato specific person in the address book.
	 * @return Show the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException {
		return AddressBookDAO.getInstance().mostrarContatosCadastrados(contato);
	}

	/**
	 * Search a contact in the address book by name.
	 * @param contato specific person in the address book.
	 * @return Search by name the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException {
		return AddressBookDAO.getInstance().pesquisarPorNome(contato);
	}

	/**
	 * Search a contact in the address book by phone number.
	 * @param contato specific person in the address book.
	 * @return Search by phone number the contacts in the address book.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException {
		return AddressBookDAO.getInstance().pesquisarPorTelefone(contato);
	}

}
