package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TipoServicoDAO;
import model.TipoServico;

public class TipoServicoController {

	private static TipoServicoController instance;

	public boolean inserir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().incluir(tipoServico);
			return true;
		}
	}

	public boolean alterar(String nome,TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServico tipoServico_alterado = tipoServico;
			TipoServicoDAO.getInstance().alterar(nome,tipoServico_alterado, tipoServico);
			return true;
		}
	}

	public boolean excluir(TipoServico tipoServico) throws SQLException {

		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().excluir(tipoServico);
			return true;
		}
	}

	private TipoServicoController() {
	}

	public static TipoServicoController getInstance() {
		if (instance == null)
			instance = new TipoServicoController();
		return instance;
	}
	
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().mostrarTipoServicoCadastrados(servico);
	}
	
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().pesquisarPorNome(servico);
	}

}
