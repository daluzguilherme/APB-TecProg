/**
 * RelatorioController
 * This class manages the services making insertions, exclusions and 
 * exhibitions of services.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ProvidedServiceDAO;
import model.ServicoPrestado;

public class ServicoPrestadoController {

	private ServicoPrestadoController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active ServicoPrestadoController instance, since it will be just one at
	 * time.
	 */
	public static ServicoPrestadoController getInstance() {
		if (instance == null)
			instance = new ServicoPrestadoController();
		return instance;
	}

	private static ServicoPrestadoController instance;

	/**
	 * Insert a service in the database.
	 * @param servico instance of an type of object ServicoPrestado.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if service is null.
	 */
	public boolean inserir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			ProvidedServiceDAO.getInstance().incluir(servico);
			return true;
		}
		
		return false;
	}

	/**
	 * Removes a service in the database.
	 * @param servico instance of an type of object ServicoPrestado.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if service is null.
	 */
	
	public boolean excluir(ServicoPrestado servico) throws SQLException {
		if (servico !=  null) {
			ProvidedServiceDAO.getInstance().excluir(servico);
			return true;

		}
		return false;	
	}
	
	/**
	 * Displays all services registered in the database.
	 * @param servico instance of an type of object ServicoPrestado.
	 * @return Show the services registered in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException {
		return ProvidedServiceDAO.getInstance().mostrarServicosPrestadosCadastrados(servico);
	}

	
}
