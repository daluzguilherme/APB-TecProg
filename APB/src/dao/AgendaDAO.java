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

	private AgendaDAO() {
		
		/* Blank constructor. */
	}

	/* Singleton implementation. */
	public static AgendaDAO getInstance() {
		if (instance == null)
			instance = new AgendaDAO();
		return instance;
	}

	/* Include new address book in the database. */
	public boolean incluir(Agenda agenda) throws SQLException {
		if (agenda == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "agenda (nome, telefone, descricao) VALUES (" + "\""
				+ agenda.getNome() + "\", " + "\"" + agenda.getTelefone()
				+ "\", " + "\"" + agenda.getDescricao() + "\"); ");
		return true;
	}

	/* This updates an address book on the database. */
	public boolean alterar(String nome, Agenda agenda_alterado, Agenda agenda) throws SQLException {	
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

	/* This removes an address book from the database. */
	public boolean excluir(Agenda contato) throws SQLException {
		if(contato ==  null)
			return false;
		
		this.updateQuery("DELETE FROM agenda WHERE " + "agenda.telefone = \""
				+ contato.getTelefone() + "\";");
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
	
	/* Shows registered contacts in address book*/
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select * from agenda;");
		
		return rs;
	}
	
	/* Search by name */
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "nome = '" + contato.getNome()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}
	
	/* search by phone number */
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "telefone = '" + contato.getTelefone()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}

}
