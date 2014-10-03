package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import dao.AddressBookDAO;
import dao.ReceiptDAO;
import exception.ReceiptException;
import exception.ReportException;

public class ReciboDAOTeste {

	Report relatorio = new Report();

	@Before
	public void setUp() throws ReceiptException, ParseException {
		try {
			relatorio.setBarbeiro("Fulano");
			relatorio.setDataFinal("09/09/2013");
			relatorio.setDataInicial("01/01/2013");

		} catch (ReportException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getInstanceDeReciboDAODeveRetonarInstanciaCorrente() {
		ReceiptDAO reciboDAO = ReceiptDAO.getInstance();
		assertEquals(ReceiptDAO.getInstance(), reciboDAO);
	}

	@Test
	public void pesquisarPorDataEBArbeiroDAODeveMostrarUmRecibo() {
		try {
			ReceiptDAO reciboDAO = ReceiptDAO.getInstance();
			ResultSet rs = reciboDAO.pesquisarServicosDoBarbeiro(
					relatorio.getBarbeiro(), relatorio.getDataInicial(),
					relatorio.getDataFinal());

			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

	}

}
