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
	
	/* Makes a search by date. */
	public ResultSet pesquisarPorData(Relatorio relatorio) throws SQLException {
		return ReportDAO.getInstance().pesquisarPorData(relatorio);
	}
	
	/* Makes a search by date and a specific barber. */
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorDataEBarbeiro(relatorio);
	}
	
	/* Makes a search by date and service. */
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorDataEServico(relatorio);
	}
	
	/* Makes a search by barber. */
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorBarbeiro(relatorio);
	}
	
	/* Makes a search by barber and service. */
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorBarbeiroEServico(relatorio);
	}
	
	/* Makes a search by service. */
	public ResultSet pesquisarPorServico(Relatorio relatorio) throws SQLException {	
		return ReportDAO.getInstance().pesquisarPorServico(relatorio);
	}
	
	/* Makes a search by date, barber and service. */
	public ResultSet pesquisarPorDataBarbeiroEServico(Relatorio relatorio) throws SQLException {		
		return ReportDAO.getInstance().pesquisarPorDataBarbeiroEServico(relatorio);
	}
	
	/* Gets an instance of RelatorioController. */
	public static RelatorioController getInstance() {
		if(instance == null)
			instance = new RelatorioController();
		return instance;
	}

}
