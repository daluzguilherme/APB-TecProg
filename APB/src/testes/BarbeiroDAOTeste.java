package testes;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barbeiro;

import org.junit.Before;
import org.junit.Test;

import dao.BarbeiroDAO;
import dao.FactoryConnection;
import exception.BarbeiroException;

public class BarbeiroDAOTeste {

	Barbeiro barbeiro = new Barbeiro();
	Barbeiro barbeiro2 = new Barbeiro();
	@Before
	public void setUp() {
		try {
			barbeiro.setNome("Alessandro");
			barbeiro.setRg("418757896");
			barbeiro.setTelefone("3389-9085");
			barbeiro.setCpf("02919594150");
			barbeiro.setCadeira("5");
			barbeiro2.setNome("Luciano");
			barbeiro2.setRg("418757896");
			barbeiro2.setTelefone("3389-9085");
			barbeiro2.setCpf("02919594150");
			barbeiro2.setCadeira("5");
			
			BarbeiroDAO barbeiroDao = BarbeiroDAO.getInstance();
			barbeiroDao.incluir(barbeiro);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (BarbeiroException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	BarbeiroDAO barbeiroDAO = BarbeiroDAO.getInstance();
	
	@Test
	public void getInstanceDeBarbeiroDAODeveRetonarInstanciaCorrente() {	
		assertEquals(BarbeiroDAO.getInstance(), barbeiroDAO);
	}

	@Test
	public void inserirDeBarbeiroDAODeveCadastrarUmBarbeiro() {
		try {
			assertTrue(barbeiroDAO.incluir(barbeiro));
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement().executeQuery("SELECT nome FROM barbeiro WHERE "
					+ " nome = \"" + barbeiro.getNome() + "\";");
			rs.next();
			assertEquals("Alessandro", rs.getString("nome"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test (expected = AssertionError.class)
	public void excluirDeBarbeiroDAODeveEnviarUmBarbeiro() {
		try {
			assertTrue(barbeiroDAO.excluir(barbeiro));
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement().executeQuery("SELECT nome FROM barbeiro WHERE "
					+ " nome = \"" + barbeiro.getNome() + "\";");
			rs.next();
			fail();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeBarbeiroDaoDeveAlterarUmBarbeiro() {
		try {
			assertTrue(barbeiroDAO.alterar(barbeiro.getNome(), barbeiro, barbeiro2));
			
			barbeiroDAO.alterar(barbeiro.getCpf(),barbeiro2, barbeiro);
			Connection connection = FactoryConnection.getInstance().getConnection();
			java.sql.PreparedStatement pst1 = connection.prepareStatement("SELECT nome FROM barbeiro WHERE "
							+ " nome = \"" + barbeiro.getNome() + "\";");
			
			ResultSet rs = pst1.executeQuery();
			
			while(rs.next())
				assertEquals("Alessandro", rs.getString("nome"));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inserirDeBarbeiroDAOPassandoUmBarbeiroNulo() {
		try {
			assertFalse(barbeiroDAO.incluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluirDeBarbeiroDAOPassandoUmBarbeiroNulo() {
		try {
			assertFalse(barbeiroDAO.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeBarbeiroDaoPassandoUmBarbeiroNulo() {
		try {
			assertFalse(barbeiroDAO.alterar(barbeiro.getNome(), null, null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeBarbeiroDaoPassandoUmBarbeiroAlteradoNulo() {
		try {
			assertFalse(barbeiroDAO.alterar(barbeiro.getNome(), null, barbeiro));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pesquisarDeBarbeiroDAODeveMostrarUmBarbeiro() {
		try {
			ResultSet rs = barbeiroDAO.pesquisar();
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mostrarBarbeirosCadastradosDeBarbeiroDAODeveMostrarBarbeiros() {
		try {
			ResultSet rs = barbeiroDAO.mostrarBarbeirosCadastrados(barbeiro);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pesquisarPorNomeDeBarbeiroDAODeveMostrarBarbeiros() {
		try {
			ResultSet rs = barbeiroDAO.pesquisarPorNome(barbeiro);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}