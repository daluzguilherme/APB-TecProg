package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Relatorio;

import org.junit.Before;
import org.junit.Test;

import dao.ReportDAO;

import exception.RelatorioException;

public class RelatorioDAOTeste {

	Relatorio relatorio = new Relatorio();

	@Before
	public void setUp() throws RelatorioException, ParseException {
		try {
			relatorio.setBarbeiro("Luciano");
			relatorio.setDataFinal("09/09/2013");
			relatorio.setDataInicial("01/01/2013");
			relatorio.setTipoServico("corte");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente() {
		ReportDAO relatorioDAO = ReportDAO.getInstance();
		assertEquals(ReportDAO.getInstance(), relatorioDAO);
	}

	@Test
	public void procurarPorDataDeRelatorioDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorData(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pesquisarPorDataEServicoDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataEServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pesquisarPorBArbeiroDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorBarbeiro(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pesquisarPorBArbeiroEServicoDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorBarbeiroEServico(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pesquisarPorServicoDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void pesquisarPorDataEBArbeiroDAODeveMostrarUmRelatorio() {
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataEBarbeiro(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void pesquisarPorDataBarbeiroEServicoDAODeveMostrarUmRelatorio(){
		try {
			ReportDAO relatorioDAO = ReportDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataBarbeiroEServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
