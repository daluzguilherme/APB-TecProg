package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BarbeiroDAO;
import model.Barbeiro;

public class BarbeiroController {

	private static BarbeiroController instance;

	private BarbeiroController() {}
	
	/* Returns an instance of a barber. */
	public static BarbeiroController getInstance() {
		if (instance == null)
			instance = new BarbeiroController();
		return instance;
	}
	
	/* Inserts a new barber on the database. */
	public boolean inserir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null) { 
			return false;
		}
			
		BarbeiroDAO.getInstance().incluir(barbeiro);
		return true;
	}

	/* Inserts a new barber on the database. */
	public boolean alterar(String nome, Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		Barbeiro barbeiro_alterado = barbeiro;
		BarbeiroDAO.getInstance().alterar(nome, barbeiro_alterado, barbeiro);
		return true;
	}

	/* Excludes a barber from the database. */
	public boolean excluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;
		
		BarbeiroDAO.getInstance().excluir(barbeiro);
		return true;
	}
	
	/* Search a barber name in the database. */
	public ResultSet pesquisar() throws SQLException {
		return BarbeiroDAO.getInstance().pesquisar();
	}
	
	/* Displays registered barbers. */
	public ResultSet mostrarBarbeirosCadastrados(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().mostrarBarbeirosCadastrados(barbeiro);
	}
	
	/* Search a barber by name. */
	public ResultSet pesquisarPorNome(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().pesquisarPorNome(barbeiro);
	}

}
