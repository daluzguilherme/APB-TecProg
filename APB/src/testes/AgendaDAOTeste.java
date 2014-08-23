package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;

import org.junit.Test;

import dao.AgendaDAO;

public class AgendaDAOTeste {

	Agenda contato = new Agenda();
	Agenda contato2 = new Agenda();
	AgendaDAO agendaDAO = AgendaDAO.getInstance();
	
	@Test
	public void getInstanceDeAgendaDAODeveRetonarInstanciaCorrente() {
		assertEquals(AgendaDAO.getInstance(), agendaDAO);
	}

	@Test
	public void inserirDeAgendaDAODeveCadastrarUmContato() {
		try {
			assertTrue(agendaDAO.incluir(contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void excluirDeAgendaDAODeveEnviarUmAgenda() {
		try {
			assertTrue(agendaDAO.excluir(contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeAgendaDAODeveEnviarUmContato() {
		try {
			assertTrue(agendaDAO.alterar(contato.getNome(),contato, contato2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inserirDeAgendaDAOPassandoUmContatoNulo() {
		try {
			assertFalse(agendaDAO.incluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluirDeAgendaDAOPassandoUmContatoNulo() {
		try {
			assertFalse(agendaDAO.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeAgendaDAOPassandoUmContatoNulo() {
		try {
			assertFalse(agendaDAO.alterar(contato.getNome(),contato, null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeAgendaDAOPassandoUmAgendaAleradoNulo() {
		try {
			assertFalse(agendaDAO.alterar(contato.getNome(), null, contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mostrarContatosAgendaDAODeveMostrarContato() {
		try {
			ResultSet rs = agendaDAO.mostrarContatosCadastrados(contato);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pesquisaPorNomeDeAgendaDAODeveMostrarContato() {
		try {
			ResultSet rs = agendaDAO.pesquisarPorNome(contato);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pesquisarPorTelefoneDeBarbeiroDAODeveMostrarBarbeiros() {
		try {
			ResultSet rs = agendaDAO.pesquisarPorTelefone(contato);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
