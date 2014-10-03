/**
 * RelatorioController
 * This class provides methods to generate different kinds of reports to
 * the barbershop owner.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;


import dao.ReportDAO;
import model.Report;

public class ReportController {
	
private static ReportController instance;

	public ReportController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active RelatorioController instance, since it will be just 
	 * one at time.
	 */
	public static ReportController getInstance() {
		if(instance == null){
			instance = new ReportController();
		}
		return instance;
	}
	
	/**
	 * This method looks for a report by date.
	 * @param report instance of an type of object Relatorio.
	 * @return resultReport Search a report by date  in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorData(Report report) throws SQLException {
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorData(report);
		return resultReport;
	}
	
	/**
	 * This method looks for by date and a specific barber.
	 * @param report instance of an type of object Relatorio. 
	 * @return resultReport Search  by date and a specific barber in the
	 *  database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorDataEBarbeiro(Report report) 
			throws SQLException {
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorDataEBarbeiro(report);
		return resultReport;
	}
	
	/**
	 * This method looks for by date and service. 
	 * @param report instance of an type of object Relatorio.
	 * @return resultReport Search  by date and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorDataEServico(Report report)
			throws SQLException {
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorDataEServico(report);
		return resultReport;
	}
	
	/**
	 * This method looks for by barber. 
	 * @param report instance of an type of object Relatorio.
	 * @return resultReport Search  by barber in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorBarbeiro(Report relatorio) 
			throws SQLException {
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorBarbeiro(relatorio);
		return resultReport;
	}
		
	/**
	 * This method looks for by  barber and service. 
	 * @param report instance of an type of object Relatorio.
	 * @return resultReport Search  by  barber and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorBarbeiroEServico(Report report)
			throws SQLException {	
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorBarbeiroEServico(report);
		return resultReport;
	}
	
	/**
	 * This method looks for  a service. 
	 * @param report instance of an type of object Relatorio.
	 * @return resultReport Search  by  service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	public ResultSet pesquisarPorServico(Report report) 
			throws SQLException {	
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorServico(report);
		return resultReport;
	}
	
	/**
	 * This method looks for by date, barber and service. 
	 * @param report instance of an type of object Relatorio.
	 * @return  resultReport Search  by date, barber and service in the database.
	 * @throws SQLException If has some problem with the database search.
	 */
	
	public ResultSet pesquisarPorDataBarbeiroEServico(Report report) 
			throws SQLException {		
		ResultSet resultReport = ReportDAO.getInstance()
				.pesquisarPorDataBarbeiroEServico(report);
		return resultReport;
	}
	

}
