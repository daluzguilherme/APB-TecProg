package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BarbeiroDAO;
import model.Barbeiro;

public class BarbeiroController {

	private static BarbeiroController instance;

	private BarbeiroController() {}
	
	public static BarbeiroController getInstance() {
		if (instance == null)
			instance = new BarbeiroController();
		return instance;
	}
	
	public boolean inserir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null) 
			return false;
			
		BarbeiroDAO.getInstance().incluir(barbeiro);
		return true;
	}

	public boolean alterar(String nome, Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		Barbeiro barbeiro_alterado = barbeiro;
		BarbeiroDAO.getInstance().alterar(nome, barbeiro_alterado, barbeiro);
		return true;
	}

	public boolean excluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;
		
		BarbeiroDAO.getInstance().excluir(barbeiro);
		return true;
	}
	
	public ResultSet pesquisar() throws SQLException {
		return BarbeiroDAO.getInstance().pesquisar();
	}
	
	public ResultSet mostrarBarbeirosCadastrados(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().mostrarBarbeirosCadastrados(barbeiro);
	}
	
	public ResultSet pesquisarPorNome(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().pesquisarPorNome(barbeiro);
	}

}
