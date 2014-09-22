/**
 * RelatorioController
 * This class provides methods to generate different kinds of reports to
 * the barbershop owner.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;


import dao.ReportDAO;
import model.Relatorio;

public class RelatorioController {
	
private static RelatorioController instance;

	public RelatorioController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active RelatorioController instance, since it will be just one at
	 * time.
	 */
	public static RelatorioController getInstance() {
		if(instance == null)
			instance = new RelatorioController();
		return instance;
	}
	
	/**
	 * This method looks for a report by date.
	 * @return Search a report by date  in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorData(Relatorio relatorio) throws SQLException {
		return ReportDAO.getInstance().pesquisarPorData(relatorio);
	}
	
	/**
	 * This method looks for by date and a specific barber. 
	 * @return Search  by date and a specific barber in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorDataEBarbeiro(relatorio);
	}
	
	/**
	 * This method looks for by date and service. 
	 * @return Search  by date and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorDataEServico(relatorio);
	}
	
	/**
	 * This method looks for by barber. 
	 * @return Search  by barber in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorBarbeiro(relatorio);
	}
		
	/**
	 * This method looks for by  barber and service. 
	 * @return Search  by  barber and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorBarbeiroEServico(relatorio);
	}
	
	/**
	 * This method looks for  a service. 
	 * @return Search  by  service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorServico(relatorio);
	}
	
	/**
	 * This method looks for by date, barber and service. 
	 * @return Search  by date, barber and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	
	public ResultSet pesquisarPorDataBarbeiroEServico(Relatorio relatorio) throws SQLException {		
		return ReportDAO.getInstance().pesquisarPorDataBarbeiroEServico(relatorio);
	}
	

}
