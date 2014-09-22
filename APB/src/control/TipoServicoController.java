/**
 * TipoServicoController
 * This class provides methods to manage the different kinds of services
 * such as insert a new type of service, alter a service's name, exclude
 * a type of service and display types of services.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ServiceTypeDAO;
import model.TipoServico;

public class TipoServicoController {
	
	private TipoServicoController() {}

	private static TipoServicoController instance;
	
	/**
	 * Provides the singleton implementation
	 * @return the active TipoServicoPrestadoController instance, since it will be just one at
	 * time.
	 */
	public static TipoServicoController getInstance() {
		if (instance == null)
			instance = new TipoServicoController();
		return instance;
	}

	/**
	 * Insert a new type service in the database.
	 * @param tipoServico instance of a type of object TipoServico.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if  type of service is null.
	 */
	public boolean inserir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			ServiceTypeDAO.getInstance().incluir(tipoServico);
			return true;
		}
	}

	/**
	 * Alters a name of a type of service in the database.
	 * @param nome his represents the new service's name.
	 * @param tipoServico instance of a type of object TipoServico.
	 * @return false if tipoServico is null.
	 * @return true if no problems.
	 * @throws SQLException If has some problem during the database update
	 */
	public boolean alterar(String nome,TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServico tipoServico_alterado = tipoServico;
			ServiceTypeDAO.getInstance().alterar(nome,tipoServico_alterado, tipoServico);
			return true;
		}
	}

	/**
	 * Removes a type of service from the database.
	 * @param tipoServico instance of a type of object TipoServico.
	 * @throws SQLException If has some problem during the database insertion
	 * @return true if no problems.
	 * @return false if  type of service is null.
	 */
	
	public boolean excluir(TipoServico tipoServico) throws SQLException {

		if (tipoServico == null) {
			return false;
		} else {
			ServiceTypeDAO.getInstance().excluir(tipoServico);
			return true;
		}
	}
	
	/**
	 * Displays all types of service registered in the database.
	 * @param tipoServico instance of a type of object TipoServico.
	 * @return Show all types of service registered in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		return ServiceTypeDAO.getInstance().mostrarTipoServicoCadastrados(servico);
	}
	
	/* Search a service by name. */
	/**
	 * Displays types of service by name in the database.
	 * @param servico instance of an type of object TipoServico.
	 * @return Show a service by name in the database.
	 * @throws SQLException If has some problem during the database deletion
	 */
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		return ServiceTypeDAO.getInstance().pesquisarPorNome(servico);
	}

}
