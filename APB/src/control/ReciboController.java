package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReciboDAO;

public class ReciboController {

	private static ReciboController instance;
	
	public ReciboController(){}
	
	public static ReciboController getInstance(){
		if(instance == null){
			instance = new ReciboController();
		}
		return instance;
	}
	
	public ResultSet pesquisarServicosDoBarbeiro(String barbeiro, String dataInicial, String dataFinal) throws SQLException{
		
		return ReciboDAO.getInstance().pesquisarServicosDoBarbeiro(barbeiro, dataInicial, dataFinal);
		
	}
	
}
