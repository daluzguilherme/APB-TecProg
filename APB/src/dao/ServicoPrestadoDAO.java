/**
 * ServicoPrestadoDAO.java
 *This class manages the DAO functions of services rendered.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServicoPrestado;

public class ServicoPrestadoDAO {
	
	/* Instance to the singleton. */
	private static ServicoPrestadoDAO instance;

	private ServicoPrestadoDAO() {

		/* Blank constructor. */
	}

	/* Singleton implementation. */
	public static ServicoPrestadoDAO getInstance() {
		if (instance == null)
			instance = new ServicoPrestadoDAO();
		return instance;
	}

	/* Include new services rendered in the database. */
	public boolean incluir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			this.updateQuery("INSERT INTO "
					+ "servicoprestado (nome, preco, barbeiro, data) VALUES ("
					+ "\"" + servico.getNomeServico() + "\", " + "\""
					+ servico.getPreco() + "\", " + "\""
					+ servico.getNomeBarbeiro() + "\", " + "\""
					+ servico.getData() + "\"); ");
			return true;
		}

		return false;
	}

	/* This removes a service rendered from the database. */
	public boolean excluir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			this.updateQuery("DELETE FROM servicoprestado WHERE "
				+ "servicoprestado.idservicoprestado = \"" + pesquisar(servico)+ "\";");
			return true;
		}
		
		return false;
	}

	/*  This searches for services rendered from the database. */
	private String pesquisar(ServicoPrestado servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM servicoprestado WHERE "
						+ "servicoprestado.nome = \""
						+ servico.getNomeServico()
						+ "\" AND servicoprestado.preco = \""
						+ servico.getPreco()
						+ "\" AND servicoprestado.barbeiro = \""
						+ servico.getNomeBarbeiro()
						+ "\" AND servicoprestado.data = \""
						+ servico.getData() + "\";");
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getString("idservicoprestado");
	}

	/* This updates a query. */
	private void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/* Shows registered services rendered */
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
						"SELECT nome, preco, barbeiro, data FROM servicoprestado ORDER BY data;");
		
		return rs;
	}

}
