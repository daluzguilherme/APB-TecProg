/**
 * RelatorioController
 * This class manages the services making insertions, exclusions and 
 * exhibitions of services.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ProvidedServiceDAO;
import model.ProvidedService;

public class ProvidedServiceController {

	private ProvidedServiceController() {}
	
	/**
	 * Provides the singleton implementation
	 * @return the active ServicoPrestadoController instance,
	 * 		since it will be just one at a time.
	 */
	public static ProvidedServiceController getInstance() {
		
		if (instance == null)
			instance = new ProvidedServiceController();
		
		return instance;
	}

	private static ProvidedServiceController instance;

	/**
	 * Insert a service in the database.
	 * @param servico instance of an type of object ServicoPrestado.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if service is null.
	 */
	public boolean inserir(ProvidedService servico) throws SQLException {
		
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
	
	public boolean excluir(ProvidedService servico) throws SQLException {
		
		if (servico !=  null) {
			ProvidedServiceDAO.getInstance().excluir(servico);
			
			return true;
		} else {
			// Nothing to do.
		}
		
		return false;	
	}
	
	/**
	 * Displays all services registered in the database.
	 * @param service instance of an type of object ServicoPrestado.
	 * @return Show the services registered in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarServicosPrestadosCadastrados(ProvidedService service)
			throws SQLException {
		
		return ProvidedServiceDAO.getInstance()
				.mostrarServicosPrestadosCadastrados(service);
	}

}
