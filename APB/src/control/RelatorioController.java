package control;

import java.sql.ResultSet;
import java.sql.SQLException;


import dao.RelatorioDAO;
import model.Relatorio;

public class RelatorioController {
	
private static RelatorioController instance;

	public RelatorioController() {}
	
	/* Search a report by date. */
	public ResultSet pesquisarPorData(Relatorio relatorio) throws SQLException {
		return RelatorioDAO.getInstance().pesquisarPorData(relatorio);
	}
	
	/* Search a report by date and a specific barber. */
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio) throws SQLException {	
		return RelatorioDAO.getInstance().pesquisarPorDataEBarbeiro(relatorio);
	}
	
	/* Search a report by date and service. */
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio) throws SQLException {	
		return RelatorioDAO.getInstance().pesquisarPorDataEServico(relatorio);
	}
	
	/* Search a report by barber. */
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio) throws SQLException {	
		return RelatorioDAO.getInstance().pesquisarPorBarbeiro(relatorio);
	}
	
	/* Search a report by barber and service. */
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio) throws SQLException {	
		return RelatorioDAO.getInstance().pesquisarPorBarbeiroEServico(relatorio);
	}
	
	/* Search a report by service. */
	public ResultSet pesquisarPorServico(Relatorio relatorio) throws SQLException {	
		return RelatorioDAO.getInstance().pesquisarPorServico(relatorio);
	}
	
	/* Search a report by date, barber and service. */
	public ResultSet pesquisarPorDataBarbeiroEServico(Relatorio relatorio) throws SQLException {		
		return RelatorioDAO.getInstance().pesquisarPorDataBarbeiroEServico(relatorio);
	}
	
	/* Gets an instance of RelatorioController. */
	public static RelatorioController getInstance() {
		if(instance == null)
			instance = new RelatorioController();
		return instance;
	}

}
