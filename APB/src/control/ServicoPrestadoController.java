package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ServicoPrestadoDAO;
import model.ServicoPrestado;

public class ServicoPrestadoController {

	private ServicoPrestadoController() {}
	
	public static ServicoPrestadoController getInstance() {
		if (instance == null)
			instance = new ServicoPrestadoController();
		return instance;
	}

	private static ServicoPrestadoController instance;

	public boolean inserir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			ServicoPrestadoDAO.getInstance().incluir(servico);
			return true;
		}
		
		return false;
	}

	public boolean excluir(ServicoPrestado servico) throws SQLException {
		if (servico !=  null) {
			ServicoPrestadoDAO.getInstance().excluir(servico);
			return true;

		}
		return false;	
	}
	
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException {
		return ServicoPrestadoDAO.getInstance().mostrarServicosPrestadosCadastrados(servico);
	}

	
}
