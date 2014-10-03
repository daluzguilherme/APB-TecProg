package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import exception.BarberException;
import exception.ServiceException;
import model.AddressBook;

public class AgendaTeste {
	
	AddressBook contato = new AddressBook();
	
	@Before
	public void setUp(){
		try {
			contato.setNome("Alessandro");
			contato.setTelefone("4568-9856");
		} catch (BarberException e1) {
			e1.printStackTrace();
		}
		contato.setDescricao("ASDAS");
	}
	
	@Test
	public void contrutorDeAgendaDeveFuncionar(){
		AddressBook contato = new AddressBook("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getNome());
		assertEquals("6589-5689", contato.getTelefone());
		assertEquals("aaaa", contato.getDescricao());
	}
	
	@Test
	public void getterDeNomeDeveFuncionar(){
		assertEquals("Alessandro", contato.getNome());
	}
	
	@Test
	public void getterDeTelefoneDeveFuncionar(){
		assertEquals("4568-9856", contato.getTelefone());
	}
	
	@Test
	public void getterDeDescricaoDeveFuncionar(){
		assertEquals("ASDAS", contato.getDescricao());
	}
	
	
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoEmBranco() throws BarberException{
		contato.setNome("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoEmBranco() throws BarberException{
		contato.setTelefone("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoForaDeFormato() throws BarberException{
		contato.setNome("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoForaDeFormato() throws BarberException{
		contato.setTelefone("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	

	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServiceException {
		assertEquals("Barba", AddressBook.getTempNome());
	}
	
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo() throws ServiceException {
		AddressBook.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco() {
		AddressBook.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test
	public void tempNomeValido() throws BarberException {
		AddressBook.setTempNome("Paulo");
		assertEquals("Paulo", AddressBook.getTempNome());
	}

}
