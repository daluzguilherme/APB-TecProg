package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoServico;

import org.junit.Before;
import org.junit.Test;

import control.TipoServicoController;
import exception.ServicoException;

public class TipoServicoControllerTeste {

	TipoServico servico = new TipoServico();
	TipoServicoController servicoController = TipoServicoController.getInstance();
	
	@Before
	public void setUp(){
		try {
			servico.setNomeTipoServico("Corte");
			servico.setPreco("15,00");
		} catch (ServicoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getInstanceDeTipoServicoControllerDeveRetornarInstanciaCorrente() {
		assertEquals(TipoServicoController.getInstance(), servicoController);
	}

	@Test
	public void inserirDeTipoServicoControllerDeveEnviarUmTipoServico() {
		try {
			assertTrue(servicoController.inserir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void excluirDeTipoServicoControllerDeveRemoverUmTipoServico() {
		try {
			assertTrue(servicoController.excluir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void alterarDeTipoServicoControllerDeveAlterarUmTipoServico() {
		try {
			assertTrue(servicoController.alterar(servico.getNomeTipoServico(),servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inserirTipoServicoNaoPodePassarTipoServicoNullo() {
		try {
			assertFalse(servicoController.inserir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void excluirTipoServicoNaoPodePassarTipoServicoNullo() {
		try {
			assertFalse(servicoController.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void alterarTipoServicoNaoPodePassarTipoServicoNullo() {
		try {
			assertFalse(servicoController.alterar(null,null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mostrarBarbeirosDeBarbeiroControllerDeveMostrarUmBarbeiro() throws SQLException {
		ResultSet rs = servicoController.mostrarTipoServicoCadastrados(servico);
		while(rs.next());
	}
	
	@Test
	public void pesquisarPorNomeDeTipoServicoControllerDeveMostrarUmServico() throws SQLException {
		ResultSet rs = servicoController.pesquisarPorNome(servico);
		while(rs.next());
	}

}
