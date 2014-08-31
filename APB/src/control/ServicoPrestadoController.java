/**
 * RelatorioController
 * This class manages the services making insertions, exclusions and 
 * exhibitions of services.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ServicoPrestadoDAO;
import model.ServicoPrestado;

public class ServicoPrestadoController {

	private ServicoPrestadoController() {}
	
	/* Gets an instance of ServicoPrestadoController. */
	public static ServicoPrestadoController getInstance() {
		if (instance == null)
			instance = new ServicoPrestadoController();
		return instance;
	}

	private static ServicoPrestadoController instance;

	/* Inserts a service in the database. */
	public boolean inserir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			ServicoPrestadoDAO.getInstance().incluir(servico);
			return true;
		}
		
		return false;
	}

	/* Excludes a service from the database. */
	public boolean excluir(ServicoPrestado servico) throws SQLException {
		if (servico !=  null) {
			ServicoPrestadoDAO.getInstance().excluir(servico);
			return true;

		}
		return false;	
	}
	
	/* Shows all services registered. */
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException {
		return ServicoPrestadoDAO.getInstance().mostrarServicosPrestadosCadastrados(servico);
	}

	
}
