package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoServico;

public class TipoServicoDAO {

	private static TipoServicoDAO instance;

	private TipoServicoDAO() {
	}

	public static TipoServicoDAO getInstance() {
		if (instance == null)
			instance = new TipoServicoDAO();
		return instance;
	}

	public boolean incluir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + tipoServico.getNomeTipoServico() + "\", " + "\""
				+ tipoServico.getPreco() + "\"); ");

		return true;
	}

	public boolean alterar(String nome,TipoServico tipoServico_alterado, TipoServico tipoServico) throws SQLException {
		if (tipoServico_alterado == null || tipoServico == null) 
			return false;
		
		this.updateQuery("UPDATE tiposervico SET nome = '"
				+ tipoServico_alterado.getNomeTipoServico() + "', " + "preco = '"
				+ tipoServico_alterado.getPreco()  + "' WHERE"
				+ " nome = '" + nome + "';");

		return true;
	}

	public boolean excluir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + tipoServico.getNomeTipoServico() + "\";");
		return true;
	}

	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * FROM tiposervico;");
		
		return rs;
	}
	
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
				+ "nome = '" + servico.getNomeTipoServico() + "';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
