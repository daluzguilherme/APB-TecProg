package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import control.ReportController;
import exception.ReportException;

public class RelatorioControllerTeste {

	Report relatorio = new Report();
	
	
	@Before
	public void setUp() throws ReportException, ParseException {
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
		ReportController relatorioController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), relatorioController);
	}
	
	@Test
	public void procurarPorServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorServico(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorDataDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorData(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorBarbeiroEServico(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorDataBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorDataBarbeiroEServico(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorDataEBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorDataEBarbeiro(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorDataEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorDataEServico(relatorio);
		
		while(rs.next());
	}
	
	@Test
	public void procurarPorBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.pesquisarPorBarbeiro(relatorio);
		
		while(rs.next());
	}

}
