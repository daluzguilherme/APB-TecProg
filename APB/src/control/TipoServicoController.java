/**
 * TipoServicoController
 * This class provides methods to manage the different kinds of services
 * such as insert a new type of service, alter a service's name, exclude
 * a type of service and display types of services.
 */

package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TipoServicoDAO;
import model.TipoServico;

public class TipoServicoController {

	private static TipoServicoController instance;

	/* Inserts a new type of service in the database. */
	public boolean inserir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().incluir(tipoServico);
			return true;
		}
	}

	/* Alters the name of a type of service in the database. */
	public boolean alterar(String nome,TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServico tipoServico_alterado = tipoServico;
			TipoServicoDAO.getInstance().alterar(nome,tipoServico_alterado, tipoServico);
			return true;
		}
	}

	/* Excludes a type of service from the database. */
	public boolean excluir(TipoServico tipoServico) throws SQLException {

		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().excluir(tipoServico);
			return true;
		}
	}

	/* Constructor. */
	private TipoServicoController() {
	}

	/* Gets and instance from TipoServicoController. */
	public static TipoServicoController getInstance() {
		if (instance == null)
			instance = new TipoServicoController();
		return instance;
	}
	
	/* Shows all types of service registered in the database. */
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().mostrarTipoServicoCadastrados(servico);
	}
	
	/* Search a service by name. */
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().pesquisarPorNome(servico);
	}

}
