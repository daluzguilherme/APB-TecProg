/**
 * BarbeiroController
 * This class inserts, alter, excludes, searches and shows a barber's name.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BarberDAO;
import model.Barbeiro;

public class BarbeiroController {

	private static BarbeiroController instance;

	private BarbeiroController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active BarbeiroController instance, since it will be just one
	 * at time
	 */
	public static BarbeiroController getInstance() {
		if (instance == null)
			instance = new BarbeiroController();
		return instance;
	}
	
	/**
	 * Inserts a new barber on the database.
	 * @param barbeiro one person who works in the barber shops.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if barbeiro is null.
	 */
	public boolean inserir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null) { 
			return false;
		}
			
		BarberDAO.getInstance().incluir(barbeiro);
		return true;
	}

	/**
	 * Alters one barber on the database.
	 * @param nome of one person in the database.
	 * @param barbeiro one person who works in the barber shops.
	 * @return false if barbeiro is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database update
	 */
	public boolean alterar(String nome, Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		Barbeiro barbeiro_alterado = barbeiro;
		BarberDAO.getInstance().alterar(nome, barbeiro_alterado, barbeiro);
		return true;
	}

	/* Excludes a barber from the database. */
	/**
	 * Excludes a barber from the database.
	 * @param  barbeiro one person who works in the barber shops.
	 * @return false if barbeiro is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public boolean excluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;
		
		BarberDAO.getInstance().excluir(barbeiro);
		return true;
	}
	
	/**
	 * Search a barber name in the database.
	 * @return Search by barber name in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisar() throws SQLException {
		return BarberDAO.getInstance().pesquisar();
	}
	
	/**
	 * Displays  registered barbers.
	 * @return Show the registered barbers in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarBarbeirosCadastrados(Barbeiro barbeiro) throws SQLException {
		return BarberDAO.getInstance().mostrarBarbeirosCadastrados(barbeiro);
	}
	
	/* Search a barber by name. */
	/**
	 * Search a barber in the database by name.
	 * @param  barbeiro one person who works in the barber shops.
	 * @return Search by name the barber in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorNome(Barbeiro barbeiro) throws SQLException {
		return BarberDAO.getInstance().pesquisarPorNome(barbeiro);
	}

}
