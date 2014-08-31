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
	
	/* Returns an instance of a receipt. */
	public static ReciboController getInstance(){
		if(instance == null){
			instance = new ReciboController();
		}
		return instance;
	}
	
	/* Search a barber's service between two dates. */ 
	public ResultSet pesquisarServicosDoBarbeiro(String barbeiro, String dataInicial, String dataFinal) throws SQLException{
		
		return ReciboDAO.getInstance().pesquisarServicosDoBarbeiro(barbeiro, dataInicial, dataFinal);
		
	}
	
}
