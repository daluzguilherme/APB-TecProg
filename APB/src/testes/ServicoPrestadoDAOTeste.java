package testes;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import model.ProvidedService;

import org.junit.Before;
import org.junit.Test;

import dao.ProvidedServiceDAO;
import exception.ServicoException;

public class ServicoPrestadoDAOTeste {

	ProvidedService servico = new ProvidedService();
	ProvidedService servico2 = new ProvidedService();
	@Before
	public void setUp() {
		try {
			servico.setNomeServico("Corte");
			servico.setNomeBarbeiro("Alessandro");
			servico.setData("10/10/2010");
			servico.setPreco("10,00");
			servico2.setNomeServico("Barba");
			servico2.setNomeBarbeiro("Luciano");
			servico2.setData("01/01/2010");
			servico2.setPreco("9,90");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ServicoException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	ProvidedServiceDAO servicoDAO = ProvidedServiceDAO.getInstance();
	
	@Test
	public void getInstanceDeServicoPrestadoDAODeveRetonarInstanciaCorrente() {
		assertEquals(ProvidedServiceDAO.getInstance(), servicoDAO);
	}

	@Test
	public void inserirDeServicoPrestadoDAODeveCadastrarUmServicoPrestado() {
		try {
			assertTrue(servicoDAO.incluir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void excluirDeServicoPrestadoDAODeveEnviarUmServicoPrestado() {
		try {
			assertTrue(servicoDAO.excluir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inserirDeServicoPrestadoDAOPassandoUmServicoNulo() {
		try {
			assertFalse(servicoDAO.incluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluirDeServicoPrestadoDAOPassandoUmServicoNulo() {
		try {
			assertFalse(servicoDAO.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
