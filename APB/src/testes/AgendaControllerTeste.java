package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;

import org.junit.Before;
import org.junit.Test;

import control.AgendaController;
import exception.BarbeiroException;

public class AgendaControllerTeste {

	Agenda contato = new Agenda();

	@Before
	public void setUp() {
		try {
			contato.setNome("Corte");
			contato.setTelefone("3895-5698");
			contato.setDescricao("AAA");
		} catch (BarbeiroException e) {
			e.printStackTrace();
		}
	}

	AgendaController agendaController = AgendaController.getInstance();
	
	@Test
	public void getInstanceDeAgendaControllerDeveRetornarInstanciaCorrente() {
		assertEquals(AgendaController.getInstance(), agendaController);
	}

	@Test
	public void inserirDeAgendaControllerDeveEnviarUm() {
		try {
			assertTrue(agendaController.incluir(contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void excluirDeAgendaControllerDeveEnviarUmaAgenda() {
		try {
			assertTrue(agendaController.excluir(contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void alterarDeAgendaControllerDeveEnviarUmaAgendaAlterada() {
		try {
			assertTrue(agendaController.alterar(contato.getNome(),contato));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inserirAgendaNaoPodePassarAgendaNullo() {
		try {
			assertFalse(agendaController.incluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void excluirAgendaNaoPodePassarAgendaNullo() {
		try {
			assertFalse(agendaController.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void alterarAgendaNaoPodePassarAgendaNullo() {
		try {
			assertFalse(agendaController.alterar(null,null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mostrarContatosDeAgendaControllerDeveMostrarUmContato() throws SQLException {
		ResultSet rs = agendaController.mostrarContatosCadastrados(contato);
		while(rs.next());
	}
	
	@Test
	public void pesquisarPorNomeDeAgendaControllerDeveMostrarUmContato() throws SQLException {
		ResultSet rs = agendaController.pesquisarPorNome(contato);
		while(rs.next());
	}

	@Test
	public void pesquisarPorTelefoneDeAgendaControllerDeveMostrarUmContato() throws SQLException {
		ResultSet rs = agendaController.pesquisarPorTelefone(contato);
		while(rs.next());
	}
	
}
