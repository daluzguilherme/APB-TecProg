/**
 * ReciboController
 * This class searches all services from a barber to generate a receipt
 * to a barber.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReciboDAO;

public class ReciboController {

	private static ReciboController instance;
	
	public ReciboController(){}
	
	/**
	 * Provides the singleton implementation
	 * @return the active ReciboController instance, since it will be just one
	 * at time
	 */
	public static ReciboController getInstance(){
		if(instance == null){
			instance = new ReciboController();
		}
		return instance;
	}
	
	/**
	 * Search a barber's service between two dates.
	 * @param  barbeiro one person who works in the barber shops.
	 * @param dataInicial This represents the date beginning of the service of 
	 *  the barber.
	 * @param dataFinal This represents the date ending of the service of 
	 *  the barber.
	 * @return Search a barber's service in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarServicosDoBarbeiro(String barbeiro,
			String dataInicial, String dataFinal) throws SQLException{
		
		return ReciboDAO.getInstance().pesquisarServicosDoBarbeiro(barbeiro,
				dataInicial, dataFinal);
		
	}
	
}
