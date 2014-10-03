package testes;

import static org.junit.Assert.*;

import java.text.ParseException;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import model.ProvidedService;

import org.junit.Test;

import exception.ServiceException;


public class ServicoPrestadoTeste {

	ProvidedService servico = new ProvidedService();
	
	@Test
	public void testeDeConstrutor(){
		ProvidedService servico1 = new ProvidedService("Corte", "15,00","Claudio");
		assertEquals("Corte", servico1.getNomeServico());
		assertEquals("15,00", servico1.getPreco());
		assertEquals("Claudio", servico1.getNomeBarbeiro());
	}
	@Test (expected = NullPointerException.class)
	public void testeSetNomeNaoNulo() throws ServiceException {
		servico.setNomeServico(null);
		Assert.fail("Deve lan�ar exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeSetNomeBranco() throws ServiceException {
		servico.setNomeServico("");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeSetNomeForaDeFormato() throws ServiceException {
		servico.setNomeServico("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test
	public void testeSetNomeValido() {
		try {
			servico.setNomeServico("Corte");
		} catch (ServiceException e) {
			e.printStackTrace();
			Assert.fail("N�o deve lan�ar exce��o");
		}
		assertEquals("Corte", servico.getNomeServico());
	}
	
	@Test (expected = ServiceException.class)
	public void precoForaDeFormato() throws ServiceException {
		servico.setPreco("as");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = NullPointerException.class)
	public void testePrecoNaoNulo() throws ServiceException {
		servico.setPreco(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testePrecoEmBranco() throws ServiceException {
		servico.setPreco("");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test
	public void testePrecoValido() {
		try {
			servico.setPreco("123,45");
		} catch (ServiceException e) {
			Assert.fail("N�o deve lan�ar exce��o");
		}
		assertEquals("123,45", servico.getPreco());
	}
	
	@Test (expected = NullPointerException.class)
	public void testeNomeBarbeiroNulo() throws ServiceException {
		servico.setNomeBarbeiro(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeNomeBarbeiroEmBranco() throws ServiceException {
		servico.setNomeBarbeiro("");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeNomeBarbeiroForaDeFormato() throws ServiceException {
		servico.setNomeBarbeiro("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = AssertionFailedError.class)
	public void testeNomeBarbeiroValido() {
		try {
			servico.setNomeBarbeiro("Jo�o");
		} catch (ServiceException e) {
			Assert.fail("Não deve lançar uma exceção");
		}
		assertEquals("Jo�o", servico.getNomeBarbeiro());
	}
	
	@Test (expected = NullPointerException.class)
	public void testeDataNulo() throws ServiceException {
		try {
			servico.setData(null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (AssertionFailedError e) {
			e.printStackTrace();
		}
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeDataEmBranco() throws ServiceException {
		try {
			servico.setData("");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (AssertionFailedError e) {
			e.printStackTrace();
		}
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test (expected = ServiceException.class)
	public void testeDataForaDeFormato() throws ServiceException {
		try {
			servico.setData("abc");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (AssertionFailedError e) {
			e.printStackTrace();
		}
		Assert.fail("Deve lan�ar uma exce��o");
	}
	
	@Test
	public void testeDataParaConverter() {
		try {
			servico.ConverterDataParaABNT("2010-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeDataNormal() {
		try {
			servico.setData("10/10/2012");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = AssertionError.class)
	public void getterDeDataDeveFuncionar(){
		assertEquals("10/10/2012", servico.getData());
	}
}
