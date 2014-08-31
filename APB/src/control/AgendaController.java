/**
 * AgendaController
 * This class adds new contacts, modifies the contacts previously created,
 * searches contacts and shows contacts.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AgendaDAO;
import model.Agenda;

public class AgendaController {

	private static AgendaController instance;

	private AgendaController() {
	}

	/* Includes an Address book into the database. */
	public boolean incluir(Agenda agenda) throws SQLException {
		if (agenda == null) return false;

		AgendaDAO.getInstance().incluir(agenda);
		return true;
	}

	/* Alters a name into a given address book. */
	public boolean alterar(String nome, Agenda agenda) throws SQLException {
		if (agenda == null) return false;

		Agenda agenda_alterado = agenda;
		AgendaDAO.getInstance().alterar(nome, agenda_alterado, agenda);
		return true;
	}

	/* Excludes a contact from the address book. */
	public boolean excluir(Agenda contato) throws SQLException {
		if (contato == null) return false;

		AgendaDAO.getInstance().excluir(contato);
		return true;
	}

	/* Returns the instance of the address book. */
	public static AgendaController getInstance() {
		if (instance == null) instance = new AgendaController();
		return instance;
	}

	/* Displays all contacts from the address book. */ 
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().mostrarContatosCadastrados(contato);
	}

	/* Search a contact in the address book by name. */
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().pesquisarPorNome(contato);
	}

	/*  Search a contact in the address book by phone number. */
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().pesquisarPorTelefone(contato);
	}

}
