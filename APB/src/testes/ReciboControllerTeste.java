package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import control.ReceiptController;
import exception.ReceiptException;
import exception.ReportException;

public class ReciboControllerTeste {
	
	Report relatorio = new Report();
	
	@Before
	public void setUp() throws ReceiptException, ParseException {
		
				try {
					relatorio.setBarbeiro("Fulano");
					relatorio.setDataFinal("09/09/2013");
					relatorio.setDataInicial("01/01/2013");
					
				} catch (ReportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	}
	
	@Test
	public void getInstanceDeReciboDAODeveRetonarInstanciaCorrente() {
		ReceiptController reciboController = ReceiptController.getInstance();
		assertEquals(ReceiptController.getInstance(), reciboController);
	}
	
	@Test
	public void procurarPorDataEBarbeiroDeReciboControllerDeveMostrarUmRecibo() throws SQLException {
		ReceiptController reciboController = new ReceiptController();
		ResultSet rs = reciboController.pesquisarServicosDoBarbeiro(relatorio.getBarbeiro(), relatorio.getDataInicial(), relatorio.getDataFinal());
		
		while(rs.next());
	}

}
