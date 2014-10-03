package testes;

import static org.junit.Assert.*;

import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import exception.ReportException;

public class RelatorioTeste {

	Report relatorio;

	@Before
	public void setUp() throws ParseException {
		try {
			relatorio = new Report();

			relatorio.setBarbeiro("Chico");
			relatorio.setTipoServico("barba");
			relatorio.setDataInicial("01/01/2013");
			relatorio.setDataFinal("09/09/2013");

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ReportException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = NullPointerException.class)
	public void dataInicialNaoPodeSerSettadaNula() throws NullPointerException, ParseException {
		try {
			relatorio.setDataInicial(null);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = AssertionError.class)
	public void dataInicialNaoPodeSerSettadaEmBranco() throws NullPointerException, ParseException {
		try {
			relatorio.setDataInicial("");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void dataFinalNaoPodeSerSettadaNula() throws NullPointerException, ParseException {
		try {
			relatorio.setDataFinal(null);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = AssertionError.class)
	public void dataFinalNaoPodeSerSettaEmBranco() throws NullPointerException, ParseException {
		try {
			relatorio.setDataFinal("");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodeSerSettadoNulo() {
		try {
			relatorio.setBarbeiro(null);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = AssertionError.class)
	public void barbeiroNaoPodeSerSettoEmBranco() {
		try {
			relatorio.setBarbeiro("");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void tipoDeServicoNaoPodeSerSettadoNulo() {
		try {
			relatorio.setTipoServico(null);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = AssertionError.class)
	public void tipoDeServicoNaoPodeSerSettoEmBranco() {
		try {
			relatorio.setTipoServico("");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void construtorDeRelatorioNaoPodePassarBarbeiroNulo() {
		try {
			new Report ("2013-01-01", "2013-01-01", null, "barba");
		} catch(ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void construtorDeRelatorioNaoPodePassarDataFinalNula() {
		try {
			new Report("2013-01-01", null, "Chico", "barba");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void construtorDeRelatorioNaoPodePassarDataInicialNula() {
		try {
			new Report (null, "2013-01-01", "Chico", "barba");
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void construtorDeRelatorioNaoPodePassarTipoServicoNulo() {
		try {
			new Report ("2013-01-01", "2013-01-01", "Chico", null);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test 
	public void construtorDeRelatorioPassandoTodosOsDadosCorretos() {
		try {
			new Report ("2013-01-01", "2013-12-31", "Chico", "barba");
			assertEquals(relatorio, relatorio);
		} catch (ReportException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void metodoParaTestarGetterDeBarbeiro() {
		assertEquals("Chico", relatorio.getBarbeiro());
	}
	
	@Test
	public void metodoParaTestarGetterDeTipoDeServico() {
		assertEquals("barba", relatorio.getTipoServico());
	}
	
	@Test
	public void metodoParaTestarGetterDeDataInicial() {
		assertEquals("2013-01-01", relatorio.getDataInicial());
	}
	
	@Test
	public void metodoParaTestarGetterDeDataFinal() {
		assertEquals("2013-09-09", relatorio.getDataFinal());
	}
	
	@Test
	public void testeDataParaConverter() {
		try {
			relatorio.ConverterDataParaABNT("2010-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
