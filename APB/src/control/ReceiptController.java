/**
 * ReciboController
 * This class searches all services from a barber to generate a receipt
 * to a barber.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReceiptDAO;

public class ReceiptController {

	private static ReceiptController instance;
	
	public ReceiptController(){}
	
	/**
	 * Provides the singleton implementation
	 * @return the active ReciboController instance, since it will be just one
	 * at time
	 */
	public static ReceiptController getInstance(){
		
		if(instance == null){
			instance = new ReceiptController();
		}
		
		return instance;
	}
	
	/**
	 * Search a barber's service between two dates.
	 * @param  barber one person who works in the barber shops.
	 * @param startDate This represents the date beginning of the service of 
	 *  the barber.
	 * @param finalDate This represents the date ending of the service of 
	 *  the barber.
	 * @return  resultSearchServices search a barber's service in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarServicosDoBarbeiro(String barber,
			String startDate, String finalDate) throws SQLException{
		
		ResultSet resultSearchServices = ReceiptDAO.getInstance()
				.pesquisarServicosDoBarbeiro(barber,startDate, finalDate);
		
		return  resultSearchServices;
		
	}
	
}
