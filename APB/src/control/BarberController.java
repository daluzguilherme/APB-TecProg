/**
 * BarbeiroController
 * This class inserts, alter, excludes, searches and shows a barber's name.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BarberDAO;
import model.Barber;

public class BarberController {

	private static BarberController instance;

	private BarberController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active BarbeiroController instance, since it will be just one
	 * at time
	 */
	public static BarberController getInstance() {
		
		if (instance == null){
			instance = new BarberController();
		} else {
			// Nothing to do.
		}
		
		return instance;
	}
	
	/**
	 * Inserts a new barber on the database.
	 * @param barber one person who works in the barber shops.
	 * @throws SQLException If has some problem during the database insertion
	 * @return result exception for barber.
	 */
	public boolean inserir(Barber barber) throws SQLException {
		
		boolean result = false;
		
		if (barber != null) {
			BarberDAO.getInstance().incluir(barber);
			result = true;
		} else{
			result = false;
		}
		
		return result;
	}

	/**
	 * Alters one barber on the database.
	 * @param newBarberName of one person in the database.
	 * @param barber one person who works in the barber shops.
	 * @return result exception for barber.
	 * @throws SQLException If has some problem during the database update
	 */
	public boolean alterar(String newBarberName, Barber barber) 
			throws SQLException {
		
		boolean result = false;
		
		if (barber != null){
			Barber alteredBarber = barber;
			BarberDAO.getInstance().alterar(newBarberName, alteredBarber, barber);
			result =  true;
		} else {
			result = false;
		}
		
		return result;
	}

	/**
	 * Excludes a barber from the database.
	 * @param  barber one person who works in the barber shops.
	 * @return result exception for barber.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public boolean excluir(Barber barber) throws SQLException {
		boolean result;
		
		if (barber != null){
			BarberDAO.getInstance().excluir(barber);
			result = true;
		}else{
			result = false;
		}
		
		return result;	
	}
	
	/**
	 * Search a barber name in the database.
	 * @return resultSearch search by barber name in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisar() throws SQLException {
		ResultSet resultSearch = BarberDAO.getInstance().pesquisar();
		
		return resultSearch;
	}
	
	/**
	 * Displays  registered barbers.
	 * @param barber instance of an type of object Barbeiro.
	 * @return  resultBarber show the registered barbers in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarBarbeirosCadastrados(Barber barber) 
			throws SQLException {
		ResultSet resultBarber = BarberDAO.getInstance()
				.mostrarBarbeirosCadastrados(barber);
		
		return resultBarber;
	}
	
	/**
	 * Search a barber in the database by name.
	 * @param  barber one person who works in the barber shops.
	 * @return  resultBarber search by name the barber in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorNome(Barber barber) throws SQLException {
		ResultSet resultBarber = BarberDAO.getInstance()
				.pesquisarPorNome(barber);
		
		return resultBarber;
	}

}
